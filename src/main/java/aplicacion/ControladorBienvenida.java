package aplicacion;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControladorBienvenida {

    @FXML
    private JFXButton btnComenzar;

	@FXML
    void Comenzar(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("SeleccionAlojamiento2.fxml");
    }

}
