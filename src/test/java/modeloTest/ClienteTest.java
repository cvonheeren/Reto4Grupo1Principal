package modeloTest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import modelo.Cliente;

public class ClienteTest {
	
	Date fecha = new Date(0);
	Date hoy = new Date(1);
	Cliente cliente = new Cliente(2, "user45", "njdsvdjv");
	
	@Test
	public void testConstructor() {
		assertEquals(2, cliente.getCodCliente());
		assertEquals("user45", cliente.getUser());
		assertEquals("424234", cliente.getDni());
		assertEquals("njdsvdjv", cliente.getContrasenia());
		assertEquals("nombre", cliente.getNombre());
		assertEquals("inventado", cliente.getApellidos());
		assertEquals(fecha, cliente.getFechaNac());
		assertEquals("aaa@gmail.com", cliente.getEmail());
	}
	
	@Test
	public void testConstructor2() {
		assertEquals(2, cliente.getCodCliente());
		assertEquals("user45", cliente.getUser());
		assertEquals("njdsvdjv", cliente.getContrasenia());
	}
	
	@Test
	public void testCodCliente() {
		cliente.setCodCliente(1);
		assertEquals(1, cliente.getCodCliente());
	}
	
	@Test
	public void testUser() {
		cliente.setUser("user45");
		assertEquals("user45", cliente.getUser());
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
	
	@Test
	public void testNombre() {
		cliente.setNombre("nombre2");
		assertEquals("nombre2", cliente.getNombre());
	}
	
	@Test
	public void testApellidos() {
		cliente.setApellidos("njdsvdjv");
		assertEquals("njdsvdjv", cliente.getApellidos());
	}
	
	@Test
	public void testFechaNac() {
		cliente.setFechaNac(hoy);
		assertEquals(hoy, cliente.getFechaNac());
	}
	
	@Test
	public void testEmail() {
		cliente.setEmail("bbb@gmail.com");
		assertEquals("bbb@gmail.com", cliente.getEmail());
	}
	
	
}
