package modeloTest;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;


import org.junit.Test;
import modelo.*;

public class GestorDineroTest {
	
	private float dinero = 20;
	private float precio = 200;
	GestorDinero gestorDinero = null;
	
	public GestorDineroTest() {
		gestorDinero = new GestorDinero();
	}
	
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
	public void testDescuento() {
		gestorDinero.setDescuento(10);
		assertEquals(10, gestorDinero.getDescuento(), 0);
	}
	
	@Test
	public void testPrecioConDescuento() {
		gestorDinero.setPrecio(precio);
		gestorDinero.setDescuento(10);
		assertEquals(190, gestorDinero.getPrecioConDescuento(), 0);
	}
	
	@Test
	public void testDineroIntroducido() {
		gestorDinero.setDineroIntroducido(precio);
		assertEquals(precio, gestorDinero.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testDineroRestante() {
		gestorDinero.setDineroRestante(precio);
		assertEquals(precio, gestorDinero.getDineroRestante(), 0);
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
		assertEquals(180, gestorDinero.calcularDineroRestante(), 0);	
	}
	
	@Test
	public void testCalcularDineroRestante2() {
		gestorDinero.setPrecio(precio);
		gestorDinero.sumarDinero(300);
		assertEquals(0, gestorDinero.calcularDineroRestante(), 0);	
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
        LocalDate fecha1 = LocalDate.of(2019, 8, 15);
        LocalDate fecha2 = LocalDate.of(2019, 8, 18);
        ArrayList<Habitacion> habReservadas = new ArrayList<Habitacion>();
        Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 1, 1, 1.2f, 1.2f, null);
        habReservadas.add(habitacion);
        ArrayList<Servicio> servicios = new ArrayList<Servicio>();
        Servicio servicio = new Servicio(0, "WIFI", 1.2f, "");
        servicios.add(servicio);
        assertEquals(1.2*2, gestorDinero.calcularPrecioConDescuentos(habReservadas, servicios, fecha1, fecha2), 0.001);
    }

	@Test
	public void testgetprecioTotalhabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 1, 1, 1.2f, 1.2f, null);
		LocalDate fecha1 = LocalDate.of(2019, 8, 15);
        LocalDate fecha2 = LocalDate.of(2019, 8, 18);
		assertEquals(1.2f, gestorDinero.getPrecioTotalHabitacion(habitacion, fecha1, fecha2), 0.000001);	
	}
	
	@Test
	public void testgetprecioDiahabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 1, 1, 1.2f, 1.2f, null);
		LocalDate fecha = LocalDate.of(2019, 8, 15);
		assertEquals(1.2f, gestorDinero.getPrecioDiaHabitacion(habitacion, fecha), 0.000001);	
	}
	
	@Test
	public void testgetHabBarata() {
		LocalDate fecha = LocalDate.of(2019, 8, 15);
		ArrayList<Habitacion> habReservadas = new ArrayList<Habitacion>();
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 1, 1.2f, 1.2f, null);
		Habitacion habitacion2 = new Habitacion(0, null, 0, 0, 0, 0, 0, 0.9f, 1.1f, 1.1f, null);
		habReservadas.add(habitacion);
		habReservadas.add(habitacion2);
		assertEquals(habitacion2, gestorDinero.getHabBarata(habReservadas, fecha));	
	}
	
	
	

}
