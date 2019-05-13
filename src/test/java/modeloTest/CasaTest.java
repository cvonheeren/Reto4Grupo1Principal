package modeloTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import modelo.Casa;
import modelo.Estancia;

public class CasaTest {

	Estancia estancia = new Estancia(1, "", 8, 2);
	ArrayList<Estancia> estancias = new ArrayList<Estancia>();
	
	Casa casa = new Casa(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 1, 3, 4, 5, "www.kakota", estancias);
	
	@Test
	public void testConstructor1() {
		assertEquals(estancias.getClass(), casa.getEstancias().getClass());
	}

	@Test
	public void testEstancias() {
		estancias = new ArrayList<Estancia>();
		casa.setEstancias(estancias);
		assertEquals(estancias, casa.getEstancias());
	}
	
	@Test
	public void testBanios() {
		casa.setBanios(1);
		assertEquals(1, casa.getBanios());
	}
	
	@Test
	public void testArea() {
		casa.setArea(1);
		assertEquals(1, casa.getArea(), 0.00001);
	}
}
