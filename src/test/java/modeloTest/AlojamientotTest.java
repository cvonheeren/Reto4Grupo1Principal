package modeloTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Servicio;

public class AlojamientotTest {
	
	private Alojamiento alojamiento = new Alojamiento(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 3, 4, 5, "www.kakota");
	private Alojamiento alojamiento2 = new Alojamiento();
	private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private ArrayList<Servicio> servicios = new ArrayList<Servicio>();
	
	@Test
	public void testConstructor() {
		assertEquals(1, alojamiento.getCodAlojamiento());
		assertEquals("Hotel Bilbao", alojamiento.getNombre());
		assertEquals("Bilbao", alojamiento.getUbicacion());
		assertEquals("cremas", alojamiento.getDescripcion());
		assertEquals(20, alojamiento.getLongitud(), 0.0001);
		assertEquals(20, alojamiento.getLatitud(), 0.0001);
		assertEquals(3, alojamiento.getDesayuno(), 0.0001);
		assertEquals(4, alojamiento.getMediaPension(), 0.0001);
		assertEquals(5, alojamiento.getPensionCompleta(), 0.0001);
		assertEquals("www.kakota", alojamiento.getImgurl());
		assertEquals(null, alojamiento2.getImgurl());
	}

	@Test
	public void testCodAlojamiento() {
		alojamiento.setCodAlojamiento(2);
		assertEquals(2, alojamiento.getCodAlojamiento());
	}
	
	@Test
	public void testNombre() {
		alojamiento.setNombre("Hotel Bilbao2");
		assertEquals("Hotel Bilbao2", alojamiento.getNombre());
	}
	
	@Test
	public void testUbicacion() {
		alojamiento.setUbicacion("Bilbao2");
		assertEquals("Bilbao2", alojamiento.getUbicacion());
	}
	
	@Test
	public void testDescripcion() {
		alojamiento.setDescripcion("Bilbao2");
		assertEquals("Bilbao2", alojamiento.getDescripcion());
	}
	
	@Test
	public void testLongitud() {
		alojamiento.setLongitud(21);
		assertEquals(21, alojamiento.getLongitud(), 0.00001);
	}
	
	@Test
	public void testLatitud() {
		alojamiento.setLatitud(21);
		assertEquals(21, alojamiento.getLatitud(), 0.00001);
	}
	
	@Test
	public void testDesayuno() {
		alojamiento.setDesayuno(21);
		assertEquals(21, alojamiento.getDesayuno(), 0.00001);
	}
	
	@Test
	public void testMediaPension() {
		alojamiento.setMediaPension(21);
		assertEquals(21, alojamiento.getMediaPension(), 0.00001);
	}
	
	@Test
	public void testPensionCompleta() {
		alojamiento.setPensionCompleta(21);
		assertEquals(21, alojamiento.getPensionCompleta(), 0.00001);
	}
	
	@Test
	public void testImgurl() {
		alojamiento.setImgurl("Bilbao2");
		assertEquals("Bilbao2", alojamiento.getImgurl());
	}
	
	@Test
	public void testHabitaciones() {
		habitaciones.add(new Habitacion(1, "hola", 2, 1, 1, 1, 1, 2f, 3f, 4f, ""));
		alojamiento.setHabitaciones(habitaciones);
		assertEquals(habitaciones, alojamiento.getHabitaciones());
	}
	
	@Test
	public void testServicios() {
		servicios.add(new Servicio(1, "wifi", 5f, ""));
		alojamiento.setServicios(servicios);
		assertEquals(servicios, alojamiento.getServicios());
	}
	
	@Test
	public void testToString() {
		assertEquals("Hotel Bilbao (Bilbao)", alojamiento.toString());
	}
}
