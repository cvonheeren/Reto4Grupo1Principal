package Modelo;

import java.sql.Date;

public class Reserva {

	protected int codReserva;
	protected Date fecha;
	protected float precio;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, Date fecha, float precio) {
		this.codReserva = codReserva;
		this.fecha = fecha;
		this.precio = precio;
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}
