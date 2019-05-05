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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import modelo.Cliente;

public class ControladorRegistro implements Initializable {
	
	@FXML
	private AnchorPane paneRegistro;
	
	@FXML
    private JFXButton login, atras;

    @FXML
    private JFXTextField textFieldDNI, textFieldNombre, textFieldApellido, textFieldMail;

    @FXML
    private JFXPasswordField contrasena, contrasenaRep;

    @FXML
    private JFXDatePicker fechaNac;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		limitarFecha();
	}

    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("Pasos.fxml");
    }

    @FXML
    void registrarse(MouseEvent event) {
    	if (validarDatos()) {
    		registrarCliente();
    	}
    }

	public void limitarFecha() {
		fechaNac.setValue(LocalDate.now().minusYears(18));
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
		 fechaNac.setDayCellFactory(dayCellFactory);
	}
    
    public void registrarCliente() {
    	int codCliente = Principal.modelo.gestorBBDD.insertarCliente(textFieldDNI.getText(), contrasena.getText(), textFieldNombre.getText(), textFieldApellido.getText(), Date.valueOf(fechaNac.getValue()), textFieldMail.getText());
		if (codCliente != -1) {
			Principal.modelo.cliente = new Cliente(textFieldDNI.getText(), contrasena.getText());
			Principal.aplicacion.mostrarMensaje(paneRegistro, "Usuario registrado correctamente");
			Principal.aplicacion.CambiarScene("Pasos.fxml");
		} else {
			Principal.aplicacion.mostrarMensaje(paneRegistro, "No se ha podido efectuar el registro.");
		}
    }
    
    public boolean validarDatos() {
    	if (textFieldDNI.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo 'DNI' vacio.");
    		return false;
    	}
    	if (!validarNif(textFieldDNI.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "El DNI está en un formato inválido.");
    		return false;
    	}
    	if (textFieldNombre.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo 'Nombre' vacio.");
    		return false;
    	}
    	if (textFieldApellido.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo 'Apellido' vacio.");
    		return false;
    	}
    	if (contrasena.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo 'Contraseña' vacio.");
    		return false;
    	}
    	if (contrasenaRep.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo 'Contraseña1' vacio.");
    		return false;
    	}
    	if (textFieldMail.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo 'Email' vacio.");
    		return false;
    	}
    	if (fechaNac.getValue() == null) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Campo Fecha de Nacimiento vacio.");
    		return false;
    	}
    	if (fechaNac.getValue().isAfter(LocalDate.now().minusYears(18))) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "No puedes registrarte siendo menor de edad.");
    		return false;
    	}
    	if (Principal.modelo.gestorBBDD.comprobarDni(textFieldDNI.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "El DNI introducido ya existe.");
    		return false;
    	}
    	if (!validarPass(contrasena.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "La contraseñas introducida debe tener al menos 8 caractéres, uno mayúscula, otro minúscula, un número y un caracter especial sin admitirse espacios.");
    		return false;
    	}
    	if (!contrasena.getText().equals(contrasenaRep.getText())) {
    		Principal.aplicacion.mostrarMensaje(paneRegistro, "Las contraseñas introducidas no son correctas.");
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
