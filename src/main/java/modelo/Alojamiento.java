package modelo;

import java.util.ArrayList;

public class Alojamiento {

	protected int codAlojamiento;
	protected String ubicacion;
	protected String nombre;
	protected String descripcion;
	protected float longitud;
	protected float latitud;
	protected float desayuno;
	protected float mediaPension;
	protected float pensionCompleta;
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
	 * @param desayuno Precio del desayuno
	 * @param mediaPension Precio de la media pension
	 * @param pensionCompleta Precio de la pension completa
	 * @param imgurl url de la imagen del alojamiento
	 */
	public Alojamiento(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud,
			float latitud, float desayuno, float mediaPension,
			float pensionCompleta, String imgurl) {
		this.codAlojamiento = codAlojamiento;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.desayuno = desayuno;
		this.mediaPension = mediaPension;
		this.pensionCompleta = pensionCompleta;
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


	public float getDesayuno() {
		return desayuno;
	}

	public void setDesayuno(float desayuno) {
		this.desayuno = desayuno;
	}

	public float getMediaPension() {
		return mediaPension;
	}

	public void setMediaPension(float mediaPension) {
		this.mediaPension = mediaPension;
	}

	public float getPensionCompleta() {
		return pensionCompleta;
	}

	public void setPensionCompleta(float pensionCompleta) {
		this.pensionCompleta = pensionCompleta;
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
