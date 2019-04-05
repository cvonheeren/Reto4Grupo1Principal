package controlador;

import modelo.Alojamiento;
import modelo.Modelo;
import vista.Vista;

public class ControladorPago {

	private Vista miVista;
	private Modelo miModelo;	

	public ControladorPago(Vista miVista, Modelo miModelo) {
		this.miModelo=miModelo;
		this.miVista=miVista;
	}
	
}
