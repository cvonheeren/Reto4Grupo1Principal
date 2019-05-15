package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.GestorArchivos;
import modelo.GestorBBDD;
import modelo.GestorDeFechas;
import modelo.GestorValidaciones;

import modelo.Modelo;
import modelo.GestorDinero;
import modelo.Reserva;

public class ModeloTest {

	private Modelo miModelo = new Modelo();
	
	@Test
	public void testpago() {
		assertNotEquals(null, miModelo.gestorDinero);
		assertEquals(GestorDinero.class, miModelo.gestorDinero.getClass());
	}
	
	@Test
	public void testcliente() {
		assertEquals(null, miModelo.cliente);
	}
	
	@Test
	public void testReserva() {
		assertNotEquals(null, miModelo.reserva);
		assertEquals(Reserva.class, miModelo.reserva.getClass());
	}
	
	@Test
	public void testGestArchvs() {
		assertNotEquals(null, miModelo.gestorArchivos);
		assertEquals(GestorArchivos.class, miModelo.gestorArchivos.getClass());
	}
	
	@Test
	public void testgestorBBDD() {
		assertNotEquals(null, miModelo.gestorBBDD);
		assertEquals(GestorBBDD.class, miModelo.gestorBBDD.getClass());
	}
	
	@Test
	public void testgestVali() {
		assertNotEquals(null, miModelo.gestorValidaciones);
		assertEquals(GestorValidaciones.class, miModelo.gestorValidaciones.getClass());
	}
	
	@Test
	public void testgestFechas() {
		assertNotEquals(null, miModelo.gestorFechas);
		assertEquals(GestorDeFechas.class, miModelo.gestorFechas.getClass());
	}
	
}
