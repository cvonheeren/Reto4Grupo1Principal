package modeloTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import modelo.GestorBBDD;
import modelo.Habitacion;


public class GestorBBDDTest {

	GestorBBDD gestorTest = new GestorBBDD();
	
	@Test
	public void testCargarNombresDestinos() {
		ArrayList<String> aloj = new ArrayList<String>(Arrays.asList("BARCELONA", "BILBAO", "MADRID", "MALAGA"));
		ArrayList<String> test = gestorTest.cargarNombresDestinos();
		assertEquals(aloj, test);
	}
	
	@Test
	public void testCargarNombresAlojamientos() {
		String[] alojT = {"Catalonia sagrada familia", "royal ramblas", "barcelona princess", "ercilla hotel", "gran hotel domine bilbao", "exe plaza", "ayre gran hotel colon", "totem madrid", "soho bahia malaga", "hotel guadalmedina",
				"la casita de pe beach", "casa urondo barri", "windrose 6", "casa mirador de gibralfaro", "bcn rambla catalunya apartments", "two sisters apartments", "bilbao apartamentos atxuri", "room and kitchen bilbao", "slow suites chueca", "aparthotel tribunal", "apartamentos pinar malaga centro", "apartamentos malaga flat"};
		ArrayList<String> alojTest = new ArrayList<String>();
		for (int i = 0; i < alojT.length; i++) {
			alojTest.add(i, alojT[i].toUpperCase());
		}
		ArrayList<String> test2 = gestorTest.cargarNombresAlojamientos();
		assertEquals(test2, alojTest);
	}
	
	@Test
	public void testCargarHabitaciones() {
		String descripcion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris purus nulla, consectetur id elit id, suscipit dictum mauris. Cras est magna, hendrerit eu fringilla ut, feugiat sed lorem. Sed sit amet placerat sapien, at sodales ligula. Proin semper nisl in nibh molestie mattis at at augue. Aenean ac facilisis ante. Fusce in elementum dolor. Aenean luctus blandit mi, et rutrum tortor placerat vel. Praesent dictum aliquam lectus, in viverra risus. Cras vehicula turpis eu metus faucibus, in mattis ";
		Habitacion habitacion = new Habitacion(2, "HABITACION DOBLE MATRIMONIO", 30, 0, 1, 0, 10, 70, 95, 126, descripcion);
		ArrayList<Habitacion> test = new ArrayList<Habitacion>();
		test.add(habitacion);
		ArrayList<Habitacion> test2 = gestorTest.cargarHabitaciones(5);
		assertEquals(test.get(0), test2.get(0));
	}
	
	@Test
	public void testCargarhabitacionesReservadas() {
		Date fecha1 = Date.valueOf(LocalDate.of(2019, 4, 18));
		Date fecha2 = Date.valueOf(LocalDate.of(2019, 4, 19));
		Habitacion habitacion = new Habitacion(1, null, 0, 0, 0, 0, 1, 0, 0, 0, null);
		ArrayList<Habitacion> test = new ArrayList<Habitacion>();
		test.add(habitacion);
		ArrayList<Habitacion> test2 = gestorTest.cargarHabitacionesReservadas(1, fecha1,  fecha2);
		assertEquals(test.get(0), test2.get(0));
	}
	
	@Test
	public void testCargarHabitacionesDisponibles() {
		Date fecha1 = Date.valueOf(LocalDate.of(2019, 4, 16));
		Date fecha2 = Date.valueOf(LocalDate.of(2019, 4, 19));
		Habitacion habitacion = new Habitacion(2, null, 0, 0, 0, 0, 0, 0, 0, 0, null);
		ArrayList<Habitacion> test = new ArrayList<Habitacion>();
		test.add(habitacion);
		ArrayList<Habitacion> test2 = gestorTest.habitacionesDisponibles(1, fecha1, fecha2);
		assertEquals(test.get(0), test2.get(0));
	}
		
	@Test
	public void testComprobarCliente() {
		String user = "PRUEBA";
		String pass1 = "1234";
		String user2 = "1";
		assertTrue(gestorTest.comprobarCliente(user, pass1));
		assertFalse(gestorTest.comprobarCliente(user2, pass1));
	}
	
	@Test
	public void testComprobarDni() {
		String dni1 = "71758055X";
		String dni2 = "1";
		assertTrue(gestorTest.comprobarDni(dni1));
		assertFalse(gestorTest.comprobarDni(dni2));
	}
	
}
