package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Modelo;
import modelo.Reserva;

public class ModeloTest {

	private Modelo miModelo = new Modelo();
	
	@Test
	public void testAlojamiento() {
		assertNotEquals(null, miModelo.alojamiento);
		assertEquals(Alojamiento.class, miModelo.alojamiento.getClass());
	}
	
	@Test
	public void testHotel() {
		assertNotEquals(null, miModelo.hotel);
		assertEquals(Hotel.class, miModelo.hotel.getClass());
	}
	
	@Test
	public void testReserva() {
		assertNotEquals(null, miModelo.reserva);
		assertEquals(Reserva.class, miModelo.reserva.getClass());
	}
	
	@Test
	public void testHabitacion() {
		assertNotEquals(null, miModelo.habitacion);
		assertEquals(Habitacion.class, miModelo.habitacion.getClass());
	}
	
}
