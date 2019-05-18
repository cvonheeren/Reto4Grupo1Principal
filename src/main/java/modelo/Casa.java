package modelo;

import java.util.ArrayList;

public class Casa extends Alojamiento {

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
	 */
	public Casa(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud,
			float tarifaNormal, float tarifaVerano, float desayuno, String imgurl, ArrayList<Estancia> estancias) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, tarifaNormal, tarifaVerano, desayuno, imgurl);
		this.estancias = estancias;
	}

	public ArrayList<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(ArrayList<Estancia> estancias) {
		this.estancias = estancias;
	}
	
	public float calcularArea() {
		float suma = 0;
		if (this.estancias != null) {
			for (Estancia e: this.estancias) {
				suma += e.getTamano() * e.getCantidad();
			}
		}
		if (this.habitaciones != null) {
			for (Habitacion h: this.habitaciones) {
				suma += h.getTamano() * h.getCantidad();
			}
		}
		return suma;
	}
	
}
