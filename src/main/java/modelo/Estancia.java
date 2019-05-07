package modelo;

public class Estancia {

	private int codEstancia;
	private String nombre;
	private float tamano;
	private int cantidad;
	
	/**
	 * Habitaciones de los hoteles
	 * @param codHabitacion Codigo unico para cada habitacion de todos los hoteles
	 * @param numeroHabitacion el numero de la habitacion en ese hotel
	 * @param tamano Los metros cuadrados de la habitacion
	 * @param numeroCamas Numero de camas
	 * @param tipoCama Tipo de cama
	 */
	public Estancia(int codEstancia, String nombre, float tamano, int cantidad) {
		this.codEstancia = codEstancia;
		this.nombre = nombre;
		this.tamano = tamano;
		this.cantidad = cantidad;
	}
	
	public Estancia(int codEstancia, int cantidad) {
		this.codEstancia = codEstancia;
		this.cantidad = cantidad;
	}
	
	public Estancia() {
		
	}
	
	public int getCodEstancia() {
		return codEstancia;
	}

	public void setCodEstancia(int codEstancia) {
		this.codEstancia = codEstancia;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
