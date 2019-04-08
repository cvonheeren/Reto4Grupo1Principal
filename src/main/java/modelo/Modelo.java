package modelo;

public class Modelo {

	
	public Alojamiento alojamiento = null;
	public Hotel hotel = null;
	public Reserva reserva = null;
	public Habitacion habitacion = null;
	public Pago pago = null;
	public GestorBBDD gestorBBDD;
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.alojamiento = new Alojamiento();
		this.hotel = new Hotel();
		this.reserva = new Reserva();
		this.habitacion = new Habitacion();
		this.pago = new Pago();
		this.gestorBBDD = new GestorBBDD();
	}
}
