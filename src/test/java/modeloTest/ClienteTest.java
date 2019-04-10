package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Cliente;

public class ClienteTest {

	Cliente cliente = new Cliente("424234", "njdsvdjv");
	
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
		assertEquals("4242njdsvdjv34", cliente.getContrasenia());
	}
	
	@Test
	public void testValidacion() {
		assertEquals(false, cliente.validacion());
		
		cliente.setDni("1234567X");
		cliente.setContrasenia("Christian");
		assertEquals(true, cliente.validacion());
	}
}
