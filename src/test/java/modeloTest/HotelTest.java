package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Hotel;

public class HotelTest {

	Hotel hotel = new Hotel(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, "www.kakota", 4);
	
	@Test
	public void testConstructor() {
		assertEquals(4, hotel.getEstrellas());
	}
	
	@Test
	public void testEstrellas() {
		hotel.setEstrellas(4);
		assertEquals(4, hotel.getEstrellas());
	}
}
