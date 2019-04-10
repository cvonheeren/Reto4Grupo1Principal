package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import modelo.Alojamiento;

public class ListenerSeleccionAlojamiento implements Initializable{

    @FXML
    private JFXComboBox<Alojamiento> comboListaAlojamientos;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnSiguiente;

    @FXML
    private JFXComboBox<String> comboBoxDestinos;
   
    @FXML
    private JFXTextField fieldBuscador;
    
    @FXML
    void Cancelar(MouseEvent event) {
    	System.out.println("Btn cancelar");
    	Principal.aplicacion.CambiarScene("Bienvenida.fxml");
    }
    
    @FXML
    void ciudadSeleccionada(ActionEvent event) {
    	comboListaAlojamientos.setDisable(false);
    	comboListaAlojamientos.getItems().clear();
    	RellenarListaAlojamientos();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RellenarListaDestinos();		
	}
	
	public void RellenarListaDestinos()
	{
		ArrayList<String> ciudades = new ArrayList<String>();
		ciudades=Principal.modelo.alojamientoLista.CargarListaDestinos();
		comboBoxDestinos.getItems().setAll(ciudades);
		
	}
	
	public void RellenarListaAlojamientos()
	{
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		alojamientos=Principal.modelo.alojamientoLista.CargarListaAlojamientos(comboBoxDestinos.getValue());
		comboListaAlojamientos.getItems().setAll(alojamientos);
	}
    
    @FXML
    void buscar(ActionEvent event) {
    	System.out.println(fieldBuscador.getText());
    	ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		alojamientos=Principal.modelo.alojamientoLista.CargarListaAlojamientos(fieldBuscador.getText());
		comboListaAlojamientos.getItems().setAll(alojamientos);
    }
    
    
}


