package Modelo;

public class Alojamiento {

	protected int codAlojamiento;
	protected String nombre;
	protected String ubicacion;
	
	public Alojamiento(int codAlojamiento, String nombre, String ubicacion) {
		this.codAlojamiento = codAlojamiento;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}

	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
