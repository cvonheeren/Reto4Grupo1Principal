package modeloTest;

import static org.junit.Assert.*;


import org.junit.Test;

import modelo.GestorArchivos;
import modelo.GestorBBDD;
import modelo.GestorDeFechas;
import modelo.GestorValidaciones;

import modelo.Modelo;
import modelo.GestorDinero;
import modelo.Reserva;

public class ModeloTest {

	Modelo modelo = new Modelo();
	GestorDinero gestorDinero = new GestorDinero();
	GestorBBDD gestorBBDD = new GestorBBDD();
	GestorArchivos gestorArchivos = new GestorArchivos();
	Reserva reserva = new Reserva();
	GestorValidaciones gestorV = new GestorValidaciones();
	GestorDeFechas gestorF = new GestorDeFechas();

	@Test
	public void testConstructor() {
		assertEquals(gestorDinero.getClass(), modelo.gestorDinero.getClass());
		assertEquals(gestorBBDD.getClass(), modelo.gestorBBDD.getClass());
		assertEquals(gestorArchivos.getClass(), modelo.gestorArchivos.getClass());
		assertEquals(reserva.getClass(), modelo.reserva.getClass());
		assertEquals(gestorV.getClass(), modelo.gestorValidaciones.getClass());
		assertEquals(gestorF.getClass(), modelo.gestorFechas.getClass());
	}
	
	@Test
	public void testCliente() {
		assertEquals(null, modelo.cliente);
	}
}
