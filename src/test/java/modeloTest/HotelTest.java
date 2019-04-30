package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Hotel;

public class HotelTest {

	Hotel hotel = new Hotel(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, 1, 1.5f, 2, 3, 4, 5, 2, "www.kakota");
	
	@Test
	public void testConstructor() {
		assertEquals(2, hotel.getEstrellas());
	}
	
	@Test
	public void testEstrellas() {
		hotel.setEstrellas(4);
		assertEquals(4, hotel.getEstrellas());
	}
}
