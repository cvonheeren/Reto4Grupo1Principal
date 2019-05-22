package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;
import modelo.GestorValidaciones;

public class GestorValidacionesTest {

	GestorValidaciones gestor = new GestorValidaciones();
	
	@Test
	public void testValidarNif() {
		String dni1 = "48359605N";
		String dni2 = "3fedswf";
		String dni3 = "48359605x";
		
		assertTrue(gestor.validarNif(dni1));
		assertFalse(gestor.validarNif(dni2));
		assertFalse(gestor.validarNif(dni3));
	}

	@Test
	public void testValidarPass() {
		String pass1 = "Elretomola#1";
		String pass2 = "3fedswf";
		
		assertTrue(gestor.validarPass(pass1));
		assertFalse(gestor.validarPass(pass2));
	}
	
//	JFXTextField textFieldDNI = mock(JFXTextField.class);
//	AnchorPane paneLogin = mock(AnchorPane.class);
//	JFXPasswordField contrasena = mock(JFXPasswordField.class);
//	
//	@Test
//	public void testDatosLogin() {
//		when(textFieldDNI.getText()).thenReturn("48359605N");
//		when(contrasena.getText()).thenReturn("dawdwqd");
//		
//		assertTrue(gestor.validarDatosLogin(textFieldDNI, paneLogin, contrasena));
//		
//	}
}
