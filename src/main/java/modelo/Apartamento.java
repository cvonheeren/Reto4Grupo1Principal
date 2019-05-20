package modelo;

import java.util.ArrayList;

public class Apartamento extends Alojamiento {

	private int piso;
	private ArrayList<Estancia> estancias = null;

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
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, imgurl);
		this.piso = piso;
		this.estancias = estancias;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	public ArrayList<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(ArrayList<Estancia> estancias) {
		this.estancias = estancias;
	}
}
