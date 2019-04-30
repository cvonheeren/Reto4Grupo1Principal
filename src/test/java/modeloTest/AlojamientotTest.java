package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Alojamiento;

public class AlojamientotTest {
	
	private Alojamiento alojamiento = new Alojamiento(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 1, 1.5f, 2, 3, 4, 5, "www.kakota");
	
	@Test
	public void testConstructor() {
		assertEquals(1, alojamiento.getCodAlojamiento());
		assertEquals("Hotel Bilbao", alojamiento.getNombre());
		assertEquals("Bilbao", alojamiento.getUbicacion());
		assertEquals("cremas", alojamiento.getDescripcion());
		assertEquals(20, alojamiento.getLongitud(), 0.0001);
		assertEquals(20, alojamiento.getLatitud(), 0.0001);
		assertEquals(1, alojamiento.getTarifaNormal(), 0.0001);
		assertEquals(1.5f, alojamiento.getTarifaVerano(), 0.0001);
		assertEquals(2, alojamiento.getRecargo(), 0.0001);
		assertEquals(3, alojamiento.getDesayuno(), 0.0001);
		assertEquals(4, alojamiento.getMediaPension(), 0.0001);
		assertEquals(5, alojamiento.getPensionCompleta(), 0.0001);
		assertEquals("www.kakota", alojamiento.getImgurl());
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
	
	@Test
	public void testDescripcion() {
		alojamiento.setDescripcion("Bilbao");
		assertEquals("Bilbao", alojamiento.getDescripcion());
	}
	
	@Test
	public void testLongitud() {
		alojamiento.setLongitud(20);
		assertEquals(20, alojamiento.getLongitud(), 0.00001);
	}
	
	@Test
	public void testLatitud() {
		alojamiento.setLatitud(20);
		assertEquals(20, alojamiento.getLatitud(), 0.00001);
	}
	
	@Test
	public void testTarifaNormal() {
		alojamiento.setTarifaNormal(20);
		assertEquals(20, alojamiento.getTarifaNormal(), 0.00001);
	}
	
	@Test
	public void testTarifaVerano() {
		alojamiento.setTarifaVerano(20);
		assertEquals(20, alojamiento.getTarifaVerano(), 0.00001);
	}
	
	@Test
	public void testRecargo() {
		alojamiento.setRecargo(20);
		assertEquals(20, alojamiento.getRecargo(), 0.00001);
	}
	
	@Test
	public void testDesayuno() {
		alojamiento.setDesayuno(20);
		assertEquals(20, alojamiento.getDesayuno(), 0.00001);
	}
	
	@Test
	public void testMediaPension() {
		alojamiento.setMediaPension(20);
		assertEquals(20, alojamiento.getMediaPension(), 0.00001);
	}
	
	@Test
	public void testPensionCompleta() {
		alojamiento.setPensionCompleta(20);
		assertEquals(20, alojamiento.getPensionCompleta(), 0.00001);
	}
	
	@Test
	public void testImgurl() {
		alojamiento.setImgurl("Bilbao");
		assertEquals("Bilbao", alojamiento.getImgurl());
	}
}
