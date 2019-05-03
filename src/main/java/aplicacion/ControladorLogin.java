package aplicacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;

public class ControladorLogin {
   
	private Label textoAviso;
	private JFXTextField textFieldDNI;
	private JFXPasswordField contrasena;
	private Hyperlink linkRegistro;
	private ControladorPasos controladorPasos;

	
	public ControladorLogin(Label textoAviso, JFXTextField textFieldDNI, JFXPasswordField contrasena, ControladorPasos controladorPasos) {
		super();
		this.textoAviso = textoAviso;
		this.textFieldDNI = textFieldDNI;
		this.contrasena = contrasena;
		this.linkRegistro = linkRegistro;
		this.controladorPasos = controladorPasos;
	}

	void resgistrar(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Registro.fxml");
    }
    
	
	boolean logear() {
		if (validarDatos()) {
    		return logearCliente();
    	} else {
    		//textoAviso.setOpacity(1);
    		return false;
    	}
	}
	
	boolean logearCliente() {
		String dni = textFieldDNI.getText();
		String pass = contrasena.getText();
		if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
			Principal.modelo.cliente = new Cliente(dni, pass);
			return true;
		} else {
			controladorPasos.MostrarMensaje("DNI y/o contraseña incorrectos.");
			return false;
		}
	}
	
	boolean validarDatos() {
    	if (textFieldDNI.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'DNI' vacio.");
    		return false;
    	}
    	if (contrasena.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'Contraseña' vacio.");
    		return false;
    	}
    	return true;
	 }

}
