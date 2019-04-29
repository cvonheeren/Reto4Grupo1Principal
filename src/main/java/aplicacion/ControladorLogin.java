package aplicacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;

public class ControladorLogin {

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton atras;

    @FXML
    private JFXTextField textFieldDNI;

    @FXML
    private Label textoAviso;

    @FXML
    private JFXPasswordField contrasena;

    
    
    @FXML
    void resgistrar(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Registro.fxml");
    }
    
    @FXML
	void atras(MouseEvent event) {
		Principal.aplicacion.CambiarScene("InfoReserva.fxml");
	}
	
	@FXML
	void logear(MouseEvent event) {
		String dni = textFieldDNI.getText();
		String pass = contrasena.getText();
		if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
			Principal.modelo.cliente = new Cliente(dni, pass);
			Principal.aplicacion.CambiarScene("Pago.fxml");
		} else {
			textoAviso.setOpacity(1.0);
		}
	}

}
