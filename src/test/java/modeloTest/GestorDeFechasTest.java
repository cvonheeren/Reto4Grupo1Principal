package modeloTest;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import modelo.GestorDeFechas;

public class GestorDeFechasTest {

	GestorDeFechas gestor = new GestorDeFechas();
	
	@Test
	public void testtipoDeFecha() {
		LocalDate fecha1 = LocalDate.of(2019, 5, 15);
		LocalDate fecha2 = LocalDate.of(2019, 6, 15);
		
		assertTrue(gestor.tipoDeFecha(fecha1));
		assertFalse(gestor.tipoDeFecha(fecha2));
	}
	
	@Test
	public void testComprobarSiEsVerano() {
		LocalDate fecha1 = LocalDate.of(2019, 8, 15);
		LocalDate fecha2 = LocalDate.of(2019, 5, 15);

		assertTrue(gestor.comprobarSiEsVerano(fecha1));
		assertFalse(gestor.comprobarSiEsVerano(fecha2));
	}

	@Test
    public void testSetDiasSelecc() {
		LocalDate fecha1 = LocalDate.of(2019, 8, 15);
		LocalDate fecha2 = LocalDate.of(2019, 8, 16);
		LocalDate fecha3 = LocalDate.of(2019, 8, 17);
        
		ArrayList<LocalDate> dias = new ArrayList<LocalDate>();
       
        dias.add(0, fecha1);
        dias.add(1, fecha2);
        dias.add(2, fecha3);
       
        assertEquals(dias, gestor.setDiasSeleccionados(fecha1, fecha3));
    }
	
	
}
