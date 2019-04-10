package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import modelo.Cliente;
import javafx.fxml.FXML;
import core.Principal;

public class ListenerLogin implements Initializable {

	@FXML
	private JFXTextField textareaDni;
	
	@FXML
	private JFXTextField textareaPass;
	
	@FXML
	private JFXTextArea textoAviso;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@FXML
	void Atras(MouseEvent event) {
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	}
	
	@FXML
	void Logear(MouseEvent event) {
		String dni = textareaDni.getText();
		String pass = textareaPass.getText();
		
		Cliente cliente = new Cliente(dni, pass);
		if(cliente.validacion())
			Principal.aplicacion.CambiarScene("");
		else
			textoAviso.setOpacity(1.0);
	}
	
	
}
