package modelo;

import java.sql.Date;

public class Cliente {
	
	private int codCliente;
	private String dni;
	private String contrasenia;
	private String nombre;
	private String apellidos;
	private Date fechaNac;
	private String email;
	
	public Cliente(int codCliente, String dni, String contrasenia, String nombre, String apellidos, Date fechaNac, String email) {
		this.codCliente = codCliente;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.email = email;
	}

	public Cliente(String dni, String contrasenia) {
		this.dni = dni;
		this.contrasenia = contrasenia;
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
	
}
