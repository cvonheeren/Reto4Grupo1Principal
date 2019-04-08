package controladorTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.Controlador;
import controlador.ControladorBienvenida;
import modelo.Modelo;
import vista.Vista;

public class ControladorTest {

	private Modelo miModelo;
	private Vista miVista;
	private ControladorBienvenida controladorBienvenida;
	private Controlador miControlador = new Controlador(miModelo, miVista);
	
	@Test
	public void testConstructor() {
		assertEquals(miControlador.miVista.getClass(), miVista.getClass());
		assertEquals(miControlador.miModelo.getClass(), miModelo.getClass());
		assertEquals(miControlador.controladorBienvenida.getClass(), controladorBienvenida.getClass());
		
		assertNotEquals(null, miControlador.miModelo);
		assertNotEquals(null, miControlador.miVista);
		assertNotEquals(null, miControlador.controladorBienvenida);
	}
	
}
