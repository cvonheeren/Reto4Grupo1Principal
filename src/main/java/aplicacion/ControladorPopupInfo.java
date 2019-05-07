package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.Habitacion;

public class ControladorPopupInfo implements Initializable {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblUbicacion;

    @FXML
    private Label lblHabitaciones;
    
    @FXML
    private Label lblPrecioTotal;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		lblUbicacion.setText(Principal.modelo.reserva.getAlojamiento().getUbicacion());
		lblPrecioTotal.setText("Total: " + Principal.modelo.reserva.getPrecio() + "€");
		ArrayList<Habitacion> habitacionesReservadas=Principal.modelo.reserva.getHabitacionesReservadas();
		String habitacionesTexto = "";
		for(int i=0;i<habitacionesReservadas.size();i++)
			{
				lblHabitaciones.setPrefHeight(lblHabitaciones.getPrefHeight()+12);
				Habitacion habitacion=habitacionesReservadas.get(i);
				habitacionesTexto=habitacionesTexto+habitacion.getNombre() + ": " + habitacion.getCantidad() + "\n";
			}
		
		lblHabitaciones.setText(habitacionesTexto);
	}
	
    @FXML
    void cerrar(MouseEvent event) {
    	Principal.aplicacion.stagePopupInfo.close();
    }

}
