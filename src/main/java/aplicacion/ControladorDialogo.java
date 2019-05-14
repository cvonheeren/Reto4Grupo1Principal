package aplicacion;


import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class ControladorDialogo implements Initializable{

    @FXML
    private AnchorPane paneBase;
	
	@FXML
    private Label texto;

    @FXML
    void aceptar(ActionEvent event) {
    	Principal.aplicacion.stageDialogo.close();
    }
    
    @FXML
    void cancelar(ActionEvent event) {
    	Principal.aplicacion.stageDialogo.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(paneBase, 2);
		texto.setText(Principal.aplicacion.textoTemporal);
	}
	

}
