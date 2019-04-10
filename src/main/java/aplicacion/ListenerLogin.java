package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import core.Principal;

public class ListenerLogin implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@FXML
	void Atras(MouseEvent event) {
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	}
	
}
