package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Casa;

public class CasaTest {

	Casa casa = new Casa(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 1, 1.5f, 2, 3, 4, 5, "www.kakota", 1, 1);
	
	@Test
	public void testConstructor1() {
		assertEquals(1, casa.getBanios());
		assertEquals(1, casa.getArea(), 0.00001);
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
