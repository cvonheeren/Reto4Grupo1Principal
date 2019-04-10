package modelo;

public class Cliente {

	protected String dni;
	protected String contrasenia;
	
	public Cliente(String dni, String contrasenia) {
		this.dni = dni;
		this.contrasenia = contrasenia;
	}

	/**
	 * Validamos que el cliente exista
	 * @return si el cliente existe
	 */
	public boolean validacion() {
		GestorBBDD gestor = new GestorBBDD();
		if (gestor.cargarCliente(dni) == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Validamos que la contrasenia esté bien
	 * @return
	 */
	public boolean validacionPas() {
		GestorBBDD gestor = new GestorBBDD();
		if (validacion() && gestor.enviarPassword(dni, contrasenia)) {
			return true;
		}
		else
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
