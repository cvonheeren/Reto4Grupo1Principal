package aplicacion;

import java.sql.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;

public class ControladorRegistro {

	@FXML
    private JFXButton login;

    @FXML
    private JFXButton atras;

    @FXML
    private JFXTextField textFieldDNI;
    
    @FXML
    private JFXTextField textFieldNombre;

    @FXML
    private JFXTextField textFieldApellido;

    @FXML
    private JFXPasswordField contrasena;

    @FXML
    private JFXPasswordField contrasena1;

    @FXML
    private JFXTextField textFieldMail;

    @FXML
    private JFXDatePicker fechaNac;
    
    @FXML
    private Label labelError;

    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Login.fxml");
    }

    @FXML
    void registrarse(MouseEvent event) {
    	if (validarDatos()) {
    		registrarCliente();
    	}
    	else
    		labelError.setOpacity(1);
    }
    
    void registrarCliente() {
    	int codCliente = Principal.modelo.gestorBBDD.insertarCliente(textFieldDNI.getText(), contrasena.getText(), textFieldNombre.getText(), textFieldApellido.getText(), Date.valueOf(fechaNac.getValue()), textFieldMail.getText());
		if (codCliente != -1) {
			Principal.modelo.cliente = new Cliente(textFieldDNI.getText(), contrasena.getText());
			Principal.aplicacion.CambiarScene("Pago.fxml");
		} else {
			labelError.setOpacity(1);
			labelError.setText("No se ha podido efectuar el registro.");
		}
    }
    
    @FXML
    boolean comprobarDni() {
    	if (Principal.modelo.gestorBBDD.comprobarDni(textFieldDNI.getText()))
    		return false;
    	else
    		return true;
    }
    
    @FXML
    boolean comprobarContras() {
    	if (contrasena.getText().equals(contrasena1.getText()))
    		return true;
    	else
    		return false;
    }
    
    boolean validarDatos() {
    	if (textFieldDNI.getText().isEmpty()) {
    		labelError.setText("Campo 'DNI' vacio.");
    		return false;
    	}
    	if (textFieldNombre.getText().isEmpty()) {
    		labelError.setText("Campo 'Nombre' vacio.");
    		return false;
    	}
    	if (textFieldApellido.getText().isEmpty()) {
    		labelError.setText("Campo 'Apellido' vacio.");
    		return false;
    	}
    	if (contrasena.getText().isEmpty()) {
    		labelError.setText("Campo 'Contraseña' vacio.");
    		return false;
    	}
    	if (contrasena1.getText().isEmpty()) {
    		labelError.setText("Campo 'Contraseña1' vacio.");
    		return false;
    	}
    	if (textFieldMail.getText().isEmpty()) {
    		labelError.setText("Campo 'Email' vacio.");
    		return false;
    	}
    	if (fechaNac.getValue() == null) {
    		labelError.setText("Campo Fecha de Nacimiento vacio.");
    		return false;
    	}
    	if (!comprobarDni()) {
    		labelError.setText("El DNI introducido ya existe.");
    		return false;
    	}
    	if (!comprobarContras()) {
    		labelError.setText("Las contraseñas introducidas no son correctas.");
    		return false;
    	}
    	return true;
    }
	
}
