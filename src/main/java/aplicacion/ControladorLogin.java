package aplicacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Cliente;

public class ControladorLogin {
	
	@FXML
	private AnchorPane paneLogin;
	
    @FXML
    private JFXButton login, atras;

    @FXML
    private JFXTextField textFieldDNI;

    @FXML
    private JFXPasswordField contrasena;
    
    @FXML
	void atras(MouseEvent event) {
		Principal.aplicacion.CambiarScene("Pasos.fxml");
	}
    
    @FXML
	void resgistrar(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Registro.fxml");
    }
	
    @FXML
	boolean logear(MouseEvent event) {
		if (validarDatos()) {
    		return logearCliente();
    	}
    	return false;
	}
	
	boolean logearCliente() {
		String dni = textFieldDNI.getText();
		String pass = contrasena.getText();
		if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
			Principal.modelo.cliente = new Cliente(dni, pass);
			return true;
		} else {
			Principal.aplicacion.mostrarMensaje(paneLogin, "DNI y/o contraseña incorrectos.");
			return false;
		}
	}
	
	boolean validarDatos() {
    	if (textFieldDNI.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'DNI' vacio.");
    		return false;
    	}
    	if (contrasena.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'Contraseña' vacio.");
    		return false;
    	}
    	return true;
	 }
	
}
