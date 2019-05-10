package modelo;

public class Habitacion extends Estancia {

	private int ctaCamasSimples;
	private int ctaCamasMatrimonio;
	private int ctaCamasInfantil;
	private float tarifaNormal;
	private float tarifaVerano;
	private float tarifaFestivo;
	private String descripcion;
	
	/**
	 * Habitaciones de los hoteles
	 * @param codHabitacion Codigo unico para cada habitacion de todos los hoteles
	 * @param numeroHabitacion el numero de la habitacion en ese hotel
	 * @param tamano Los metros cuadrados de la habitacion
	 * @param numeroCamas Numero de camas
	 * @param tipoCama Tipo de cama
	 */
	public Habitacion(int codHabitacion, String nombre, float tamano, int ctaCamasSimples, int ctaCamasMatrimonio,
			int ctaCamasInfantil, int cantidad, float tarifaNormal, float tarifaVerano, float tarifaFestivo, String descripcion) {
		super(codHabitacion, nombre, tamano, cantidad);
		this.ctaCamasSimples = ctaCamasSimples;
		this.ctaCamasMatrimonio = ctaCamasMatrimonio;
		this.ctaCamasInfantil = ctaCamasInfantil;
		this.tarifaNormal = tarifaNormal;
		this.tarifaVerano = tarifaVerano;
		this.tarifaFestivo = tarifaFestivo;
		this.descripcion = descripcion;
	}
	
	public Habitacion(int codHabitacion, int cantidad) {
		super(codHabitacion, cantidad);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public float getTarifaNormal() {
		return tarifaNormal;
	}

	public void setTarifaNormal(float tarifaNormal) {
		this.tarifaNormal = tarifaNormal;
	}

	public float getTarifaVerano() {
		return tarifaVerano;
	}

	public void setTarifaVerano(float tarifaVerano) {
		this.tarifaVerano = tarifaVerano;
	}

	public float getTarifaFestivo() {
		return tarifaFestivo;
	}

	public void setTarifaFestivo(float tarifaFestivo) {
		this.tarifaFestivo = tarifaFestivo;
	}
	
	public int getNumAdultos() {
		int num = this.ctaCamasSimples*1 + this.ctaCamasMatrimonio*2;
		return num;
	}
	
	public int getNumHuespedes() {
		return this.ctaCamasSimples + this.ctaCamasMatrimonio * 2 + this.ctaCamasInfantil;
	}
	
}
