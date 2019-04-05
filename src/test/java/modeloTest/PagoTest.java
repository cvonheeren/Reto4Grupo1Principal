package modeloTest;
import static org.junit.Assert.*;
import org.junit.Test;
import modelo.*;

public class PagoTest {

	Pago pago = new Pago();
	
	@Test
	public void testRedimensionarArrayMayor() {
		float[] array = new float[0];
		array = pago.redimensionarArrayMayor(array);
		assertEquals(1, array.length);
	}
	
	@Test
	public void testRedimensionarArrayMenor() {
		float[] array = new float[2];
		array = pago.redimensionarArrayMenor(array);
		assertEquals(1, array.length);
	}
	
	@Test
	public void testFalta() {
		float total = 85.7f;
		float dinero = 83.5f;
		float falta = 2.2f;
		assertEquals(falta, pago.falta(total, dinero), 0);	
	}
	
	@Test
	public void testSobra() {
		float total = 83.5f;
		float dinero = 85.5f;
		float sobra = 2.0f;
		assertEquals(sobra, pago.sobra(total, dinero), 0);	
	}
	
	@Test
	public void testCalcularMonedasBilletes() {
		float sobra = 3.5f;
		String devuelve = "1 monedas de 2€ \n" + "1 monedas de 1€ \n" +  "1 monedas de 0.5€ \n";
		assertEquals(devuelve, pago.calcularMonedasBilletes(sobra));	
	}
	
	@Test
	public void testCalcularMonedasBilletes2() {
		float sobra = 13.5f;
		String devuelve = "1 billetes de 10€ \n" + "1 monedas de 2€ \n" + "1 monedas de 1€ \n" +  "1 monedas de 0.5€ \n";
		assertEquals(devuelve, pago.calcularMonedasBilletes(sobra));	
	}

}
