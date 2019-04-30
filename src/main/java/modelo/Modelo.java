package modelo;

public class Modelo {

	
	public Habitacion habitacion = null;
	public Cliente cliente = null;
	public Reserva reserva = null;
	public Pago pago = null;
	public GestorBBDD gestorBBDD = null;
	public GenerarFactura generarFactura = null;
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.pago = new Pago();
		this.gestorBBDD = new GestorBBDD();
		this.generarFactura = new GenerarFactura();
		this.reserva = new Reserva();
	}
}
