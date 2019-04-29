package aplicacion;

import java.sql.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControladorRegistro {

	@FXML
    private JFXButton login;

    @FXML
    private JFXButton atras;

    @FXML
    private JFXTextField textFieldDNI;

    @FXML
    private JFXPasswordField contrasena;

    @FXML
    private JFXPasswordField contrasena1;

    @FXML
    private JFXTextField textFieldMail;

    @FXML
    private JFXTextField textFieldFechaNac;

    @FXML
    private JFXTextField textFieldNombre;

    @FXML
    private JFXTextField textFieldApellido;
    
    @FXML
    private Label labelError;

    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Login.fxml");
    }

    @FXML
    void registrarse(MouseEvent event) {
    	if (comprobarDni() && comprobarContras()) {
    		Principal.modelo.gestorBBDD.modificarBBDD.insertarCliente(textFieldDNI.getText(), contrasena.getText(), textFieldNombre.getText(), textFieldApellido.getText(), new Date(2, 2, 2), textFieldMail.getText());
    		Principal.aplicacion.CambiarScene("Pago.fxml");
    	}
    	else
    		labelError.setOpacity(1);
    }
    
    @FXML
    boolean comprobarDni() {
    	if (!Principal.modelo.gestorBBDD.comprobarDni(textFieldDNI.getText()))
    		return true;
    	else
    		return false;
    }
    
    @FXML
    boolean comprobarContras() {
    	if (contrasena.getText() == contrasena1.getText())
    		return true;
    	else
    		return false;
    }
	
}
