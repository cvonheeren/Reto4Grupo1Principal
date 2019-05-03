package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;


import modelo.Apartamento;


public class ApartamentoTest {

	Apartamento apartamento = new Apartamento(1, "Bilbao", "Hotel Bilbao", "cremas", 20f, 20f, 1f, 1.5f, 2f, 3f, 4f, 5f, "www.kakota", 1);
	
	@Test
	public void testConstructor() {
		assertEquals(1, apartamento.getPiso());
	}

	@Test
	public void testReserva() {
		apartamento.setPiso(1);
		assertEquals(1, apartamento.getPiso());
	}
}
