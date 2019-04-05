package controlador;

import modelo.Modelo;
import vista.Vista;

public class Controlador {

	public Modelo miModelo;
	public Vista miVista;
	
	/**
	 * Constructor
	 * @param miModelo Los objetos y los metodos que los tratan
	 * @param miVista La interfaz
	 */
	public Controlador(Modelo miModelo, Vista miVista) {
		this.miModelo = miModelo;
		this.miVista = miVista;
		ControladorBienvenida controladorBienvenida = new ControladorBienvenida(this);
	}

}
