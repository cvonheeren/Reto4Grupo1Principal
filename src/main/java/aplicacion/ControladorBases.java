package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControladorBases implements Initializable {

    @FXML
    private Label texto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		texto.setText(Principal.modelo.LeerTexto(System.getProperty("user.dir") + "\\BasesLegales.txt"));
		
	}
    
    

}