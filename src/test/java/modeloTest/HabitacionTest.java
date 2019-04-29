package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Habitacion;

public class HabitacionTest {

	Habitacion habitacion = new Habitacion(1, "Eren", 2, 3, 4, 0, 1);
	
	@Test
	public void testConstructor() {
		assertEquals(1, habitacion.getCodHabitacion());
		assertEquals("Eren", habitacion.getNombre());
		assertEquals(2, habitacion.getCtaCamasSimples());
		assertEquals(3, habitacion.getCtaCamasMatrimonio());
		assertEquals(4, habitacion.getCtaCamasInfantil());
		assertEquals(0, habitacion.getTamano(), 0.000001);
		assertEquals(1, habitacion.getCantidad());
	}

	@Test
	public void testCodHabitacion() {
		habitacion.setCodHabitacion(1);
		assertEquals(1, habitacion.getCodHabitacion());
	}
	
	@Test
	public void testNumerohabitacion() {
		habitacion.setNumerohabitacion(2);
		assertEquals(2, habitacion.getNumerohabitacion());
	}
	
	@Test
	public void testTamano() {
		habitacion.setTamano(3);
		assertEquals(3, habitacion.getTamano(), 0.000001);
	}
	
	
}
