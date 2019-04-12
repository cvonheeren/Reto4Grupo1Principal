
package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("SeleccionAlojamiento2.fxml");
    }

    @FXML
    void siguiente(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Login.fxml");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cod.setText(Integer.toString(Principal.modelo.alojamiento.getCodAlojamiento()));
		tipo.setText("Hotel");
		nombre.setText(Principal.modelo.alojamiento.getNombre());
	}

}