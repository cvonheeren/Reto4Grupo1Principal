package modelo;

import java.util.ArrayList;

public class Casa extends Alojamiento {

	private ArrayList<Estancia> estancias = new ArrayList<Estancia>();
	
	/**
	 * Crea un objeto Casa que guarda los datos especificos de las casas
	 * @param codAlojamiento
	 * @param ubicacion
	 * @param nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param imgurl
	 * @param estancias
	 */
	public Casa(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud,
			String imgurl, ArrayList<Estancia> estancias) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, imgurl);
		this.estancias = estancias;
	}

	public ArrayList<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(ArrayList<Estancia> estancias) {
		this.estancias = estancias;
	}
	
	/**
	 * Calcula el tamaño total de una casa en metros cuadrados
	 * @return metros cuadrados totales
	 */
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
