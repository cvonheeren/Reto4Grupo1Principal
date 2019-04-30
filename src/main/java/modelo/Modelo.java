package modelo;

import java.sql.Date;
import java.util.ArrayList;

public class Modelo {

	
	public Alojamiento alojamiento = null;
	public Hotel hotel = null;
	public Reserva reserva = null;
	public Habitacion habitacion = null;
	public Pago pago = null;
	public GestorBBDD gestorBBDD = null;
	public GenerarFactura generarFactura = null;
	public AlojamientoLista alojamientoLista = null;
	public Cliente cliente = null;
	public Date fechaEntrada = null;
	public Date fechaSalida = null;
	public ArrayList<Habitacion> habitacionesReservadas = null;
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.pago = new Pago();
		this.gestorBBDD = new GestorBBDD();
		this.alojamientoLista = new AlojamientoLista();
		this.habitacionesReservadas = new ArrayList<Habitacion>();
		this.generarFactura = new GenerarFactura();
	}
}
