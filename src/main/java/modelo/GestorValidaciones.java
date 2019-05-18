package modelo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import aplicacion.ControladorLoginRegistro;
import core.Principal;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class GestorValidaciones {

	
	public GestorValidaciones() {
		
	}
	
	/**
	 * 
	 */
	public void limitarFechaMenorEdad(JFXDatePicker fechaNacReg) {
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
	
	/**
	 * 
	 * @return
	 */
	public boolean validarDatosLogin(JFXTextField textFieldDNI, AnchorPane paneLogin, JFXPasswordField contrasena) {
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
    
	/**
	 * 
	 * @return
	 */
    public boolean validarDatosRegistro(ControladorLoginRegistro gestor) {
    	if (gestor.textFieldUserName.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Nombre de usuario' vacio.");
    		return false;
    	}
    	if (gestor.textFieldUserName.getText().length() < 8) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Nombre de usuario' debe tener al menos 8 caracteres.");
    		return false;
    	}
    	if (gestor.textFieldDNIReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'DNI' vacio.");
    		return false;
    	}
    	if (!validarNif(gestor.textFieldDNIReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "El DNI está en un formato inválido.");
    		return false;
    	}
    	if (gestor.textFieldNombreReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Nombre' vacio.");
    		return false;
    	}
    	if (gestor.textFieldApellidoReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Apellido' vacio.");
    		return false;
    	}
    	if (gestor.contrasenaReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Contraseña' vacio.");
    		return false;
    	}
    	if (gestor.contrasenaRepReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Contraseña1' vacio.");
    		return false;
    	}
    	if (gestor.textFieldMailReg.getText().isEmpty()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo 'Email' vacio.");
    		return false;
    	}
    	if (gestor.fechaNacReg.getValue() == null) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Campo Fecha de Nacimiento vacio.");
    		return false;
    	}
    	if (gestor.fechaNacReg.getValue().isAfter(LocalDate.now().minusYears(18))) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "No puedes registrarte siendo menor de edad.");
    		return false;
    	}
    	if (Principal.modelo.gestorBBDD.comprobarDni(gestor.textFieldDNIReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "El DNI introducido ya existe.");
    		return false;
    	}
    	if (!validarPass(gestor.contrasenaReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "La contraseñas introducida debe tener al menos 8 caractéres, uno mayúscula, otro minúscula, un número y un caracter especial sin admitirse espacios.");
    		return false;
    	}
    	if (!gestor.contrasenaReg.getText().equals(gestor.contrasenaRepReg.getText())) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Las contraseñas introducidas no son correctas.");
    		return false;
    	}
    	if (!gestor.chkboxBases.isSelected()) {
    		Principal.aplicacion.mostrarMensaje(gestor.paneLogin, "Para continuar debe aceptar las bases legales.");
    		return false;
    	}
    	return true;
    }
    
    /**
     * Valida que el dni este en el formato correcto
     * @param nif
     * @return
     */
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
    
    /**
     * Valida que la pass este en el formato correcto
     * @param password
     * @return
     */
    public boolean validarPass(String password) {
		 String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,}";
	     if(password.matches(pattern))
	    	 return true;
	     else
	    	 return false;
    }
}
