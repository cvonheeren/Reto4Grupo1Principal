package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Habitacion;

public class HabitacionTest {

	Habitacion habitacion = new Habitacion(1, "", 2, 3, 1, 2, 3, 4f, 2f, 1f, "");
	Habitacion habitacion2 = new Habitacion(1, 3);
	
	@Test
	public void testConstructor() {
		assertEquals(3, habitacion.getCtaCamasSimples());
		assertEquals(1, habitacion.getCtaCamasMatrimonio());
		assertEquals(2, habitacion.getCtaCamasInfantil());
		assertEquals(4f, habitacion.getTarifaNormal(), 0.000001);
		assertEquals(2f, habitacion.getTarifaVerano(), 0.000001);
		assertEquals(1f, habitacion.getTarifaFestivo(), 0.000001);
		assertEquals("", habitacion.getDescripcion());
	}
	
	@Test
	public void testConstructor2() {
		assertEquals(1, habitacion2.getCodEstancia());
		assertEquals(3, habitacion2.getCantidad());
	}

	@Test
	public void testCtdaCmsSmpls() {
		habitacion.setCtaCamasSimples(1);
		assertEquals(1, habitacion.getCtaCamasSimples());
	}
	
	@Test
	public void testCtdaCmsmatrm() {
		habitacion.setCtaCamasMatrimonio(1);
		assertEquals(1, habitacion.getCtaCamasMatrimonio());
	}
	
	@Test
	public void testCtdaCmsinfan() {
		habitacion.setCtaCamasInfantil(1);
		assertEquals(1, habitacion.getCtaCamasInfantil());
	}
	
	@Test
	public void testtarifaNormal() {
		habitacion.setTarifaNormal(2);
		assertEquals(2, habitacion.getTarifaNormal(),  0.000001);
	}
	
	@Test
	public void testtarifaVerano() {
		habitacion.setTarifaVerano(2);
		assertEquals(2, habitacion.getTarifaVerano(),  0.000001);
	}
	
	@Test
	public void testtarifafestivo() {
		habitacion.setTarifaFestivo(2);
		assertEquals(2, habitacion.getTarifaFestivo(),  0.000001);
	}
	
	@Test
	public void testdescri() {
		habitacion.setDescripcion("");
		assertEquals("", habitacion.getDescripcion());
	}
	
	@Test
	public void testNumAdultos() {
		habitacion.setCtaCamasSimples(2);
		habitacion.setCtaCamasMatrimonio(2);
		assertEquals(6, habitacion.getNumAdultos());
	}
	
	@Test
	public void testEquals() {
		Habitacion habitacion = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, null);
		Habitacion habitacion2 = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, null);
		assertEquals(habitacion, habitacion2);
	}
	
	@Test
	public void testEquals2() {
		Habitacion habitacion = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, "aaa");
		Habitacion habitacion2 = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, null);
		assertNotEquals(habitacion, habitacion2);
	}
	
	@Test
	public void testEquals3() {
		Habitacion habitacion = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 40, 0, 1, 0, 10, 70, 95, 126, null);
		Habitacion habitacion2 = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, null);
		assertNotEquals(habitacion, habitacion2);
	}
	
	@Test
	public void testEquals4() {
		Habitacion habitacion = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 80, 95, 126, null);
		Habitacion habitacion2 = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, null);
		assertNotEquals(habitacion, habitacion2);
	}
	
}
