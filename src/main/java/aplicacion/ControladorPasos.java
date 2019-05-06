package aplicacion;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import modelo.Habitacion;
import modelo.Hotel;

public class ControladorPasos {
	
	@FXML
    private JFXTabPane tabPane;
	
	@FXML
    private Tab idTabInfo, idTabHab, idTabServ, idTabPago, idTabFin;
	
	@FXML
    private JFXButton btnLogin, btnSiguiente, btnAtras, btnInformacion, btnCancelar;
	
	@FXML
    void login(ActionEvent event) {
		Principal.aplicacion.CambiarScene("Login2.fxml");
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
			case "idTabInfo":
				if(Principal.modelo.reserva.getAlojamiento() instanceof Hotel) {
					tabPane.getSelectionModel().select(idTabHab);
				} else {
					tabPane.getSelectionModel().select(idTabServ);
				}
				break;	
				
			case "idTabHab":
				if(comprobarHabitacionSeleccionada())
					tabPane.getSelectionModel().select(idTabServ);
				break;
				
			case "idTabServ":
				if(!(Principal.modelo.cliente==null)) {
					tabPane.getSelectionModel().select(idTabPago);
				} else {
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

}
