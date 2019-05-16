package modeloTest;

import static org.junit.Assert.*;

import java.sql.Date;

import java.util.ArrayList;

import org.junit.Test;

import modelo.Alojamiento;
import modelo.GestorBBDD;
import modelo.Habitacion;


public class GestorBBDDTest {

	GestorBBDD gestorTest = new GestorBBDD();
	
	@Test
	public void testCargarNombresDestinos() {
		String[] alojamientos = {"Barcelona", "Bilbao", "Madrid", "Malaga", "Valencia", "Zaragoza", "Amsterdan", "Berlin", "Londres", "Paris", "Roma"};
		ArrayList<String> aloj = new ArrayList<String>();
		for (int i = 0; i < alojamientos.length; i++) {
			aloj.add(i, alojamientos[i].toUpperCase());
		}
		ArrayList<String> test = gestorTest.cargarNombresDestinos();
		assertEquals(aloj.getClass(), test.getClass());
	}

	@Test
	public void testCargarAlojamientos1() {
		String[] tipos = {"C", "H"};
		ArrayList<Alojamiento> alojamientos = gestorTest.cargarAlojamientos("Bilbao", 1, 4, tipos);
		assertEquals(alojamientos.get(0).getNombre(), "ERCILLA HOTEL");
	}
	
	@Test
	public void testCargarAlojamientos2() {
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
	public void testCargarListaHabitaciones() {
		ArrayList<Habitacion> test = new ArrayList<Habitacion>();
		ArrayList<Habitacion> test2 = gestorTest.cargarHabitaciones(1);
		assertEquals(test2.getClass(), test.getClass());
	}
	
	@Test
	public void testCargarhabitacionesReservadas() {
		ArrayList<Habitacion> test = new ArrayList<Habitacion>();
		//Poner las fechas de una reserva de verdad para cubrir todo
		ArrayList<Habitacion> test2 = gestorTest.cargarHabitacionesReservadas(1, new Date(2019,04,30),  new Date(2019,05,1));
		assertEquals(test2.getClass(), test.getClass());
	}
	
	@Test
	public void testCargarHabitacionesDisponibles() {
		ArrayList<Habitacion> test = new ArrayList<Habitacion>();
		ArrayList<Habitacion> test2 = gestorTest.habitacionesDisponibles(1, new Date(2019,04,30), new Date(2019,05,1));
		assertEquals(test2.getClass(), test.getClass());
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
