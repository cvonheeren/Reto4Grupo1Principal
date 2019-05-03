package modelo;

public class Casa extends Alojamiento{

	int banios;
	float area;
	
	/**
	 * Objeto hijo de Alojamiento del tipo Casa
	 * @param codAlojamiento
	 * @param ubicacion
	 * @param nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param tarifaNormal
	 * @param tarifaVerano
	 * @param recargo
	 * @param desayuno
	 * @param mediaPension
	 * @param pensionCompleta
	 * @param imgurl
	 * @param banios
	 * @param area
	 */
	public Casa(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud,
			float tarifaNormal, float tarifaVerano, float recargo, float desayuno, float mediaPension,
			float pensionCompleta, String imgurl, int banios, float area) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, tarifaNormal, tarifaVerano, recargo,
				desayuno, mediaPension, pensionCompleta, imgurl);
		this.banios = banios;
		this.area = area;
	}

	public int getBanios() {
		return banios;
	}

	public void setBanios(int banios) {
		this.banios = banios;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}
}
