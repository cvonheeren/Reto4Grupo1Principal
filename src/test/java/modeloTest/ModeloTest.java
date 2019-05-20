package modeloTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import modelo.Cliente;
import modelo.GestorArchivos;
import modelo.GestorBBDD;
import modelo.GestorDeFechas;
import modelo.GestorValidaciones;

import modelo.Modelo;
import modelo.GestorDinero;
import modelo.Reserva;

public class ModeloTest {

	private Modelo modelo = new Modelo();
	GestorDinero gestorDinero = new GestorDinero();
	GestorArchivos gestorArchivosMock = mock(GestorArchivos.class);
	GestorBBDD gestorBBDDMock = mock(GestorBBDD.class);
	Reserva reservaMock = mock(Reserva.class);
	Cliente clienteMock = mock(Cliente.class);

	@Test
	public void testConstructor() {
		assertEquals(gestorDinero, modelo.gestorDinero);
		assertEquals(clienteMock, modelo.cliente);
		assertEquals(reservaMock, modelo.reserva);
		assertEquals(gestorBBDDMock, modelo.gestorBBDD);
	}
	
}
