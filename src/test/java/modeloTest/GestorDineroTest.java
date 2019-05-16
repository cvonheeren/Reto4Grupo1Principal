package modeloTest;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;


import org.junit.Test;
import modelo.*;

public class GestorDineroTest {
	
	float dinero = 20;
	float precio = 200;

	GestorDinero gestorDinero = new GestorDinero();
	
	@Test
	public void testConstructor() {
		assertEquals(0, gestorDinero.getDineroIntroducido(), 0.000001);
		assertEquals(0, gestorDinero.getDineroRestante(),  0.000001);
	}
	
	@Test
	public void testPrecio() {
		gestorDinero.setPrecio(precio);
		assertEquals(precio, gestorDinero.getPrecio(), 0);
	}
	
	@Test
	public void testSumarDinero() {
		gestorDinero.sumarDinero(dinero);
		assertEquals(dinero, gestorDinero.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testDevolverDinero() {
		gestorDinero.sumarDinero(dinero);
		gestorDinero.devolverDinero();
		assertEquals(0, gestorDinero.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testCalcularDineroRestante() {
		gestorDinero.setPrecio(precio);
		gestorDinero.sumarDinero(dinero);
		float falta = 180;
		assertEquals(falta, gestorDinero.calcularDineroRestante(), 0);	
	}
	
	@Test
	public void testCalcularDineroSobrante() {
		gestorDinero.setPrecio(precio);
		gestorDinero.sumarDinero(dinero);
		float sobra = -180;
		assertEquals(sobra, gestorDinero.calcularDineroSobrante(), 0);	
	}
	
	@Test
	public void testCalcularMonedasBilletes() {
		float sobra = 3.5f;
		String devuelve = "1 monedas de 2€ \n" + "1 monedas de 1€ \n" +  "1 monedas de 0.5€ \n";
		assertEquals(devuelve, gestorDinero.calcularMonedasBilletes(sobra));	
	}
	
	@Test
	public void testCalcularMonedasBilletes2() {
		float sobra = 13.5f;
		String devuelve = "1 billetes de 10€ \n" + "1 monedas de 2€ \n" + "1 monedas de 1€ \n" +  "1 monedas de 0.5€ \n";
		assertEquals(devuelve, gestorDinero.calcularMonedasBilletes(sobra));	
	}
	
	@Test
    public void testCalcularPrecioConDescuentos() {
        
//        LocalDate fecha11 = LocalDate.of(2019, 8, 15);
//        Date fecha1 = java.sql.Date.valueOf(fecha11);
//        LocalDate fecha22 = LocalDate.of(2019, 8, 18);
//        Date fecha2 = java.sql.Date.valueOf(fecha22);
        ArrayList<Habitacion> habReservadas = new ArrayList<Habitacion>();
        Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
        habReservadas.add(0, habitacion);
      
        assertEquals(1.2f+1.2f+1.2f, gestorDinero.calcularPrecioConDescuentos(habReservadas), 0.00001);   
    }

	@Test
	public void testgetprecioTotalhabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		
		assertEquals(1, gestorDinero.getPrecioTotalHabitacion(habitacion), 0.000001);	
	}
	
	@Test
	public void testgetprecioDiahabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		LocalDate fecha = LocalDate.of(2019, 8, 15);
		
		assertEquals(1.2f, gestorDinero.getPrecioDiaHabitacion(habitacion, fecha), 0.000001);	
	}
	
	@Test
	public void testgetHabBarata() {
		ArrayList<Habitacion> habReservadas = new ArrayList<Habitacion>();
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		Habitacion habitacion2 = new Habitacion(0, null, 0, 0, 0, 0, 0, 0.9f, 1.1f, 1.1f, null);
		habReservadas.add(0, habitacion);
		habReservadas.add(1, habitacion2);
		
		assertEquals(habitacion2, gestorDinero.getHabBarata(habReservadas));	
	}
	
	
	

}
