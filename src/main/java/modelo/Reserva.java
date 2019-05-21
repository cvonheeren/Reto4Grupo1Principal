package modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Reserva {

	private int codReserva;
	private Timestamp fechaCompra;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Alojamiento alojamiento = null;
	private ArrayList<Habitacion> habitacionesSeleccionadas = null;
	private int ctdHabitaciones;
	private ArrayList<Servicio> serviciosSeleccionados = null;
	private int ctdServicios;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, Timestamp fechaCompra, Date fechaEntrada, Date fechaSalida) {
		this.codReserva = codReserva;
		this.fechaCompra = fechaCompra;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.habitacionesSeleccionadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
		this.serviciosSeleccionados = new ArrayList<Servicio>();
		this.ctdServicios = 0;
	}
	
	public Reserva() {
		this.habitacionesSeleccionadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
		this.serviciosSeleccionados = new ArrayList<Servicio>();
		this.ctdServicios = 0;
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public Timestamp getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Timestamp fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}
	
	// habitaciones 
	public ArrayList<Habitacion> getHabitacionesSeleccionadas() {
		return habitacionesSeleccionadas;
	}

	public void setHabitacionesSeleccionadas(ArrayList<Habitacion> habitacionesReservadas) {
		this.habitacionesSeleccionadas = habitacionesReservadas;
	}
	
	public void addHabitacion(Habitacion habitacion) {
		this.habitacionesSeleccionadas.add(habitacion);
		this.ctdHabitaciones++;
	}
	
	public void removeHabitacion(Habitacion habitacion) {
		this.habitacionesSeleccionadas.remove(habitacion);
		this.ctdHabitaciones--;
	}

	public int getCtdHabitaciones() {
		return ctdHabitaciones;
	}

	public void setCtdHabitaciones(int ctdHabitaciones) {
		this.ctdHabitaciones = ctdHabitaciones;
	}
	
	// servicios 
	public ArrayList<Servicio> getServiciosSeleccionados() {
		return serviciosSeleccionados;
	}

	public void setServiciosSeleccionados(ArrayList<Servicio> serviciosSeleccionados) {
		this.serviciosSeleccionados = serviciosSeleccionados;
	}
	
	public void addServicio(Servicio servicio) {
		this.serviciosSeleccionados.add(servicio);
		this.ctdServicios++;
	}
	
	public void removeServicio(Servicio servicio) {
		this.serviciosSeleccionados.remove(servicio);
		this.ctdServicios--;
	}

	public int getCtdServicios() {
		return ctdServicios;
	}

	public void setCtdServicios(int ctdServicios) {
		this.ctdServicios = ctdServicios;
	}
	
}
