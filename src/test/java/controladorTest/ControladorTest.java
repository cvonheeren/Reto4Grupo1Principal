package controladorTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

public class ControladorTest {

	private Modelo miModelo;
	private Vista miVista;
	private Controlador miControlador = new Controlador(miModelo, miVista);
	
	@Test
	public void testConstructor() {
		assertEquals(miControlador.miVista, miVista);
		assertEquals(miControlador.miModelo, miModelo);
	}
	
}
