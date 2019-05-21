package aplicacion;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import modelo.GestorDinero;
import modelo.Habitacion;
import modelo.Modelo;
import modelo.Reserva;
import modelo.Servicio;

public class ControladorPasos implements Initializable {
	
	@FXML
    public JFXTabPane tabPane;
	
	@FXML
    public Tab idTabHab, idTabServ, idTabPago, idTabFin;
	
	@FXML
	public Hyperlink lblSesion;
	
	@FXML
	public Label lblSaludo, carrito;
	
    @FXML
    private FontAwesomeIconView iconInfo;
    
    @FXML
    public AnchorPane anchorPaneBase, botones;
	
	@FXML
    private JFXButton btnLogin, btnSiguiente, btnAtras, btnCancelar;
	
	private FontAwesomeIconView iconoCama;
	
	private Modelo modelo() {return Principal.modelo;}
	private Aplicacion aplicacion() {return Principal.aplicacion;}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iconoCama = new FontAwesomeIconView(FontAwesomeIcon.BED);
		comprobarSesionIniciada();
		idTabPago.setDisable(true);
		idTabFin.setDisable(true);
		aplicacion().controladorPasos = this;
	}
	
	public void actualizarCarrito() {
		ArrayList<Habitacion> habitaciones = modelo().reserva.getHabitacionesSeleccionadas();
		int numHab = 0;
		for (int i = 0; i < habitaciones.size(); i++) {
			numHab += habitaciones.get(i).getCantidad();
		}
		this.carrito.setText(String.valueOf(numHab));
	}
	
	@FXML
    void IniciarCerrar(ActionEvent event) {
		if(modelo().cliente == null) {
			aplicacion().CambiarScene("LoginRegistro.fxml");
			aplicacion().controladorLoginRegistro.setPantallaAnterior("Pasos.fxml");
			aplicacion().controladorLoginRegistro.setTabActiva(tabPane.getSelectionModel().getSelectedIndex());
		} else {
			if (tabPane.getSelectionModel().getSelectedItem() == idTabPago ) {
				JOptionPane.showMessageDialog(new JFrame(), "Es necesario estar identificado para realizar el pago. Si cierra sesion, se le devolvera a una pantalla anterior. ¿Desea cerrar sesion de todas formas?", "Error", JOptionPane.ERROR_MESSAGE);
				tabPane.getSelectionModel().select(idTabHab);
			} else if(tabPane.getSelectionModel().getSelectedItem() == idTabFin) {
				JOptionPane.showMessageDialog(new JFrame(), "Es necesario estar identificado para ver la factura de su reserva. Si cierra sesion, se le devolvera a una pantalla anterior. ¿Desea cerrar sesion de todas formas?", "Error", JOptionPane.ERROR_MESSAGE);
				Principal.iniciarPrograma();
			}
			modelo().cliente = null;
			comprobarSesionIniciada();
		}
    }
	
	@FXML
    void cancelar(ActionEvent event) {
		if(aplicacion().ventanaSiNo("¿Desea cancelar la compra?")) {
			Principal.aplicacion.textoBusqueda = "";
			Principal.modelo.reserva = new Reserva();
			Principal.modelo.gestorDinero = new GestorDinero();
			aplicacion().CambiarScene("SeleccionAlojamiento.fxml");
		}
    }

    @FXML
    void siguiente(ActionEvent event) {
		switch(tabPane.getSelectionModel().getSelectedItem().getId()) {	
				
			case "idTabHab":
				btnSiguienteHabitaciones();
				break;
				
			case "idTabServ":
				btnSiguienteServicios();
				
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
    	aplicacion().cargarPopupInfo(iconInfo.localToScreen(iconInfo.getBoundsInLocal()));
    }
       
    /**
     * Controla los eventos que se dan al darle al botón de siguiente
     * si has iniciado sesión o no lo has hecho
     */
    public void btnSiguienteHabitaciones() {
    	if(comprobarHabitacionSeleccionada()) {
    		ArrayList<Habitacion> habitaciones = modelo().reserva.getHabitacionesSeleccionadas();
    		LocalDate fecha1 = Principal.modelo.reserva.getFechaEntrada().toLocalDate();
    		LocalDate fecha2 = Principal.modelo.reserva.getFechaSalida().toLocalDate();
			float precio = modelo().gestorDinero.calcularPrecioConDescuentos(habitaciones, fecha1, fecha2);
			modelo().gestorDinero.setPrecio(precio);
//			if(modelo().cliente != null) {
//				idTabPago.setDisable(false);
//				tabPane.getSelectionModel().select(idTabPago);
//				Principal.aplicacion.controladorPago.initialize(null, null);
//				Principal.aplicacion.controladorFactura.initialize(null, null);
//			} else {
//				aplicacion().CambiarScene("LoginRegistro.fxml");
//				aplicacion().controladorLoginRegistro.setPantallaAnterior("Pasos.fxml");
//				aplicacion().controladorLoginRegistro.setTabActiva(tabPane.getSelectionModel().getSelectedIndex());
//				aplicacion().controladorLoginRegistro.setLoginForced(true);
//			}
		}
    }
    
    /**
     * Controla los eventos que se dan al darle al botón de siguiente
     * si has iniciado sesión o no lo has hecho
     */
    public void btnSiguienteServicios() {
    	if(comprobarHabitacionSeleccionada()) {
    		ArrayList<Habitacion> habitaciones = modelo().reserva.getHabitacionesSeleccionadas();
    		ArrayList<Servicio> servicios = modelo().reserva.getServiciosSeleccionados();
    		LocalDate fecha1 = Principal.modelo.reserva.getFechaEntrada().toLocalDate();
    		LocalDate fecha2 = Principal.modelo.reserva.getFechaSalida().toLocalDate();
			float precio = modelo().gestorDinero.calcularPrecioConDescuentos2(habitaciones, servicios, fecha1, fecha2);
			modelo().gestorDinero.setPrecio(precio);
			if(modelo().cliente != null) {
				idTabPago.setDisable(false);
				tabPane.getSelectionModel().select(idTabPago);
				Principal.aplicacion.controladorPago.initialize(null, null);
				Principal.aplicacion.controladorFactura.initialize(null, null);
			} else {
				aplicacion().CambiarScene("LoginRegistro.fxml");
				aplicacion().controladorLoginRegistro.setPantallaAnterior("Pasos.fxml");
				aplicacion().controladorLoginRegistro.setTabActiva(tabPane.getSelectionModel().getSelectedIndex());
				aplicacion().controladorLoginRegistro.setLoginForced(true);
			}
		}
    }
    
    /**
     * Controla los eventos que se dan cuando has terminado de pagar en
     * este caso insertar la reserva en las BBDD etc.
     */
    public void btnSiguientePago() {
    	if (modelo().gestorDinero.calcularDineroRestante() == 0) {
			
			// guarda la fecha de la compra
    		
			Timestamp fechaCompra = new Timestamp(System.currentTimeMillis());
			modelo().reserva.setFechaCompra(fechaCompra);
			
			// inserta la reserva en la base de datos
			int codReserva = modelo().gestorBBDD.insertarReserva(modelo().cliente, modelo().reserva, modelo().gestorDinero);
			for (Habitacion h: modelo().reserva.getHabitacionesSeleccionadas()) {
				modelo().gestorBBDD.modificarBBDD.insertarReservaHabitaciones(codReserva, h.getCodEstancia(), h.getCantidad());
			}
			
			// guarda el codigo de la reserva
	    	modelo().reserva.setCodReserva(codReserva);
	    	
	    	// actualiza y muestra la pantalla final
	    	anchorPaneBase.getChildren().remove(botones);
	    	idTabFin.setDisable(false);
	    	idTabHab.setDisable(true);
	    	idTabPago.setDisable(true);
	    	aplicacion().controladorFactura.actualizarDatos();
	    	tabPane.getSelectionModel().select(idTabFin);
	    	
    	} else {
    		aplicacion().mostrarMensaje(aplicacion().controladorPasos.anchorPaneBase, "Aun no ha introducido todo el dinero");
    	}
    }
    
    /**
     * Controla que se ha seleccionado al menos una habitación
     * @return
     */
    public boolean comprobarHabitacionSeleccionada() {
		boolean sigTab = true;
		ArrayList<Habitacion> habitacionesReservadas = modelo().reserva.getHabitacionesSeleccionadas();
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
    
    /**
     * 
     * @param index
     */
    public void selecionarTab(int index) {
    	if (tabPane.getTabs().get(index).isDisable()) {
    		tabPane.getTabs().get(index).setDisable(false);
    	}
    	tabPane.getSelectionModel().select(index);
    }

    /**
     * Comprueba si la sesión está iniciada
     */
    public void comprobarSesionIniciada() {
		if(modelo().cliente != null) {
			lblSaludo.setText("Hola, " + modelo().cliente.getUser());
			lblSesion.setText("Cerrar Sesion");
		} else {
			lblSaludo.setText("Hola, Anónimo");
			lblSesion.setText("Identifíquese");
		}
	}
    
    /**
     * 
     */
	public void AnimacionCama() {
		try{anchorPaneBase.getChildren().remove(iconoCama);} catch (Exception e) {}
		iconoCama.setSize("100");
		iconoCama.setLayoutX(300);
		iconoCama.setLayoutY(500);
		iconoCama.setFill(Paint.valueOf("#777777"));
		anchorPaneBase.getChildren().add(iconoCama);
		
		
		TranslateTransition animacionCama = new TranslateTransition();
		animacionCama.setFromX(0);
		animacionCama.setFromY(0);
		animacionCama.setToX(700);
		animacionCama.setToY(-400);
		animacionCama.setDuration(Duration.seconds(0.3F));
		animacionCama.setNode(iconoCama);
		
		TranslateTransition animacionCama2 = new TranslateTransition();
		animacionCama2.setToX(1000);
		animacionCama2.setDelay(Duration.seconds(0.3F));
		animacionCama2.setDuration(Duration.seconds(0.01F));
		animacionCama2.setNode(iconoCama);
		
		ParallelTransition transiciones = new ParallelTransition(animacionCama, animacionCama2);
		transiciones.play();
		
		
	}

}