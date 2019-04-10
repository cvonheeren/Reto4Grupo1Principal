package aplicacion;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Alojamiento;
import modelo.AlojamientoLista;

public class ListenerSeleccionAlojamiento implements Initializable{
	
	

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnSiguiente;

    @FXML
    private JFXTextField fieldBuscador;
    

    @FXML
    private FlowPane listaAlojamientos;
    
    @FXML
    void Cancelar(MouseEvent event) {
    	System.out.println("Btn cancelar");
    	Principal.aplicacion.CambiarScene("Bienvenida.fxml");
    }
    
    

    
    @FXML
    void buscar(ActionEvent event) {
    	System.out.println(fieldBuscador.getText());
    	ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		alojamientos = Principal.modelo.gestorBBDD.cargarListaAlojamientos(fieldBuscador.getText());
		
    }

    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Node newLoadedPane = null;
		Node newLoadedPane2 = null;
		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/vista/Alojamiento.fxml"));
			newLoadedPane2 = FXMLLoader.load(getClass().getResource("/vista/Alojamiento.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listaAlojamientos.getChildren().add(newLoadedPane);
		listaAlojamientos.getChildren().add(newLoadedPane2);
	}
	
	
    
}


