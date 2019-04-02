package ModeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Modelo.Reserva;

public class ReservaTest {

	Reserva reserva = new Reserva(1, null, 400.34f);
	
	@Test
	public void testConstructor() {
		assertEquals(1, reserva.getCodReserva());
		assertEquals(null, reserva.getFecha());
		assertEquals(400.34f, reserva.getPrecio(), 0.000001);
	}

	@Test
	public void testReserva() {
		reserva.setCodReserva(1);
		assertEquals(1, reserva.getCodReserva());
	}
	
	@Test
	public void testFecha() {
		reserva.setFecha(null);
		assertEquals(null, reserva.getFecha());
	}
	
	@Test
	public void testPrecio() {
		reserva.setPrecio(400.34f);
		assertEquals(400.34f, reserva.getPrecio(), 0.000001);
	}
}
