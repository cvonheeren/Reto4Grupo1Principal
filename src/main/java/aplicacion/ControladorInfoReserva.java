
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
    	Principal.aplicacion.CambiarScene("Login.fxml");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mapa.getEngine().loadContent("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2904.936506675277!2d-2.984688684119379!3d43.27370597913605!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zNDPCsDE2JzI1LjMiTiAywrA1OCc1Ny4wIlc!5e0!3m2!1ses!2ses!4v1555066530582!5m2!1ses!2ses\" width=\"350\" height=\"250\" frameborder=\"0\" style=\"border:0\"></iframe>", "text/html");
		cod.setText(Integer.toString(Principal.modelo.alojamiento.getCodAlojamiento()));
		tipo.setText("Hotel");
		nombre.setText(Principal.modelo.alojamiento.getNombre());
	}

}