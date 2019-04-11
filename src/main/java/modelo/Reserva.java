package modelo;

import java.sql.Date;

public class Reserva {

	private int codReserva;
	private int codHotel;
	private Date fecha;
	private float precio;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * 
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, int codHotel, Date fecha, float precio) {
		this.codReserva = codReserva;
		this.codHotel = codHotel;
		this.fecha = fecha;
		this.precio = precio;
	}
	
	public Reserva() {
		
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public int getCodHotel() {
		return codHotel;
	}

	public void setCodHotel(int codHotel) {
		this.codHotel = codHotel;
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
