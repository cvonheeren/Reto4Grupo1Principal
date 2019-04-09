package modeloTest;
import static org.junit.Assert.*;
import org.junit.Test;
import modelo.*;

public class PagoTest {
	
	float dinero = 20;
	float precio = 200;

	Pago pago = new Pago();
	
	@Test
	public void testConstructor() {
		assertEquals(0, pago.getPrecioTotal(), 0);
		assertEquals(0, pago.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testPrecio() {
		pago.setPrecioTotal(precio);
		assertEquals(precio, pago.getPrecioTotal(), 0);
	}
	
	@Test
	public void testSumarDinero() {
		pago.sumarDinero(dinero);
		assertEquals(dinero, pago.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testDevolverDinero() {
		pago.sumarDinero(dinero);
		pago.devolverDinero();
		assertEquals(0, pago.getDineroIntroducido(), 0);
	}
	
	@Test
	public void testCalcularDineroRestante() {
		pago.setPrecioTotal(precio);
		pago.sumarDinero(dinero);
		float falta = 180;
		assertEquals(falta, pago.calcularDineroRestante(), 0);	
	}
	
	@Test
	public void testCalcularDineroSobrante() {
		pago.setPrecioTotal(precio);
		pago.sumarDinero(dinero);
		float sobra = -180;
		assertEquals(sobra, pago.calcularDineroSobrante(), 0);	
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
