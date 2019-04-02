package Modelo;

public class Hotel extends Alojamiento {

	protected int estrellas;
	
	/**
	 * Objeto hijo de Alojamiento del tipo Hotel
	 * @param codAlojamiento Codigo principal del alojamiento
	 * @param nombre Nombre
	 * @param ubicacion Ubicacion
	 * @param estrellas Numero de estrellas del hotel
	 */
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
