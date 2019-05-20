package modelo;

import java.util.ArrayList;

public class Alojamiento {

	protected int codAlojamiento;
	protected String ubicacion;
	protected String nombre;
	protected String descripcion;
	protected float longitud;
	protected float latitud;
	protected String imgurl;
	protected ArrayList<Habitacion> habitaciones = null;
	protected ArrayList<Servicio> servicios = null;
	
	/**
	 * Crea un objeto Alojamiento que guarda los datos no especificos de los alojamientos
	 * @param codAlojamiento Codigo unico para cada alojamiento
	 * @param ubicacion Nombre de la ciudad donde se encuentra el alojamiento
	 * @param nombre Nombre del alojamiento
	 * @param descripcion Informacion del alojamiento
	 * @param longitud Coordenadas longitud
	 * @param latitud Coordanadas latitud
	 * @param imgurl url de la imagen del alojamiento
	 */
	public Alojamiento(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud,
			float latitud, String imgurl) {
		this.codAlojamiento = codAlojamiento;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.imgurl = imgurl;
	}

	public Alojamiento() {
		
	}
	
	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	@Override
	public String toString() {
		return nombre + " (" + ubicacion + ")";
	}
}
