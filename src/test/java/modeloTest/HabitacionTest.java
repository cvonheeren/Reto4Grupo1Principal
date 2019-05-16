package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Habitacion;

public class HabitacionTest {

	Habitacion habitacion = new Habitacion(1, "", 2, 3, 1, 2, 3, 4f, 2f, 1f, "");
	
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
		assertEquals(1, habitacion.getCodEstancia());
		assertEquals(3, habitacion.getCantidad());
		
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
		habitacion.setDescripcion("");;
		assertEquals("", habitacion.getDescripcion());
	}
	
	
	
	
}
