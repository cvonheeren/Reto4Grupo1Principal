package ModeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Alojamiento;

public class AlojamientotTest {
	
	private Alojamiento alojamiento = new Alojamiento(1, "Hotel Bilbao", "Bilbao");
	
	@Test
	public void testConstructor() {
		assertEquals(1, alojamiento.getCodAlojamiento());
		assertEquals("Hotel Bilbao", alojamiento.getNombre());
		assertEquals("Bilbao", alojamiento.getUbicacion());
	}

	@Test
	public void testCodAlojamiento() {
		alojamiento.setCodAlojamiento(1);
		assertEquals(1, alojamiento.getCodAlojamiento());
	}
	
	@Test
	public void testNombre() {
		alojamiento.setNombre("Hotel Bilbao");
		assertEquals("Hotel Bilbao", alojamiento.getNombre());
	}
	
	@Test
	public void testUbicacion() {
		alojamiento.setUbicacion("Bilbao");
		assertEquals("Bilbao", alojamiento.getUbicacion());
	}
}
