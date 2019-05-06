package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControladorPopupInfo implements Initializable {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblUbicacion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		lblUbicacion.setText(Principal.modelo.reserva.getAlojamiento().getUbicacion());
	}
	
    @FXML
    void cerrar(MouseEvent event) {
    	Principal.aplicacion.stagePopupInfo.close();
    }

}
