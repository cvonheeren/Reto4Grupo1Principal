package modeloTest;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;


import org.junit.Test;
import modelo.*;

public class PagoTest {
	
	float dinero = 20;
	float precio = 200;

	GestorDinero pago = new GestorDinero();
	
	@Test
	public void testConstructor() {
		assertEquals(0, pago.getDineroIntroducido(), 0.000001);
		assertEquals(0, pago.getDineroRestante(),  0.000001);
	}
	
	@Test
	public void testPrecio() {
		pago.setPrecio(precio);
		assertEquals(precio, pago.getPrecio(), 0);
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
		pago.setPrecio(precio);
		pago.sumarDinero(dinero);
		float falta = 180;
		assertEquals(falta, pago.calcularDineroRestante(), 0);	
	}
	
	@Test
	public void testCalcularDineroSobrante() {
		pago.setPrecio(precio);
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
       
        System.out.println(pago.calcularPrecioConDescuentos(habReservadas));
        assertEquals(1.2f+1.2f+1.2f, pago.calcularPrecioConDescuentos(habReservadas), 0.00001);   
    }

	@Test
	public void testgetprecioTotalhabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		
		assertEquals(1, pago.getPrecioTotalHabitacion(habitacion), 0.000001);	
	}
	
	@Test
	public void testgetprecioDiahabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		LocalDate fecha = LocalDate.of(2019, 8, 15);
		
		assertEquals(1.2f, pago.getPrecioDiaHabitacion(habitacion, fecha), 0.000001);	
	}
	
	@Test
	public void testgetHabBarata() {
		ArrayList<Habitacion> habReservadas = new ArrayList<Habitacion>();
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		Habitacion habitacion2 = new Habitacion(0, null, 0, 0, 0, 0, 0, 0.9f, 1.1f, 1.1f, null);
		habReservadas.add(0, habitacion);
		habReservadas.add(1, habitacion2);
		
		assertEquals(habitacion2, pago.getHabBarata(habReservadas));	
	}
	
	
	

}
