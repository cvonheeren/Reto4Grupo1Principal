package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;


import modelo.Cliente;
import modelo.GestorArchivos;
import modelo.GestorBBDD;
import modelo.Habitacion;

import modelo.Modelo;
import modelo.Pago;
import modelo.Reserva;

public class ModeloTest {

	private Modelo miModelo = new Modelo();
	
	@Test
	public void testpago() {
		assertNotEquals(null, miModelo.pago);
		assertEquals(Pago.class, miModelo.pago.getClass());
	}
	
	@Test
	public void testcliente() {
		assertEquals(null, miModelo.cliente);
		assertNotEquals(Cliente.class, miModelo.cliente.getClass());
	}
	
	@Test
	public void testReserva() {
		assertNotEquals(null, miModelo.reserva);
		assertEquals(Reserva.class, miModelo.reserva.getClass());
	}
	
	@Test
	public void testHabitacion() {
		assertEquals(null, miModelo.habitacion);
		assertNotEquals(Habitacion.class, miModelo.habitacion.getClass());
	}
	
	@Test
	public void testgestorBBDD() {
		assertNotEquals(null, miModelo.gestorBBDD);
		assertEquals(GestorBBDD.class, miModelo.gestorBBDD.getClass());
	}
	
	@Test
	public void testgenerarFactura() {
		assertNotEquals(null, miModelo.generarFactura);
		assertEquals(GestorArchivos.class, miModelo.generarFactura.getClass());
	}
	
}
