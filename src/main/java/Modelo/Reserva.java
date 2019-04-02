package Modelo;

import java.sql.Date;

public class Reserva {

	protected int codReserva;
	protected Date fecha;
	protected float precio;
	
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
