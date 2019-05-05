package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import core.Principal;
import javafx.fxml.Initializable;

public class ControladorBienvenida implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	}

}