package modelo;

import controlador.Controlador;

public class Modelo {

	private Controlador miControlador;
	
	public Alojamiento alojamiento = null;
	public Hotel hotel = null;
	public Reserva reserva = null;
	public Habitacion habitacion = null;
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.alojamiento = new Alojamiento();
		this.hotel = new Hotel();
		this.reserva = new Reserva();
		this.habitacion = new Habitacion();
	}
	
	
	public Controlador getControlador() {
		return miControlador;
	}

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	} 
}
