package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.Controlador;
import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Modelo;
import modelo.Reserva;

public class ModeloTest {

	private Controlador controlador = new Controlador(null, null);
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
	
	@Test
	public void testControlador() {
		assertEquals(Controlador.class, controlador.getClass());
	}
}
