package modeloTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;

public class CasaTest {

	
	ArrayList<Estancia> estancias = null;
	ArrayList<Habitacion> habitaciones = null;
	Estancia estancia = null;
	Estancia estancia2 = null;
	Habitacion habitacion = null;
	Casa casa = null;
	
	public CasaTest() {
		estancias = new ArrayList<Estancia>();
		habitaciones = new ArrayList<Habitacion>();
		estancia = new Estancia(1, "", 8, 2);
		estancia2 = new Estancia(2, "estancia", 6, 4);
		habitacion = new Habitacion(1, "hola", 2, 1, 1, 1, 1, 2f, 3f, 4f, "");
		estancias.add(estancia);
		
		casa = new Casa(1, "Bilbao", "Hotel Bilbao", "cremas", 20, 20, "www.kakota", estancias);
	}
	
	@Test
	public void testConstructor1() {
		assertEquals(estancias, casa.getEstancias());
	}

	@Test
	public void testEstancias() {
		estancias.add(estancia2);
		casa.setEstancias(estancias);
		assertEquals(estancias, casa.getEstancias());
	}
	
	@Test
	public void testCalcularArea() {
		habitaciones.add(habitacion);
		casa.setHabitaciones(habitaciones);
		float area = 8*2 + 2*1;
		assertEquals(area, casa.calcularArea(), 0);
	}
	
	@Test
	public void testCalcularAreaNull() {
		casa.setEstancias(null);
		casa.setHabitaciones(null);
		assertEquals(0, casa.calcularArea(), 0);
	}
	
	
}
