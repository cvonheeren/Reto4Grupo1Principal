package modelo;

import java.sql.Date;
import java.util.ArrayList;


public class Reserva {

	private int codReserva;
	private Date fechaCompra;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Alojamiento alojamiento = null;
	private ArrayList<Habitacion> habitacionesSeleccionadas = null;
	private int ctdHabitaciones;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, Date fechaCompra, Date fechaEntrada, Date fechaSalida) {
		this.codReserva = codReserva;
		this.fechaCompra = fechaEntrada;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaEntrada;
		this.habitacionesSeleccionadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
	}
	
	public void ActualizarHabitaciones(ArrayList<Habitacion> habitacionesSelecionadas, int cantidad)
	{
		this.habitacionesSeleccionadas = habitacionesSelecionadas;
		this.ctdHabitaciones = cantidad;
	}
	
	public void VaciarSeleccionHabitaciones()
	{
		this.habitacionesSeleccionadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
	}
	
	public Reserva() {
		this.habitacionesSeleccionadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
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

	
}
