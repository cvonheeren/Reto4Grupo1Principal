package modelo;

public class Estancia {

	protected int codEstancia;
	protected String nombre;
	protected float tamano;
	protected int cantidad;
	
	/**
	 * Crea un objeto estancia con todos los datos de una estancia
	 * @param codEstancia Codigo unico para cada estancia de un alojamiento
	 * @param nombre Nombre del tipo de habitacion
	 * @param tamano Metros cuadrados de la habitacion
	 * @param cantidad Numero de estancias de este tipo que hay en un alojamiento
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
