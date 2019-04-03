package ModeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Habitacion;

public class HabitacionTest {

	Habitacion habitacion = new Habitacion(1, 2, 3, 4, "Mascachapas");
	
	@Test
	public void testConstructor() {
		assertEquals(1, habitacion.getCodHabitacion());
		assertEquals(2, habitacion.getNumerohabitacion());
		assertEquals(3, habitacion.getTamano(), 0.000001);
		assertEquals(4, habitacion.getNumeroCamas());
		assertEquals("Mascachapas", habitacion.getTipoCama());
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
	
	@Test
	public void testNumeroCamas() {
		habitacion.setNumeroCamas(4);
		assertEquals(4, habitacion.getNumeroCamas());
	}
	
	@Test
	public void testTipoCama() {
		habitacion.setTipoCama(null);
		assertEquals(null, habitacion.getTipoCama());
	}
}
