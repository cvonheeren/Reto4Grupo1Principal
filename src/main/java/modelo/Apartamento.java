package modelo;

import java.util.ArrayList;

public class Apartamento extends Alojamiento {

	private int piso;
	private ArrayList<Estancia> estancias = null;

	/**
	 * Objeto hijo de Alojamiento del tipo Apartamento
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
	 * @param piso
	 */
	public Apartamento(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud, float latitud,
			float tarifaNormal, float tarifaVerano, float recargo, float desayuno, float mediaPension,
			float pensionCompleta, String imgurl, int piso, ArrayList<Estancia> estancias) {
		super(codAlojamiento, ubicacion, nombre, descripcion, longitud, latitud, tarifaNormal, tarifaVerano, recargo,
				desayuno, mediaPension, pensionCompleta, imgurl);
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
