package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import modelo.Habitacion;
import modelo.Hotel;

public class ControladorPasos implements Initializable {
	
	@FXML
    public JFXTabPane tabPane;
	
	@FXML
    public Tab idTabInfo, idTabHab, idTabServ, idTabPago, idTabFin;
	
	@FXML
	public Hyperlink lblSesion;
	
	@FXML
	public Label lblSaludo;
	
    @FXML
    private FontAwesomeIconView iconInfo;
	
	
	@FXML
    private JFXButton btnLogin, btnSiguiente, btnAtras, btnInformacion, btnCancelar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Principal.modelo.cliente!=null)
		{
			SesionIniciada();
		}
		
		if(Principal.modelo.reserva.getAlojamiento() instanceof Hotel) {
			tabPane.getSelectionModel().select(idTabHab);
		} else {
			tabPane.getSelectionModel().select(idTabServ);
			tabPane.getTabs().remove(idTabHab);
		}
	}
	
	@FXML
    void IniciarCerrar(ActionEvent event) {
		Principal.aplicacion.controladorPasos=this;
		if(Principal.modelo.cliente==null)
		{
			Principal.aplicacion.CargarSceneLogin();
		}
		else
		{
			Principal.iniciarPrograma();
		}
		
    }
	
	@FXML
    void cancelar(ActionEvent event) {
    	Principal.iniciarPrograma();
    }

    @FXML
    void informacion(ActionEvent event) {
    	tabPane.getSelectionModel().select(idTabInfo);	
    }

    @FXML
    void siguiente(ActionEvent event) {
		switch(tabPane.getSelectionModel().getSelectedItem().getId()) {	
				
			case "idTabHab":
				if(comprobarHabitacionSeleccionada())
					tabPane.getSelectionModel().select(idTabServ);
				break;
				
			case "idTabServ":
				if(!(Principal.modelo.cliente==null)) {
					tabPane.getSelectionModel().select(idTabPago);
				} else {
					Principal.aplicacion.controladorPasos=this;
					Principal.aplicacion.CargarSceneLogin();
				}
				break;
				
			case "idTabPago":
				tabPane.getSelectionModel().select(idTabFin);
				break;
				
			case "idTabFin":
				Principal.iniciarPrograma();
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
    void mostrarInfo(MouseEvent event)
    {
    	Principal.aplicacion.CargarpopupInfo(iconInfo.localToScreen(iconInfo.getBoundsInLocal()));
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
    
    public void SelecionarTab(Tab tab)
    {
    	tabPane.getSelectionModel().select(tab);
    }

	public void SesionIniciada() {

		lblSaludo.setText("Hola, " + Principal.modelo.cliente.getDni());
		lblSesion.setText("Cerrar Sesion");
			
	}

}
