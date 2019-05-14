package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import modelo.Habitacion;

public class ControladorPopupInfo implements Initializable {
	
    @FXML
    private AnchorPane paneBase;

    @FXML
    private Label lblNombre, lblPrecioTotal, lblFechas;
    
    @FXML
    private Text lblUbicacion;
    
    @FXML
    private Hyperlink mapa;
    
    @FXML
    private FlowPane flowPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(paneBase, 1);
		ArrayList<Habitacion> habitacionesReservadas = Principal.modelo.reserva.getHabitacionesReservadas();
		lblNombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		lblUbicacion.setText(Principal.modelo.reserva.getAlojamiento().getUbicacion());
		lblPrecioTotal.setText("Total: " + Principal.modelo.pago.getPrecioTotal(habitacionesReservadas, Principal.modelo.pago.getDescuento()) + "€");
		mapa.setLayoutX((int)(lblUbicacion.getBoundsInLocal().getMaxX() + lblUbicacion.getLayoutX() + 7));
		lblFechas.setText("Del " + Principal.modelo.reserva.getFechaEntrada() + " al " + Principal.modelo.reserva.getFechaSalida());
		
		for(int i=0;i<habitacionesReservadas.size();i++) {
			Habitacion habitacion = habitacionesReservadas.get(i);
			Label textoHabitacion = new Label(habitacion.getNombre() + " x " + habitacion.getCantidad());
			textoHabitacion.setMinWidth(370);
			textoHabitacion.setStyle("-fx-font-weight: bold");
			flowPane.getChildren().add(textoHabitacion);
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
