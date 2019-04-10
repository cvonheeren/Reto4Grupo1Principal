package aplicacion;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;
import javafx.fxml.FXML;
import core.Principal;

public class ListenerLogin  {

	@FXML
	private JFXTextField textareaDni;
	
	@FXML
	private JFXTextField textareaPass;
	
	@FXML
	private JFXTextArea textoAviso;
	

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
