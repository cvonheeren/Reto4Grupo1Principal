package modelo;

import java.util.ArrayList;

public class Casa extends Alojamiento {

	private int banios;
	private float area;
	private ArrayList<Estancia> estancias = new ArrayList<Estancia>();
	
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
	 * @param imgurl
	 * @param banios
	 * @param area
	 */
	public Casa(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud,
			float tarifaNormal, float tarifaVerano, float recargo, float desayuno, String imgurl, ArrayList<Estancia> estancias) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, tarifaNormal, tarifaVerano, recargo,
				desayuno, imgurl);
//		this.banios = numBanios();
//		this.area = calcularArea();
		this.estancias = estancias;
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

	public ArrayList<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(ArrayList<Estancia> estancias) {
		this.estancias = estancias;
	}
	
//	private int numBanios() {
//		int suma = 0;
//		for (Estancia e: this.estancias) {
//			if ( e.getNombre() == "BANIO") {
//				suma++;
//			}
//		}
//		return suma;
//	}
//	
//	private float calcularArea() {
//		float suma = 0;
//		if (this.estancias != null) {
//			for (Estancia e: this.estancias) {
//				suma += e.getTamano();
//			}
//		}
//		if (this.habitaciones != null) {
//			for (Habitacion h: this.habitaciones) {
//				suma += h.getTamano();
//			}
//		}
//		return suma;
//	}
	
}
