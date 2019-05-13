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
	public void testCargarListaDestinos() {
		String[] alojamientos = {"Barcelona", "Bilbao", "Madrid", "Malaga", "Valencia", "Zaragoza", "Amsterdan", "Berlin", "Londres", "Paris", "Roma"};
		ArrayList<String> aloj = new ArrayList<String>();
		for (int i = 0; i < alojamientos.length; i++) {
			aloj.add(i, alojamientos[i].toUpperCase());
			//por si quiero ver los elementos en consola
			//System.out.println(aloj.get(i));
		}
		
		ArrayList<String> test = gestorTest.cargarNombresDestinos();
		
		assertEquals(aloj, test);
	}

	@Test
	public void testCargarListaAlojamientos1() {
		ArrayList<Alojamiento> test = new ArrayList<Alojamiento>();
		ArrayList<Alojamiento> test2 = gestorTest.cargarAlojamientos("Bilbao");
		
		assertEquals(test2.getClass(), test.getClass());
	}
	
	@Test
	public void testCargarListaAlojamientos2() {
		String[] alojT = {"Catalonia sagrada familia", "royal ramblas", "barcelona princess", "ercilla hotel", "gran hotel domine bilbao", "exe plaza", "ayre gran hotel colon", "totem madrid", "soho bahia malaga", "hotel guadalmedina"};
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
		String dni1 = "79066869E";
		String pass1 = "1234";
		String dni2 = "1";
		
		assertTrue(gestorTest.comprobarCliente(dni1, pass1));
		assertFalse(gestorTest.comprobarCliente(dni2, pass1));
	}
	
	@Test
	public void testComprobarDni() {
		String dni1 = "79066869E";
		String dni2 = "1";
		
		assertTrue(gestorTest.comprobarDni(dni1));
		assertFalse(gestorTest.comprobarDni(dni2));
	}
}
