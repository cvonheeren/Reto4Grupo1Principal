package modeloTest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import modelo.Habitacion;
import modelo.Reserva;

public class ReservaTest {

	Reserva reserva = new Reserva(1, null, null, null);
	Reserva reserva2 = new Reserva();
	ArrayList<Habitacion> habitacionesReservadas = new ArrayList<Habitacion>();
	
	@Test
	public void testConstructor1() {
		assertEquals(1, reserva.getCodReserva());
		assertEquals(null, reserva.getFechaCompra());
		assertEquals(null, reserva.getFechaEntrada());
		assertEquals(null, reserva.getFechaSalida());
		assertEquals(habitacionesReservadas, reserva.getHabitacionesSeleccionadas());
		assertEquals(0, reserva.getCtdHabitaciones());
	}
	
	@Test
	public void testConstructor2() {
		assertEquals(habitacionesReservadas, reserva2.getHabitacionesSeleccionadas());
	}

	
	@Test
	public void testReserva() {
		reserva.setCodReserva(1);
		assertEquals(1, reserva.getCodReserva());
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
	public void testAlojLista() {
		reserva.setHabitacionesSeleccionadas(habitacionesReservadas);
		assertEquals(habitacionesReservadas, reserva.getHabitacionesSeleccionadas());
	}
	
	@Test
	public void testContador() {
		reserva.setCtdHabitaciones(0);
		assertEquals(0, reserva.getCtdHabitaciones());
	}
	
	@Test
	public void testAddHabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		reserva.setCtdHabitaciones(0);
		reserva.addHabitacion(habitacion);
		assertEquals(1, reserva.getCtdHabitaciones());
	}
	
	@Test
	public void testRemoveHabitacion() {
		Habitacion habitacion = new Habitacion(0, null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		reserva.setCtdHabitaciones(0);
		reserva.addHabitacion(habitacion);
		reserva.removeHabitacion(habitacion);
		assertEquals(0, reserva.getCtdHabitaciones());
	}
}
