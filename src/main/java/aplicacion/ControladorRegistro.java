package aplicacion;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;

import com.jfoenix.controls.JFXTextField;

import core.Principal;

import modelo.Cliente;

public class ControladorRegistro {

	private JFXTextField textFieldDNIReg, textFieldNombreReg, textFieldApellidoReg, textFieldMailReg;
	private JFXPasswordField contrasenaReg, contrasenaRegRep;
	private JFXDatePicker fechaNacReg;
	private JFXButton btnReg;
	private ControladorPasos controladorPasos;

	public ControladorRegistro(JFXTextField textFieldDNIReg, JFXTextField textFieldNombreReg,
			JFXTextField textFieldApellidoReg, JFXTextField textFieldMailReg, JFXPasswordField contrasenaReg,
			JFXPasswordField contrasenaRegRep, JFXDatePicker fechaNacReg, JFXButton btnReg, ControladorPasos controladorPasos) {
		super();
		this.textFieldDNIReg = textFieldDNIReg;
		this.textFieldNombreReg = textFieldNombreReg;
		this.textFieldApellidoReg = textFieldApellidoReg;
		this.textFieldMailReg = textFieldMailReg;
		this.contrasenaReg = contrasenaReg;
		this.contrasenaRegRep = contrasenaRegRep;
		this.fechaNacReg = fechaNacReg;
		this.btnReg = btnReg;
		this.controladorPasos = controladorPasos;
	}


    void registrarse() {
    	if (validarDatos()) {
    		registrarCliente();
    	}
    	//else
    		//labelError.setOpacity(1);
    }
    
    void registrarCliente() {
    	int codCliente = Principal.modelo.gestorBBDD.insertarCliente(textFieldDNIReg.getText(), contrasenaReg.getText(), textFieldNombreReg.getText(), textFieldApellidoReg.getText(), Date.valueOf(fechaNacReg.getValue()), textFieldMailReg.getText());
		if (codCliente != -1) {
			Principal.modelo.cliente = new Cliente(textFieldDNIReg.getText(), contrasenaReg.getText());
			controladorPasos.MostrarMensaje("Usuario registrado correctamente");
			//Principal.aplicacion.CambiarScene("Pago.fxml");
		} else {
			controladorPasos.MostrarMensaje("No se ha podido efectuar el registro.");
		}
    }
    
    boolean validarDatos() {
    	if (textFieldDNIReg.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'DNI' vacio.");
    		return false;
    	}
    	if (textFieldNombreReg.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'Nombre' vacio.");
    		return false;
    	}
    	if (textFieldApellidoReg.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'Apellido' vacio.");
    		return false;
    	}
    	if (contrasenaReg.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'Contraseña' vacio.");
    		return false;
    	}
    	if (contrasenaRegRep.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'Contraseña1' vacio.");
    		return false;
    	}
    	if (textFieldMailReg.getText().isEmpty()) {
    		controladorPasos.MostrarMensaje("Campo 'Email' vacio.");
    		return false;
    	}
    	if (fechaNacReg.getValue() == null) {
    		controladorPasos.MostrarMensaje("Campo Fecha de Nacimiento vacio.");
    		return false;
    	}
    	if (Principal.modelo.gestorBBDD.comprobarDni(textFieldDNIReg.getText())) {
    		controladorPasos.MostrarMensaje("El DNI introducido ya existe.");
    		return false;
    	}
    	if (!contrasenaReg.getText().equals(contrasenaRegRep.getText())) {
    		controladorPasos.MostrarMensaje("Las contraseñas introducidas no son correctas.");
    		return false;
    	}
    	if (!validarNif(textFieldDNIReg.getText())) {
    		controladorPasos.MostrarMensaje("El DNI está en un formato inválido.");
    		return false;
    	}
    	
    	return true;
    }
	
    public boolean validarNif(String nif){
        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);

        if(matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index%23;
            String reference = letras.substring(index,index+1);
            
            if(reference.equalsIgnoreCase(letra))
                correcto = true;
            else 
                correcto = false;
        } 
        else 
            correcto = false;

        return correcto;
    }
}
