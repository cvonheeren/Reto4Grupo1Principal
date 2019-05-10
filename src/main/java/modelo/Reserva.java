package modelo;

import java.sql.Date;
import java.util.ArrayList;

import core.Principal;

public class Reserva {

	private int codReserva;
	private Date fechaCompra;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float precio;
	private Alojamiento alojamiento = null;
	private Cliente cliente = null;
	private ArrayList<Habitacion> habitacionesReservadas = null;
	private int ctdHabitaciones;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, Date fechaCompra, Date fechaEntrada, Date fechaSalida, float precio) {
		this.codReserva = codReserva;
		this.fechaCompra = fechaEntrada;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaEntrada;
		this.precio = precio;
		this.habitacionesReservadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
	}
	
	public Reserva() {
		this.habitacionesReservadas = new ArrayList<Habitacion>();
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Habitacion> getHabitacionesReservadas() {
		return habitacionesReservadas;
	}

	public void setHabitacionesReservadas(ArrayList<Habitacion> habitacionesReservadas) {
		this.habitacionesReservadas = habitacionesReservadas;
	}
	
	public void addHabitacion(Habitacion habitacion) {
		this.habitacionesReservadas.add(habitacion);
		this.ctdHabitaciones++;
	}
	
	public void removeHabitacion(Habitacion habitacion) {
		this.habitacionesReservadas.remove(habitacion);
		this.ctdHabitaciones--;
	}

	public int getCtdHabitaciones() {
		return ctdHabitaciones;
	}

	public void setCtdHabitaciones(int ctdHabitaciones) {
		this.ctdHabitaciones = ctdHabitaciones;
	}
	
}
