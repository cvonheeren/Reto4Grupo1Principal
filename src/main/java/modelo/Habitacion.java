package modelo;

public class Habitacion {

	private int codHabitacion;
	private String nombre;
	private float tamano;
	private int ctaCamasSimples;
	private int ctaCamasMatrimonio;
	private int ctaCamasInfantil;
	private int cantidad;
	
	/**
	 * Habitaciones de los hoteles
	 * @param codHabitacion Codigo unico para cada habitacion de todos los hoteles
	 * @param numeroHabitacion el numero de la habitacion en ese hotel
	 * @param tamano Los metros cuadrados de la habitacion
	 * @param numeroCamas Numero de camas
	 * @param tipoCama Tipo de cama
	 */
	public Habitacion(int codHabitacion, String nombre, int ctaCamasSimples, int ctaCamasMatrimonio, int ctaCamasInfantil, float tamano, int cantidad) {
		this.codHabitacion = codHabitacion;
		this.nombre = nombre;
		this.ctaCamasSimples = ctaCamasSimples;
		this.ctaCamasMatrimonio = ctaCamasMatrimonio;
		this.ctaCamasInfantil = ctaCamasInfantil;
		this.tamano = tamano;
		this.cantidad = cantidad;
	}
	
	public Habitacion(int codHabitacion, int cantidad) {
		this.codHabitacion = codHabitacion;
		this.cantidad = cantidad;
	}
	
	public Habitacion() {
		
	}

	public int getCodHabitacion() {
		return codHabitacion;
	}

	public void setCodHabitacion(int codHabitacion) {
		this.codHabitacion = codHabitacion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
