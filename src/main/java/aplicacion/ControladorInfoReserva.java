
package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import modelo.Habitacion;

public class ControladorInfoReserva implements Initializable {

    @FXML
    private Label cod;

    @FXML
    private Label tipo;

    @FXML
    private Label nombre;

    @FXML
    private Label fechaEntrada;

    @FXML
    private Label fechaSalida;

    @FXML
    private Label adultos;

    @FXML
    private Label ninos;

    @FXML
    private Label habitaciones;

    @FXML
    private Label precio;

    @FXML
    private JFXButton siguiente;

    @FXML
    private JFXButton atras;

    @FXML
    private WebView mapa;
    
    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("SeleccionHabitacion.fxml");
    }

    @FXML
    void siguiente(MouseEvent event) {
    	if (Principal.modelo.cliente == null) {
    		Principal.aplicacion.CambiarScene("Login.fxml");
    	} else {
    		Principal.aplicacion.CambiarScene("Pago.fxml");	
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void ActualizarDatos()
	{
		mapa.getEngine().loadContent("<iframe src=\"https://maps.google.com/maps?q=" + Principal.modelo.reserva.getAlojamiento().getLongitud() + "," + Principal.modelo.reserva.getAlojamiento().getLatitud() + "&hl=es;z=14&amp;output=embed\" width=\"350\" height=\"250\" frameborder=\"0\" style=\"border:0\"></iframe>", "text/html");
		cod.setText(Integer.toString(Principal.modelo.reserva.getAlojamiento().getCodAlojamiento()));
		tipo.setText("Hotel"); // CAMBIAR!!
		nombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		precio.setText(Principal.modelo.reserva.getAlojamiento().getTarifaNormal() + "€");
		fechaEntrada.setText(Principal.modelo.reserva.getFechaEntrada().toLocalDate().toString());
		fechaSalida.setText(Principal.modelo.reserva.getFechaSalida().toLocalDate().toString());
		String str = "";
		for (Habitacion h: Principal.modelo.reserva.getHabitacionesReservadas()) {
		    str += h.getNombre() + " x " + h.getCantidad() + "\n";
		}
		habitaciones.setText(str);
		precio.setText(Float.toString(Principal.modelo.pago.getPrecioTotal()));
	}

}