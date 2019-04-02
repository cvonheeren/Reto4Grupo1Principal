package Modelo;

public class Hotel extends Alojamiento {

	protected int estrellas;
	
	public Hotel(int codAlojamiento, String nombre, String ubicacion, int estrellas) {
		super(codAlojamiento, nombre, ubicacion);
		this.estrellas = estrellas;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
}
