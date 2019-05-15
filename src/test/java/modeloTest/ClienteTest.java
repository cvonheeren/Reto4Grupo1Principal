package modeloTest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import modelo.Cliente;

public class ClienteTest {
	
	Date fecha = new Date(0);
	Cliente cliente = new Cliente(2, "user45", "424234", "njdsvdjv", "nombre", "inventado", fecha, "aaa@gmail.com");
	
	@Test
	public void testConstructor() {
		assertEquals("424234", cliente.getDni());
		assertEquals("njdsvdjv", cliente.getContrasenia());
	}

	@Test
	public void testDni() {
		cliente.setDni("424234");
		assertEquals("424234", cliente.getDni());
	}
	
	@Test
	public void testContra() {
		cliente.setContrasenia("njdsvdjv");
		assertEquals("njdsvdjv", cliente.getContrasenia());
	}
	
	
}
