package modelo;

import java.util.ArrayList;

public class Apartamento extends Casa {

	private int piso;

	/**
	 * Crea un objeto Apartamento que guarda los datos especificos de los apartamentos
	 * @param codAlojamiento
	 * @param ubicacion
	 * @param nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param imgurl
	 * @param piso
	 * @param estancias
	 */
	public Apartamento(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud,
			String imgurl, int piso, ArrayList<Estancia> estancias) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, imgurl, estancias);
		this.piso = piso;
	}

	/**
	 * Obtiene el piso
	 * @return
	 */
	public int getPiso() {
		return piso;
	}

	/**
	 * Establece el piso
	 * @param piso
	 */
	public void setPiso(int piso) {
		this.piso = piso;
	}
	
}
