package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import modelo.Habitacion;

public class ControladorPopupInfo implements Initializable {
	
    @FXML
    private AnchorPane paneBase;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblUbicacion;
    
    @FXML
    private Label lblPrecioTotal;
    
    @FXML
    private FlowPane flowPane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(paneBase, 1);
		lblNombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		lblUbicacion.setText(Principal.modelo.reserva.getAlojamiento().getUbicacion());
		lblPrecioTotal.setText("Total: " + Principal.modelo.reserva.getPrecio() + "€");
		ArrayList<Habitacion> habitacionesReservadas=Principal.modelo.reserva.getHabitacionesReservadas();
		for(int i=0;i<habitacionesReservadas.size();i++)
			{
				Habitacion habitacion=habitacionesReservadas.get(i);
				Label textoHabitacion=new Label(habitacion.getNombre());
				textoHabitacion.setMinWidth(370);
				textoHabitacion.setStyle("-fx-font-weight: bold");
				flowPane.getChildren().add(textoHabitacion);
				
				
				Label textoCantHab = new Label("                 x" + habitacion.getCantidad());
				textoCantHab.setMinWidth(370);
				flowPane.getChildren().add(textoCantHab);
			}
		
	}
	
    @FXML
    void cerrar(MouseEvent event) {
    	Principal.aplicacion.stagePopupInfo.close();
    }
    
    @FXML
    void verMapa(MouseEvent event) {
    	Principal.aplicacion.verMapa("Mapa.fxml");
    }

}
