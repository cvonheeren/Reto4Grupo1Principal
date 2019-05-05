package aplicacion;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import modelo.Cliente;

public class ControladorLogin2 implements Initializable {

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private JFXTextField textFieldDNIReg;

    @FXML
    private JFXTextField textFieldNombreReg;

    @FXML
    private JFXTextField textFieldApellidoReg;

    @FXML
    private JFXPasswordField contrasenaReg;

    @FXML
    private JFXPasswordField contrasenaRepReg;

    @FXML
    private JFXTextField textFieldMailReg;

    @FXML
    private JFXDatePicker fechaNacReg;

    @FXML
    private JFXTextField textFieldDNI;

    @FXML
    private JFXPasswordField contrasena;

    @FXML
    private JFXButton btnLogin;
    
    @FXML
    private JFXButton btnReg;
    
    @FXML
    private JFXButton btnVolver;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		limitarFecha();
	}
    
    @FXML
    void volver(ActionEvent event) {
    	Principal.aplicacion.CambiarScene("Pasos.fxml");
    }

    @FXML
	void logear(ActionEvent event) {
		if (validarDatosLogin()) {
			String dni = textFieldDNI.getText();
			String pass = contrasena.getText();
			if(Principal.modelo.gestorBBDD.comprobarCliente(dni, pass)) {
				Principal.modelo.cliente = new Cliente(dni, pass);
				Principal.aplicacion.CambiarScene("Pasos.fxml");
				//Principal.aplicacion.mostrarMensaje(paneLogin, "Usuario logeado correctamente");
			} else {
				Principal.aplicacion.mostrarMensaje(paneLogin, "DNI y/o contraseña incorrectos.");
			}
    	}
	}

    @FXML
    void registrarse(ActionEvent event) {
    	if (validarDatosRegistro()) {
    		int codCliente = Principal.modelo.gestorBBDD.insertarCliente(textFieldDNIReg.getText(), contrasenaReg.getText(), textFieldNombreReg.getText(), textFieldApellidoReg.getText(), Date.valueOf(fechaNacReg.getValue()), textFieldMailReg.getText());
    		if (codCliente != -1) {
    			Principal.modelo.cliente = new Cliente(textFieldDNIReg.getText(), contrasenaReg.getText());
    			Principal.aplicacion.CambiarScene("Pasos.fxml");
    			//Principal.aplicacion.mostrarMensaje(paneLogin, "Usuario registrado correctamente");
    		} else {
    			Principal.aplicacion.mostrarMensaje(paneLogin, "No se ha podido efectuar el registro.");
    		}
    	}
    }

	public void limitarFecha() {
		fechaNacReg.setValue(LocalDate.now().minusYears(18));
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override
		             public void updateItem(LocalDate date, boolean empty) {
		                 super.updateItem(date, empty);
		                 LocalDate today = LocalDate.now().minusYears(18);
		                 setDisable(empty || date.compareTo(today) > 0 );
		             }
		         };
		     }
		 };
		 fechaNacReg.setDayCellFactory(dayCellFactory);
	}
	
	boolean validarDatosLogin() {
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
    
    public boolean validarDatosRegistro() {
    	if (textFieldDNIReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'DNI' vacio.");
    		return false;
    	}
    	if (!validarNif(textFieldDNIReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "El DNI está en un formato inválido.");
    		return false;
    	}
    	if (textFieldNombreReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'Nombre' vacio.");
    		return false;
    	}
    	if (textFieldApellidoReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'Apellido' vacio.");
    		return false;
    	}
    	if (contrasenaReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'Contraseña' vacio.");
    		return false;
    	}
    	if (contrasenaRepReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'Contraseña1' vacio.");
    		return false;
    	}
    	if (textFieldMailReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo 'Email' vacio.");
    		return false;
    	}
    	if (fechaNacReg.getValue() == null) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Campo Fecha de Nacimiento vacio.");
    		return false;
    	}
    	if (fechaNacReg.getValue().isAfter(LocalDate.now().minusYears(18))) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "No puedes registrarte siendo menor de edad.");
    		return false;
    	}
    	if (Principal.modelo.gestorBBDD.comprobarDni(textFieldDNIReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "El DNI introducido ya existe.");
    		return false;
    	}
    	if (!validarPass(contrasenaReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "La contraseñas introducida debe tener al menos 8 caractéres, uno mayúscula, otro minúscula, un número y un caracter especial sin admitirse espacios.");
    		return false;
    	}
    	if (!contrasenaReg.getText().equals(contrasenaRepReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneLogin, "Las contraseñas introducidas no son correctas.");
    		return false;
    	}
    	return true;
    }
	
    public boolean validarNif(String nif) {
        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);
        if(matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index%23;
            String reference = letras.substring(index,index+1);
            
            if(reference.equalsIgnoreCase(letra)) {
            	correcto = true;
            } else {
            	correcto = false;
            }
        } else {
        	correcto = false;
        }
        return correcto;
    }
    
    public boolean validarPass(String password) {
		 String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,}";
	     if(password.matches(pattern))
	    	 return true;
	     else
	    	 return false;
    }

}