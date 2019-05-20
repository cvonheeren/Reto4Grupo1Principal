package modelo;

public class Hotel extends Alojamiento {

	private int estrellas;
	
	/**
	 * Objeto que guarda los datos especificos de los hoteles
	 * @param codAlojamiento
	 * @param ubicacion
	 * @param nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param imgurl
	 * @param estrellas
	 */
	public Hotel(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud, String imgurl, int estrellas) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, imgurl);
		this.estrellas = estrellas;
	}

	public Hotel() {
		
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
}
