package modelo;

public class Cliente {

	protected String dni;
	protected String contrasenia;
	
	
	public Cliente(String dni, String contrasenia) {
		this.dni = dni;
		this.contrasenia = contrasenia;
	}

	public boolean validacion() {
		
		
		
		return false;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
}