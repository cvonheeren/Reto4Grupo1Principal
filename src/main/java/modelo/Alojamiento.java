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
	
	
	/**
	 * Devuelve el codigo de alojamiento
	 * @return
	 */
	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	/**
	 * Establece el codigo de alojamiento
	 * @param codAlojamiento
	 */
	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
	}

	/**
	 * Devuelve la ubicacion
	 * @return
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * Establece la ubicacion
	 * @param ubicacion
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Devuelve el nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la descripcion
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * Devuelve la longitud
	 * @return
	 */
	public float getLongitud() {
		return longitud;
	}

	
	/**
	 * Establece la longitud
	 * @param longitud
	 */
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	/**
	 * Devuelve la latitud
	 * @return
	 */
	public float getLatitud() {
		return latitud;
	}

	/**
	 * Establece la latitud
	 * @param latitud
	 */
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	
	/**
	 * Devuelve la url de la imagen
	 * @return
	 */
	public String getImgurl() {
		return imgurl;
	}

	/**
	 * Establece la URL de la imagen
	 * @param imgurl
	 */
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	/**
	 * Obtene el arraylist de habitaciones
	 * @return
	 */
	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	/**
	 * Establece el arraylist de habitaciones
	 * @param habitaciones
	 */
	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	/**
	 * Obtiene el arraylist de servicios
	 * @return
	 */
	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * Establece el arraylist de servicios
	 * @param servicios
	 */
	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	@Override
	public String toString() {
		return nombre + " (" + ubicacion + ")";
	}
}
