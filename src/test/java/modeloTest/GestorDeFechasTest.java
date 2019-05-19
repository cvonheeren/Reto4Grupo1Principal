package modeloTest;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import modelo.GestorDeFechas;

public class GestorDeFechasTest {

	GestorDeFechas gestor = new GestorDeFechas();
	
	@Test
	public void testComprobarFestivo() {
		LocalDate fecha1 = LocalDate.of(2019, 10, 12);
		LocalDate fecha2 = LocalDate.of(2019, 6, 15);
		
		assertTrue(gestor.comprobarFestivo(fecha1));
		assertFalse(gestor.comprobarFestivo(fecha2));
	}
	
	@Test
	public void testComprobarSiEsVerano() {
		LocalDate fecha1 = LocalDate.of(2019, 8, 15);
		LocalDate fecha2 = LocalDate.of(2019, 5, 15);
		LocalDate fecha3 = LocalDate.of(2019, 12, 15);

		assertTrue(gestor.comprobarSiEsVerano(fecha1));
		assertFalse(gestor.comprobarSiEsVerano(fecha2));
		assertFalse(gestor.comprobarSiEsVerano(fecha3));
	}

	@Test
    public void testSetDiasSelecc() {
		LocalDate fecha1 = LocalDate.of(2019, 8, 15);
		LocalDate fecha2 = LocalDate.of(2019, 8, 16);
		LocalDate fecha3 = LocalDate.of(2019, 8, 17);
        
		ArrayList<LocalDate> dias = new ArrayList<LocalDate>();
        
		dias.add(fecha1);
        dias.add(fecha2);
       
        assertEquals(dias, gestor.setDiasSeleccionados(fecha1, fecha3));
    }
	
	
}
