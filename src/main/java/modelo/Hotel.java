package modelo;

public class Hotel extends Alojamiento {

	private int estrellas;
	
	
	/**
	 * 
	 * @param codAlojamiento
	 * @param ubicacion
	 * @param nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param desayuno
	 * @param mediaPension
	 * @param pensionCompleta
	 * @param imgurl
	 * @param estrellas
	 */
	public Hotel(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud, float desayuno, float mediaPension, float pensionCompleta, String imgurl, int estrellas) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, desayuno, mediaPension,
				pensionCompleta, imgurl);
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
