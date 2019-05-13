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
        
//        LocalDate fecha11 = LocalDate.of(2019, 8, 15);
//        Date fecha1 = java.sql.Date.valueOf(fecha11);
//        LocalDate fecha22 = LocalDate.of(2019, 8, 18);
//        Date fecha2 = java.sql.Date.valueOf(fecha22);
        ArrayList<Habitacion> habReservadas = new ArrayList<Habitacion>();
        Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
        habReservadas.add(0, habitacion);
       
        System.out.println(pago.getPrecioTotal(habReservadas));
        assertEquals(1.2f+1.2f+1.2f, pago.getPrecioTotal(habReservadas), 0.00001);   
    }

	
	
	
	
	
	

}
