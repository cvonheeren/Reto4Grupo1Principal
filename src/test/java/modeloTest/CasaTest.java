package modeloTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import modelo.Casa;
import modelo.Estancia;

public class CasaTest {

	
	ArrayList<Estancia> estanciasLista = new ArrayList<Estancia>();
	Estancia estancia = new Estancia(1, "", 8, 2);
	
	Casa casa = new Casa(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 3, 4, 5, "www.kakota", estanciasLista);
	
	@Test
	public void testConstructor1() {
		assertEquals(estanciasLista.getClass(), casa.getEstancias().getClass());
	}

	@Test
	public void testEstancias() {
		estanciasLista = new ArrayList<Estancia>();
		casa.setEstancias(estanciasLista);
		assertEquals(estanciasLista, casa.getEstancias());
	}
	
	
}
