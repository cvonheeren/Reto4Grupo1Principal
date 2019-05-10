package aplicacion;

import java.net.URL;
import java.sql.Date;

import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import modelo.Cliente;

public class ControladorLoginRegistro implements Initializable {

    @FXML
    public AnchorPane paneLogin;

    @FXML
    public JFXTextField textFieldDNIReg;

    @FXML
    public JFXTextField textFieldNombreReg;

    @FXML
    public JFXTextField textFieldApellidoReg;

    @FXML
    public JFXPasswordField contrasenaReg;

    @FXML
    public JFXPasswordField contrasenaRepReg;

    @FXML
    public JFXTextField textFieldMailReg;

    @FXML
    public JFXDatePicker fechaNacReg;

    @FXML
    public JFXTextField textFieldDNI;

    @FXML
    public JFXPasswordField contrasena;

    @FXML
    public JFXButton btnLogin;
    
    @FXML
    public JFXButton btnReg;
    
    @FXML
    public JFXButton btnVolver;
    
    @FXML
    public AnchorPane paneReg;
    
    @FXML
    public JFXCheckBox chkboxBases;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Principal.aplicacion.controladorLoginRegistro=this;
		JFXDepthManager.setDepth(paneReg, 2);
		Principal.modelo.gestorValidaciones.limitarFechaMenorEdad(fechaNacReg);
	}
    
    @FXML
    void volver(ActionEvent event) {
    	Principal.aplicacion.stageLogin.close();
    }

    @FXML
	void logear(ActionEvent event) {
		
    	
    	if (Principal.modelo.gestorValidaciones.validarDatosLogin(textFieldDNI, paneLogin, contrasena)) {
			String dni = textFieldDNI.getText();
			String pass = contrasena.getText();
			if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
				Principal.modelo.cliente = new Cliente(dni, pass);
				Principal.modelo.reserva.setCliente(new Cliente(dni, pass));
				try {Principal.aplicacion.controladorPasos.SesionIniciada();}catch(Exception e) {}
				try {Principal.aplicacion.controladorSelAlojamiento.SesionIniciada();}catch(Exception e) {}
				try {Principal.aplicacion.controladorInformacionAloj.SesionIniciada();}catch(Exception e) {}
				Principal.aplicacion.stageLogin.close();
			} else {
				Principal.aplicacion.mostrarMensaje(paneLogin, "DNI y/o contraseña incorrectos.");
			}
    	}
	}

    @FXML
    void registrarse(ActionEvent event) {
    	
    	if (Principal.modelo.gestorValidaciones.validarDatosRegistro(this)) {
    		int codCliente = Principal.modelo.gestorBBDD.insertarCliente(textFieldDNIReg.getText(), contrasenaReg.getText(), textFieldNombreReg.getText(), textFieldApellidoReg.getText(), Date.valueOf(fechaNacReg.getValue()), textFieldMailReg.getText());
    		if (codCliente != -1) {
    			Principal.modelo.cliente = new Cliente(textFieldDNIReg.getText(), contrasenaReg.getText());
    			Principal.modelo.reserva.setCliente(new Cliente(textFieldDNIReg.getText(), contrasenaReg.getText()));
    			Principal.aplicacion.stageLogin.close();
    			Principal.aplicacion.controladorSelAlojamiento.SesionIniciada();
    		} else {
    			Principal.aplicacion.mostrarMensaje(paneLogin, "No se ha podido efectuar el registro.");
    		}
    	}
    }
    
    @FXML
    void verBases(MouseEvent event) {
    	System.out.println("bases");
    	Principal.aplicacion.VerBases();
    }
}