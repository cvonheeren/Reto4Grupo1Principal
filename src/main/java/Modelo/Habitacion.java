package modelo;

public class Habitacion {

	protected int codHabitacion;
	protected int numeroHabitacion;
	protected float tamano;
	protected int numeroCamas;
	protected String tipoCama;
	
	/**
	 * Habitaciones de los hoteles
	 * @param codHabitacion Codigo unico para cada habitacion de todos los hoteles
	 * @param numeroHabitacion el numero de la habitacion en ese hotel
	 * @param tamano Los metros cuadrados de la habitacion
	 * @param numeroCamas Numero de camas
	 * @param tipoCama Tipo de cama
	 */
	public Habitacion(int codHabitacion, int numerohabitacion, float tamano, int numeroCamas, String tipoCama) {
		super();
		this.codHabitacion = codHabitacion;
		this.numeroHabitacion = numerohabitacion;
		this.tamano = tamano;
		this.numeroCamas = numeroCamas;
		this.tipoCama = tipoCama;
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

	public int getNumeroCamas() {
		return numeroCamas;
	}

	public void setNumeroCamas(int numeroCamas) {
		this.numeroCamas = numeroCamas;
	}

	public String getTipoCama() {
		return tipoCama;
	}

	public void setTipoCama(String tipoCama) {
		this.tipoCama = tipoCama;
	}
}
