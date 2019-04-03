package controlador;

import controlador.Principal;
import modelo.Modelo;
import vista.Vista;

public class Principal {

	static Modelo miModelo;
	static Vista miVista;
	static Controlador miControlador;
	
	/**
	 * Método que inicia el programa
	 * @param args
	 */
	public static void main(String[] args) {
		iniciar();
	}

	/**
	 * Método que crea el modelo y la vista y las instancia al constructor del controlador
	 */
	public static void iniciar() {
		miVista = new Vista();
		miModelo = new Modelo();
		miControlador = new Controlador(miModelo, miVista);
		
		//Seteamos el controlador en el modelo para cuando usemos algo del controlador en el modelo
		miModelo.setControlador(miControlador);
	}
}
