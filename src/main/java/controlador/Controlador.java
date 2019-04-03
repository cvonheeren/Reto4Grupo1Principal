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
	}

	public Modelo getMiModelo() {
		return miModelo;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public Vista getMiVista() {
		return miVista;
	}

	public void setMiVista(Vista miVista) {
		this.miVista = miVista;
	}
}
