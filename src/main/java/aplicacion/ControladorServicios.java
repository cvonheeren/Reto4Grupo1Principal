package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Alojamiento;

public class ControladorServicios implements Initializable {
    
	@FXML
	private AnchorPane paneServicios, main;
	
	@FXML
	private Pane panePrincipal;
	
	@FXML
	private Label titulo;
	
	@FXML
	private JFXCheckBox reservarTodo;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
	}
    
    @FXML
    void reservarTodo(ActionEvent event) {
    		
    }
    
    public void cargarServicios(Alojamiento alojamiento) {
    	
    	
    }

}