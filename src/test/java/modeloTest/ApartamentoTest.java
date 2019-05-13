package modeloTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import modelo.Apartamento;
import modelo.Estancia;


public class ApartamentoTest {

	ArrayList<Estancia> estancias = new ArrayList<Estancia>();
	Apartamento apartamento = new Apartamento(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 3, 4, 5, "www.kakota", 1, estancias);
	
	@Test
	public void testConstructor() {
		assertEquals(1, apartamento.getPiso());
		assertEquals(estancias, apartamento.getEstancias());
	}

	@Test
	public void testReserva() {
		apartamento.setPiso(1);
		assertEquals(1, apartamento.getPiso());
	}
	
	@Test
	public void testEstancias() {
		apartamento.setEstancias(estancias);
		assertEquals(estancias, apartamento.getEstancias());
	}
}
