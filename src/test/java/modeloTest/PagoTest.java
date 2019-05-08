package modeloTest;
import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;
import modelo.*;

public class PagoTest {
	
	float dinero = 20;
	float precio = 200;

	Pago pago = new Pago();
	
	@Test
	public void testConstructor() {
		assertEquals(0, pago.getPrecioTotal(), 0);
		assertEquals(0, pago.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testPrecio() {
		pago.setPrecioTotal(precio);
		assertEquals(precio, pago.getPrecioTotal(), 0);
	}
	
	@Test
	public void testSumarDinero() {
		pago.sumarDinero(dinero);
		assertEquals(dinero, pago.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testDevolverDinero() {
		pago.sumarDinero(dinero);
		pago.devolverDinero();
		assertEquals(0, pago.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testCalcularDineroRestante() {
		pago.setPrecioTotal(precio);
		pago.sumarDinero(dinero);
		float falta = 180;
		assertEquals(falta, pago.calcularDineroRestante(), 0);	
	}
	
	@Test
	public void testCalcularDineroSobrante() {
		pago.setPrecioTotal(precio);
		pago.sumarDinero(dinero);
		float sobra = -180;
		assertEquals(sobra, pago.calcularDineroSobrante(), 0);	
	}
	
	@Test
	public void testCalcularMonedasBilletes() {
		float sobra = 3.5f;
		String devuelve = "1 monedas de 2€ \n" + "1 monedas de 1€ \n" +  "1 monedas de 0.5€ \n";
		assertEquals(devuelve, pago.calcularMonedasBilletes(sobra));	
	}
	
	@Test
	public void testCalcularMonedasBilletes2() {
		float sobra = 13.5f;
		String devuelve = "1 billetes de 10€ \n" + "1 monedas de 2€ \n" + "1 monedas de 1€ \n" +  "1 monedas de 0.5€ \n";
		assertEquals(devuelve, pago.calcularMonedasBilletes(sobra));	
	}
	
	@Test
    public void testCalcularPrecio() {
        Date fecha1 = new Date(2019, 8, 15);
        Date fecha2 = new Date(2019, 8, 18);
        Alojamiento aloh = new Alojamiento(1, "", "", "", 1, 1, 1, 1.2f, 1.2f, 1.2f, 1, 1, "");
        ArrayList<Habitacion> habReservadas = null;
       
        assertEquals(1.2f+2, pago.calcularPrecio(aloh, fecha1, fecha2, habReservadas), 0.00001);   
    }
	
	
	@Test
    public void testSetDiasSelecc() {
		LocalDate fecha1 = LocalDate.of(2019, 8, 15);
		LocalDate fecha2 = LocalDate.of(2019, 8, 16);
		LocalDate fecha3 = LocalDate.of(2019, 8, 17);
        
 
       
        
		LocalDate[] dias = new LocalDate[3];
       
        dias[1] = fecha1;
        dias[2] = fecha2;
        dias[3] = fecha3;
        
        SimpleDateFormat formmat1 = new SimpleDateFormat("dd-mm-yyyy");
        
        for (int i = 0; i < dias.length; i++) {
			System.out.println(formmat1.format(dias[i]));
        	
		}
       
       
        assertEquals(dias, pago.setDiasSeleccionados(fecha1, fecha2));   
    }
	
	
	

}
