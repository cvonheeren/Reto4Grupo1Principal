package modeloTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Reserva;

public class ReservaTest {

	Reserva reserva = new Reserva(1, 1, 1, null, null, null, 400.34f);
	Reserva reserva2 = new Reserva();
	ArrayList<Habitacion> habitacionesReservadas = new ArrayList<Habitacion>();
	Alojamiento alojTest = new Alojamiento();
	
	@Test
	public void testConstructor1() {
		assertEquals(1, reserva.getCodReserva());
		assertEquals(1, reserva.getCodCliente());
		assertEquals(1, reserva.getCodAlojamiento());
		assertEquals(null, reserva.getFechaCompra());
		assertEquals(null, reserva.getFechaEntrada());
		assertEquals(null, reserva.getFechaSalida());
		assertEquals(400.34f, reserva.getPrecio(), 0.000001);
		assertEquals(habitacionesReservadas, reserva.getHabitacionesReservadas());
	}
	
	@Test
	public void testConstructor2() {
		assertEquals(habitacionesReservadas, reserva2.getHabitacionesReservadas());
	}

	@Test
	public void testtipoDeFecha() {
		Calendar fecha1 = new GregorianCalendar();
        fecha1.set(2019, 5, 15);
		
		Calendar fecha2 = new GregorianCalendar();
        fecha2.set(2019, 6, 30);
		
		assertTrue(reserva.tipoDeFecha(fecha1));
		assertFalse(reserva.tipoDeFecha(fecha2));
	}
	
	@Test
	public void testComprobarSiEsVerano() {
		Calendar fecha = new GregorianCalendar();
        fecha.set(2019, 8, 15);
        Calendar fecha2 = new GregorianCalendar();
        fecha2.set(2019, 9, 15);

		assertTrue(reserva.comprobarSiEsVerano(fecha));
		assertFalse(reserva.comprobarSiEsVerano(fecha2));
	}
	
	@Test
	public void testReserva() {
		reserva.setCodReserva(1);
		assertEquals(1, reserva.getCodReserva());
	}
	
	@Test
	public void testCliente() {
		reserva.setCodCliente(1);
		assertEquals(1, reserva.getCodCliente());
	}
	
	@Test
	public void testAlojamiento() {
		reserva.setCodAlojamiento(1);
		assertEquals(1, reserva.getCodAlojamiento());
	}
	
	@Test
	public void testFechaCompra() {
		reserva.setFechaCompra(null);
		assertEquals(null, reserva.getFechaCompra());
	}
	
	@Test
	public void testFechaEntrada() {
		reserva.setFechaEntrada(null);
		assertEquals(null, reserva.getFechaEntrada());
	}
	
	@Test
	public void testFecha3() {
		reserva.setFechaSalida(null);
		assertEquals(null, reserva.getFechaSalida());
	}
	
	@Test
	public void testPrecio() {
		reserva.setPrecio(400.34f);
		assertEquals(400.34f, reserva.getPrecio(), 0.000001);
	}
	
	@Test
	public void testAlojLista() {
		reserva.setHabitacionesReservadas(habitacionesReservadas);
		assertEquals(habitacionesReservadas, reserva.getHabitacionesReservadas());
	}
	
	@Test
	public void testAloj() {
		reserva.setAlojamiento(alojTest);;
		assertEquals(alojTest, reserva.getAlojamiento());
	}
}
