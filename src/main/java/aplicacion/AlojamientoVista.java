package aplicacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modelo.Alojamiento;

public class AlojamientoVista implements Initializable {

    @FXML
    public Label lblNombre;

    @FXML
    public Label lblPrecio;

    @FXML
    public Label lblUbicacion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
	}

}

