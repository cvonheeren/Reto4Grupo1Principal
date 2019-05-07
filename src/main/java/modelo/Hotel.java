package modelo;

public class Hotel extends Alojamiento {

	private int estrellas;
	
	/**
	 * Objeto hijo de Alojamiento del tipo Hotel
	 * @param codAlojamiento Codigo principal del alojamiento
	 * @param ubicacion Ubicacion
	 * @param nombre Nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param tarifaNormal
	 * @param tarifaVerano
	 * @param recargo
	 * @param desayuno
	 * @param mediaPension
	 * @param pensionCompleta
	 * @param estrellas Numero de estrellas del hotel
	 */
	public Hotel(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud, float tarifaNormal,
			float tarifaVerano, float recargo, float desayuno, float mediaPension, float pensionCompleta, int estrellas, String imgurl) {
		
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, tarifaNormal, tarifaVerano, recargo, desayuno, mediaPension, pensionCompleta, imgurl);
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
