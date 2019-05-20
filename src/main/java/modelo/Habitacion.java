package modelo;

import java.util.Objects;

public class Habitacion extends Estancia {

	private int ctaCamasSimples;
	private int ctaCamasMatrimonio;
	private int ctaCamasInfantil;
	private float tarifaNormal;
	private float tarifaVerano;
	private float tarifaFestivo;
	private String descripcion;
	
	/**
	 * 
	 * @param codHabitacion Codigo unico para cada habitacion de todos los hoteles
	 * @param numeroHabitacion el numero de la habitacion en ese hotel
	 * @param tamano Los metros cuadrados de la habitacion
	 * @param ctaCamasSimples
	 * @param ctaCamasMatrimonio
	 * @param ctaCamasInfantil
	 * @param cantidad
	 * @param tarifaNormal
	 * @param tarifaVerano
	 * @param tarifaFestivo
	 * @param descripcion
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

	@Override
	public String toString() {
		return "Habitacion [ctaCamasSimples=" + ctaCamasSimples + ", ctaCamasMatrimonio=" + ctaCamasMatrimonio
				+ ", ctaCamasInfantil=" + ctaCamasInfantil + ", tarifaNormal=" + tarifaNormal + ", tarifaVerano="
				+ tarifaVerano + ", tarifaFestivo=" + tarifaFestivo + ", descripcion=" + descripcion + ", codEstancia="
				+ codEstancia + ", nombre=" + nombre + ", tamano=" + tamano + ", cantidad=" + cantidad + "]";
	}

	 @Override
     public boolean equals(Object o) {
         if (this == o) {
             return true;
         }
         if (o == null || getClass() != o.getClass()) {
             return false;
         }
         Habitacion habitacion = (Habitacion) o;
         return ctaCamasSimples == habitacion.ctaCamasSimples &&
        		 ctaCamasMatrimonio == habitacion.ctaCamasMatrimonio &&
        		 ctaCamasInfantil == habitacion.ctaCamasInfantil &&
        		 tarifaNormal == habitacion.tarifaNormal &&
        		 tarifaVerano == habitacion.tarifaVerano &&
        		 tarifaFestivo == habitacion.tarifaFestivo &&
        		 ctaCamasInfantil == habitacion.ctaCamasInfantil &&
        		 codEstancia == habitacion.codEstancia &&
        		 tamano == habitacion.tamano &&
        		 cantidad == habitacion.cantidad &&
        		 Objects.equals(descripcion, habitacion.descripcion) &&
        		 Objects.equals(nombre, habitacion.nombre);
     }
	
}
