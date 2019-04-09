package aplicacion;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ListenerBienvenida {

    @FXML
    private JFXButton btnComenzar;

    

	@FXML
    void Comenzar(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
    }
	

}
