package aplicacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import Reto4Grupo1BBDD.ModificarBBDD;

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
    void atras(MouseEvent event) {

    }

    @FXML
    void registrarse(MouseEvent event) {
    	//if (contrasena.getText() == contrasena1.getText() && !Principal.modelo.gestorBBDD.comprobarCliente(textFieldDNI.getText(), contrasena.getText()))
    		//ModificarBBDD.insertarCliente(textFieldDNI.getText(), contrasena.getText(), "", "", "" , '2008-11-11', "");
    	
    }
	
}
