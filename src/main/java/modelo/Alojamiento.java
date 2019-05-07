package modelo;

import java.util.ArrayList;

public class Alojamiento {

	protected int codAlojamiento;
	protected String ubicacion;
	protected String nombre;
	protected String descripcion;
	protected float longitud;
	protected float latitud;
	protected float tarifaNormal;
	protected float tarifaVerano;
	protected float recargo;
	protected float desayuno;
	protected float mediaPension;
	protected float pensionCompleta;
	protected String imgurl;
	protected ArrayList<Habitacion> habitaciones = null;
	
	/**
	 * Objeto padre Alojamiento
	 * @param codAlojamiento Codigo principal del alojamiento
	 * @param ubicacion Ubicacion
	 * @param nombre Nombre
	 * @param descripcion
	 * @param longitud
	 * @param latitud
	 * @param tarifaNormal
	 * @param tarifaVerano
	 * @param recargo
	 * @param desayuno
	 * @param mediaPension
	 * @param pensionCompleta
	 */
	public Alojamiento(int codAlojamiento, String ubicacion, String nombre, String descripcion, float longitud,
			float latitud, float tarifaNormal, float tarifaVerano, float recargo, float desayuno, float mediaPension,
			float pensionCompleta, String imgurl) {
		this.codAlojamiento = codAlojamiento;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.tarifaNormal = tarifaNormal;
		//hacer el select para cada alojamienro, de momento es general para todos un 20% mas
		this.tarifaVerano = tarifaNormal * 1.2f;
		this.recargo = tarifaNormal * 1.2f;
		
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

	public float getRecargo() {
		return recargo;
	}

	public void setRecargo(float recargo) {
		this.recargo = recargo;
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

	@Override
	public String toString() {
		return nombre + " (" + ubicacion + ")";
	}
}
