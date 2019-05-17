package modelo;

public class Servicio {

	protected int codServicio;
	protected String nombre;
	protected float precio;
	protected String iconString;
	
	public Servicio(int codServicio, String nombre, float precio, String icon) {
		this.codServicio = codServicio;
		this.nombre = nombre;
		this.precio = precio;
		this.iconString = icon;
	}

	public int getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(int codServicio) {
		this.codServicio = codServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getIcon() {
		return iconString;
	}

	public void setIcon(String icon) {
		this.iconString = icon;
	}
}
