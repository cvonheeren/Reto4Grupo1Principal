package modelo;

public class Modelo {

	
//	public Habitacion habitacion = null;
	public Cliente cliente = null;
	public Reserva reserva = null;
	public Pago pago = null;
	public GestorBBDD gestorBBDD = null;
	public GestorArchivos gestorArchivos = null;
	public GestorValidaciones gestorValidaciones = null;
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.pago = new Pago();
		this.gestorBBDD = new GestorBBDD();
		this.gestorArchivos = new GestorArchivos();
		this.reserva = new Reserva();
		this.gestorValidaciones = new GestorValidaciones();
	}		
	
}

