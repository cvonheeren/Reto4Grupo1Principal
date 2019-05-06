package modeloTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import modelo.GestorDeFechas;

public class GestorDeFechasTest {

	GestorDeFechas gestor = new GestorDeFechas();
	
	@Test
	public void testtipoDeFecha() {
		Calendar fecha1 = new GregorianCalendar();
        fecha1.set(2019, 5, 15);
		
		Calendar fecha2 = new GregorianCalendar();
        fecha2.set(2019, 6, 30);
		
		assertTrue(gestor.tipoDeFecha(fecha1));
		assertFalse(gestor.tipoDeFecha(fecha2));
	}
	
	@Test
	public void testComprobarSiEsVerano() {
		Calendar fecha = new GregorianCalendar();
        fecha.set(2019, 8, 15);
        Calendar fecha2 = new GregorianCalendar();
        fecha2.set(2019, 9, 15);

		assertTrue(gestor.comprobarSiEsVerano(fecha));
		assertFalse(gestor.comprobarSiEsVerano(fecha2));
	}

}
