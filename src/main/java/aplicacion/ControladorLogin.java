package aplicacion;


import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.input.MouseEvent;
import modelo.*;
import javafx.fxml.FXML;
import core.Principal;

public class ControladorLogin  {

	@FXML
	private JFXTextArea textareaDni;
	
	@FXML
	private JFXTextArea textareaPass;
	
	@FXML
	private JFXTextField textoAviso;
	

	@FXML
	void Atras(MouseEvent event) {
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	}
	
	@FXML
	void Logear(MouseEvent event) {
		String dni = textareaDni.getText();
		String pass = textareaPass.getText();
		
		if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
			Principal.modelo.cliente = new Cliente(dni, pass);
			Principal.aplicacion.CambiarScene("Bienvenida.fxml");
		} else {
			textoAviso.setOpacity(1.0);
		}
	}
}
