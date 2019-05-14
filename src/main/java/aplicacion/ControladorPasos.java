package aplicacion;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Habitacion;

public class ControladorPasos implements Initializable {
	
	@FXML
    public JFXTabPane tabPane;
	
	@FXML
    public Tab idTabHab, idTabServ, idTabPago, idTabFin;
	
	@FXML
	public Hyperlink lblSesion;
	
	@FXML
	public Label lblSaludo;
	
    @FXML
    private FontAwesomeIconView iconInfo;
    
    @FXML
    public AnchorPane anchorPaneBase, botones;
	
	@FXML
    private JFXButton btnLogin, btnSiguiente, btnAtras, btnCancelar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comprobarSesionIniciada();
		idTabPago.setDisable(true);
		idTabFin.setDisable(true);
		Principal.aplicacion.controladorPasos = this;
	}
	
	@FXML
    void IniciarCerrar(ActionEvent event) {
		if(Principal.modelo.cliente == null) {
			Principal.aplicacion.CambiarScene("LoginRegistro.fxml");
			Principal.aplicacion.controladorLoginRegistro.setPantallaAnterior("Pasos.fxml");
			Principal.aplicacion.controladorLoginRegistro.setTabActiva(tabPane.getSelectionModel().getSelectedIndex());
		} else {
			if (tabPane.getSelectionModel().getSelectedItem() == idTabPago ) {
				JOptionPane.showMessageDialog(new JFrame(), "Es necesario estar identificado para realizar el pago. Si cierra sesion, se le devolvera a una pantalla anterior. ¿Desea cerrar sesion de todas formas?", "Error", JOptionPane.ERROR_MESSAGE);
				tabPane.getSelectionModel().select(idTabHab);
			} else if(tabPane.getSelectionModel().getSelectedItem() == idTabFin) {
				JOptionPane.showMessageDialog(new JFrame(), "Es necesario estar identificado para ver la factura de su reserva. Si cierra sesion, se le devolvera a una pantalla anterior. ¿Desea cerrar sesion de todas formas?", "Error", JOptionPane.ERROR_MESSAGE);
				Principal.iniciarPrograma();
			}
			Principal.modelo.cliente = null;
			comprobarSesionIniciada();
		}
    }
	
	@FXML
    void cancelar(ActionEvent event) {
		if(Principal.aplicacion.ventanaSiNo("¿Desea cancelar la compra?")) {
			Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
		}
    }

    @FXML
    void siguiente(ActionEvent event) {
		switch(tabPane.getSelectionModel().getSelectedItem().getId()) {	
				
			case "idTabHab":
				btnSiguienteHabitaciones();
				break;
				
//			case "idTabServ":
//				btnSiguienteServicios();
				
			case "idTabPago":
				btnSiguientePago();
				break;
		}
    }

    @FXML
    void volver(ActionEvent event) {
    	if(tabPane.getSelectionModel().getSelectedIndex()>0) {
        	tabPane.getSelectionModel().select(tabPane.getSelectionModel().getSelectedIndex()-1);
    	}
    }
    
    @FXML
    void mostrarInfo(MouseEvent event) {
    	Principal.aplicacion.cargarPopupInfo(iconInfo.localToScreen(iconInfo.getBoundsInLocal()));
    }
    
    
    public void btnSiguienteHabitaciones() {
    	if(comprobarHabitacionSeleccionada()) {
			float precio = Principal.modelo.pago.getPrecioTotal(Principal.modelo.reserva.getHabitacionesReservadas(),0);
			Principal.modelo.pago.setPrecioTotal(precio);
			Principal.modelo.reserva.setPrecio(precio);
			if(Principal.modelo.cliente != null) {
				idTabPago.setDisable(false);
				tabPane.getSelectionModel().select(idTabPago);
			} else {
				Principal.aplicacion.CambiarScene("LoginRegistro.fxml");
				Principal.aplicacion.controladorLoginRegistro.setPantallaAnterior("Pasos.fxml");
				Principal.aplicacion.controladorLoginRegistro.setTabActiva(tabPane.getSelectionModel().getSelectedIndex());
				Principal.aplicacion.controladorLoginRegistro.setLoginForced(true);
			}
		}
    }
    
    public void btnSiguientePago() {
    	if (Principal.modelo.pago.calcularDineroRestante() == 0) {
			
			// guarda la fecha de la compra
			Date fechaCompra = Date.valueOf(LocalDate.now());
			Principal.modelo.reserva.setFechaCompra(fechaCompra);
			
			// inserta la reserva en la base de datos
			int codReserva = Principal.modelo.gestorBBDD.insertarReserva(Principal.modelo.reserva);
			for (Habitacion h: Principal.modelo.reserva.getHabitacionesReservadas()) {
				Principal.modelo.gestorBBDD.modificarBBDD.insertarReservaHabitaciones(codReserva, h.getCodEstancia(), h.getCantidad());
			}
			
			// guarda el codigo de la reserva
	    	Principal.modelo.reserva.setCodReserva(codReserva);
	    	
	    	// actualiza y muestra la pantalla final
	    	anchorPaneBase.getChildren().remove(botones);
	    	idTabFin.setDisable(false);
	    	idTabHab.setDisable(true);
	    	idTabPago.setDisable(true);
	    	tabPane.getSelectionModel().select(idTabFin);
	    	
    	} else {
    		Principal.aplicacion.mostrarMensaje(Principal.aplicacion.controladorPasos.anchorPaneBase, "Aun no ha introducido todo el dinero");
    	}
    }
    
    public boolean comprobarHabitacionSeleccionada() {
		boolean sigTab = true;
		ArrayList<Habitacion> habitacionesReservadas = Principal.modelo.reserva.getHabitacionesReservadas();
		if (habitacionesReservadas.size() == 0) {
			sigTab = false;
			JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar al menos una habitación", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean select = false;
			for (int i = 0; i < habitacionesReservadas.size(); i++) {
				if (habitacionesReservadas.get(i).getCantidad() > 0) {
					select = true;
				}
			}
			if (!select) {
				sigTab = false;
				JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar al menos una habitación", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return sigTab;
	}
    
    public void selecionarTab(int index) {
    	if (tabPane.getTabs().get(index).isDisable()) {
    		tabPane.getTabs().get(index).setDisable(false);
    	}
    	tabPane.getSelectionModel().select(index);
    }

    /**
     * 
     */
    public void comprobarSesionIniciada() {
		if(Principal.modelo.cliente != null) {
			lblSaludo.setText("Hola, " + Principal.modelo.cliente.getUser());
			lblSesion.setText("Cerrar Sesion");
		} else {
			lblSaludo.setText("Hola, Anonimo");
			lblSesion.setText("Identifiquese");
		}
	}

}
