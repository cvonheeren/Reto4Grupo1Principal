package modelo;

import java.sql.Date;

public class Cliente {
	
	private int codCliente;
	private String username;
	private String dni;
	private String contrasenia;
	private String nombre;
	private String apellidos;
	private Date fechaNac;
	private String email;
	
	/**
	 * Crea un objeto Cliente con el codigo de cliente, el username y la contraseña de un usuario.
	 * @param codCliente
	 * @param username
	 * @param contrasenia
	 */
	public Cliente(Integer codCliente, String username, String contrasenia) {
		this.username = username;
		this.contrasenia = contrasenia;
		this.codCliente = codCliente;
	}
	
	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}
	
}
