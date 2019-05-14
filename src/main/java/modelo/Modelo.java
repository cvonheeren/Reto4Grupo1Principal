package modelo;

public class Modelo {

	
//	public Habitacion habitacion = null;
	public Cliente cliente = null;
	public Reserva reserva = null;
	public GestorDinero gestorDinero = null;
	public GestorBBDD gestorBBDD = null;
	public GestorArchivos gestorArchivos = null;
	public GestorValidaciones gestorValidaciones = null;
	public GestorDeFechas gestorFechas = new GestorDeFechas();
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.gestorDinero = new GestorDinero();
		this.gestorBBDD = new GestorBBDD();
		this.gestorArchivos = new GestorArchivos();
		this.reserva = new Reserva();
		this.gestorValidaciones = new GestorValidaciones();
		this.gestorFechas = new GestorDeFechas();
	}		
	
}

