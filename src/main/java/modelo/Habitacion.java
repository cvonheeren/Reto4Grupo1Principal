package modelo;

public class Habitacion {

	protected int codHabitacion;
	protected int numeroHabitacion;
	protected float tamano;
	protected int ctaCamasSimples;
	protected int ctaCamasMatrimonio;
	protected int ctaCamasInfantil;
	
	/**
	 * Habitaciones de los hoteles
	 * @param codHabitacion Codigo unico para cada habitacion de todos los hoteles
	 * @param numeroHabitacion el numero de la habitacion en ese hotel
	 * @param tamano Los metros cuadrados de la habitacion
	 * @param numeroCamas Numero de camas
	 * @param tipoCama Tipo de cama
	 */
	public Habitacion(int codHabitacion, int ctaCamasSimples, int ctaCamasMatrimonio, int ctaCamasInfantil, float tamano) {
		super();
		this.codHabitacion = codHabitacion;
//		this.numeroHabitacion = numerohabitacion;
		this.ctaCamasSimples = ctaCamasSimples;
		this.ctaCamasMatrimonio = ctaCamasMatrimonio;
		this.ctaCamasInfantil = ctaCamasInfantil;
		this.tamano = tamano;
	}
	
	public Habitacion() {
		
	}

	public int getCodHabitacion() {
		return codHabitacion;
	}

	public void setCodHabitacion(int codHabitacion) {
		this.codHabitacion = codHabitacion;
	}

	public int getNumerohabitacion() {
		return numeroHabitacion;
	}

	public void setNumerohabitacion(int numerohabitacion) {
		this.numeroHabitacion = numerohabitacion;
	}

	public float getTamano() {
		return tamano;
	}

	public void setTamano(float tamano) {
		this.tamano = tamano;
	}

	public int getCtaCamasSimples() {
		return ctaCamasSimples;
	}

	public void setCtaCamasSimples(int ctaCamasSimples) {
		this.ctaCamasSimples = ctaCamasSimples;
	}

	public int getCtaCamasMatrimonio() {
		return ctaCamasMatrimonio;
	}

	public void setCtaCamasMatrimonio(int ctaCamasMatrimonio) {
		this.ctaCamasMatrimonio = ctaCamasMatrimonio;
	}

	public int getCtaCamasInfantil() {
		return ctaCamasInfantil;
	}

	public void setCtaCamasInfantil(int ctaCamasInfantil) {
		this.ctaCamasInfantil = ctaCamasInfantil;
	}	
	
}
