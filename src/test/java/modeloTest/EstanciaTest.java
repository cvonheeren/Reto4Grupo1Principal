package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Estancia;

public class EstanciaTest {

	Estancia estancia = new Estancia(1, "", 2, 3);
	Estancia estancia2 = new Estancia(1, 3);
	
	@Test
	public void testConstructor() {
		assertEquals(1, estancia.getCodEstancia());
		assertEquals("", estancia.getNombre());
		assertEquals(2, estancia.getTamano(), 0.000001);
		assertEquals(3, estancia.getCantidad(), 0.000001);
	}
	
	@Test
	public void testConstructor2() {
		assertEquals(1, estancia2.getCodEstancia());
		assertEquals(3, estancia2.getCantidad(), 0.000001);
	}
	
	@Test
	public void testCod() {
		estancia.setCodEstancia(3);;
		assertEquals(3, estancia.getCodEstancia());
	}
	
	@Test
	public void testNom() {
		estancia.setNombre("");
		assertEquals("", estancia.getNombre());
	}
	
	@Test
	public void testTamano() {
		estancia.setTamano(3);
		assertEquals(3, estancia.getTamano(), 0.000001);
	}
	
	@Test
	public void testCantidad() {
		estancia.setCantidad(3);
		assertEquals(3, estancia.getCantidad(), 0.000001);
	}
}
