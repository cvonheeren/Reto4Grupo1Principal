package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Servicio;

public class ServicioTest {
	
	private Servicio servicio = new Servicio(1, "wifi", 5f, "");

	@Test
	public void testConstructor() {
		assertEquals(1, servicio.getCodServicio());
		assertEquals("wifi", servicio.getNombre());
		assertEquals(5f, servicio.getPrecio(), 0);
		assertEquals("", servicio.getIcon());
	}

	@Test
	public void testCodServico() {
		servicio.setCodServicio(2);
		assertEquals(2, servicio.getCodServicio());
	}
	
	@Test
	public void testNombre() {
		servicio.setNombre("masaje");
		assertEquals("masaje", servicio.getNombre());
	}
	
	@Test
	public void testPrecio() {
		servicio.setPrecio(10f);
		assertEquals(10f, servicio.getPrecio(), 0);
	}
	
	@Test
	public void testIcon() {
		servicio.setIcon("fa");
		assertEquals("fa", servicio.getIcon());
	}

}
