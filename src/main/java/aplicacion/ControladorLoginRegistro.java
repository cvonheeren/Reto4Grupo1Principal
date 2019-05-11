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
    
    private String pantallaAnterior;
	private int tabGuardada;
	private boolean loginForced;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabGuardada = -1;
		loginForced = false;
		Principal.aplicacion.controladorLoginRegistro = this;
		JFXDepthManager.setDepth(paneReg, 2);
		Principal.modelo.gestorValidaciones.limitarFechaMenorEdad(fechaNacReg);
	}
    
    @FXML
    void volver(ActionEvent event) {
    	Principal.aplicacion.CambiarScene(pantallaAnterior);
		if (tabGuardada != -1) {
			Principal.aplicacion.controladorPasos.selecionarTab(tabGuardada);
		}
    }

    @FXML
	void logear(ActionEvent event) {
    	if (Principal.modelo.gestorValidaciones.validarDatosLogin(textFieldDNI, paneLogin, contrasena)) {
			String dni = textFieldDNI.getText();
			String pass = contrasena.getText();
			if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
				Principal.modelo.cliente = new Cliente(dni, pass);
				Principal.modelo.reserva.setCliente(new Cliente(dni, pass));
				Principal.aplicacion.CambiarScene(pantallaAnterior);
				if (tabGuardada != -1) {
					if (loginForced) {
						tabGuardada++;
					}
					Principal.aplicacion.controladorPasos.selecionarTab(tabGuardada);
				}
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
    			Principal.aplicacion.CambiarScene(pantallaAnterior);
    			if (tabGuardada != -1) {
    				if (loginForced) {
						tabGuardada++;
					}
    				Principal.aplicacion.controladorPasos.selecionarTab(tabGuardada);
    			}
    		} else {
    			Principal.aplicacion.mostrarMensaje(paneLogin, "No se ha podido efectuar el registro.");
    		}
    	}
    }
    
    @FXML
    void verBases(MouseEvent event) {
    	Principal.aplicacion.verBases();
    }
    
    public void setPantallaAnterior(String nombrePantalla) {
    	this.pantallaAnterior = nombrePantalla;
    }
    
	public void setTabActiva(int nombreTab) {
		this.tabGuardada = nombreTab;
	}
	
	public void setLoginForced(boolean forced) {
		this.loginForced = forced;
	}
   
}