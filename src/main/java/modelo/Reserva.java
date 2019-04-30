package modelo;

import java.sql.Date;
import java.util.ArrayList;

public class Reserva {

	private int codReserva;
	private int codCliente;
	private int codAlojamiento;
	private Date fechaCompra;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float precio;
	public ArrayList<Habitacion> habitacionesReservadas;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * 
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, int codCliente, int codAlojamiento, Date fechaCompra, Date fechaEntrada, Date fechaSalida, float precio) {
		this.codReserva = codReserva;
		this.codCliente = codCliente;
		this.codAlojamiento = codAlojamiento;
		this.fechaCompra = fechaEntrada;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaEntrada;
		this.precio = precio;
		this.habitacionesReservadas = new ArrayList<Habitacion>();
	}
	
	public Reserva() {
		
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
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
	
}
