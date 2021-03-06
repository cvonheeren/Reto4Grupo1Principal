package modeloTest;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.GestorArchivos;

public class GestorArchivosTest {

	GestorArchivos gestor = new GestorArchivos();
	
	@Test
	public void testpreguntarGuardar() {
		assertEquals(null, gestor.preguntarGuadar());
	}
	
	@Test
	public void testleereBases() {
		String bases = "T�rminos y Condiciones de Uso\r\n" + 
				" \r\n" + 
				"\r\n" + 
				"INFORMACI�N RELEVANTE\r\n" + 
				"\r\n" + 
				"Es requisito necesario para la adquisici�n de los productos que se ofrecen en este sitio, que lea y acepte los siguientes T�rminos y Condiciones que a continuaci�n se redactan. El uso de nuestros servicios as� como la compra de nuestros productos implicar� que usted ha le�do y aceptado los T�rminos y Condiciones de Uso en el presente documento. Todas los productos  que son ofrecidos por nuestro sitio web pudieran ser creadas, cobradas, enviadas o presentadas por una p�gina web tercera y en tal caso estar�an sujetas a sus propios T�rminos y Condiciones. En algunos casos, para adquirir un producto, ser� necesario el registro por parte del usuario, con ingreso de datos personales fidedignos y definici�n de una contrase�a.\r\n" + 
				"\r\n" + 
				"El usuario puede elegir y cambiar la clave para su acceso de administraci�n de la cuenta en cualquier momento, en caso de que se haya registrado y que sea necesario para la compra de alguno de nuestros productos. www.bidaion.eus no asume la responsabilidad en caso de que entregue dicha clave a terceros.\r\n" + 
				"\r\n" + 
				"Todas las compras y transacciones que se lleven a cabo por medio de este sitio web, est�n sujetas a un proceso de confirmaci�n y verificaci�n, el cual podr�a incluir la verificaci�n del stock y disponibilidad de producto, validaci�n de la forma de pago, validaci�n de la factura (en caso de existir) y el cumplimiento de las condiciones requeridas por el medio de pago seleccionado. En algunos casos puede que se requiera una verificaci�n por medio de correo electr�nico.\r\n" + 
				"\r\n" + 
				"Los precios de los productos ofrecidos en esta Tienda Online es v�lido solamente en las compras realizadas en este sitio web.\r\n" + 
				"\r\n" + 
				"LICENCIA\r\n" + 
				"\r\n" + 
				"BiDai-On S.A.  a trav�s de su sitio web concede una licencia para que los usuarios utilicen  los productos que son vendidos en este sitio web de acuerdo a los T�rminos y Condiciones que se describen en este documento.\r\n" + 
				"\r\n" + 
				"USO NO AUTORIZADO\r\n" + 
				"\r\n" + 
				"En caso de que aplique (para venta de software, templetes, u otro producto de dise�o y programaci�n) usted no puede colocar uno de nuestros productos, modificado o sin modificar, en un CD, sitio web o ning�n otro medio y ofrecerlos para la redistribuci�n o la reventa de ning�n tipo.\r\n" + 
				"\r\n" + 
				"PROPIEDAD\r\n" + 
				"\r\n" + 
				"Usted no puede declarar propiedad intelectual o exclusiva a ninguno de nuestros productos, modificado o sin modificar. Todos los productos son propiedad  de los proveedores del contenido. En caso de que no se especifique lo contrario, nuestros productos se proporcionan  sin ning�n tipo de garant�a, expresa o impl�cita. En ning�n esta compa��a ser�  responsables de ning�n da�o incluyendo, pero no limitado a, da�os directos, indirectos, especiales, fortuitos o consecuentes u otras p�rdidas resultantes del uso o de la imposibilidad de utilizar nuestros productos.\r\n" + 
				"\r\n" + 
				"POL�TICA DE REEMBOLSO Y GARANT�A\r\n" + 
				"\r\n" + 
				"En el caso de productos que sean  mercanc�as irrevocables no-tangibles, no realizamos reembolsos despu�s de que se env�e el producto, usted tiene la responsabilidad de entender antes de comprarlo.  Le pedimos que lea cuidadosamente antes de comprarlo. Hacemos solamente excepciones con esta regla cuando la descripci�n no se ajusta al producto. Hay algunos productos que pudieran tener garant�a y posibilidad de reembolso pero este ser� especificado al comprar el producto. En tales casos la garant�a solo cubrir� fallas de f�brica y s�lo se har� efectiva cuando el producto se haya usado correctamente. La garant�a no cubre aver�as o da�os ocasionados por uso indebido. Los t�rminos de la garant�a est�n asociados a fallas de fabricaci�n y funcionamiento en condiciones normales de los productos y s�lo se har�n efectivos estos t�rminos si el equipo ha sido usado correctamente. Esto incluye:\r\n" + 
				"\r\n" + 
				"� De acuerdo a las especificaciones t�cnicas indicadas para cada producto.\r\n" + 
				"� En condiciones ambientales acorde con las especificaciones indicadas por el fabricante.\r\n" + 
				"� En uso espec�fico para la funci�n con que fue dise�ado de f�brica.\r\n" + 
				"� En condiciones de operaci�n el�ctricas acorde con las especificaciones y tolerancias indicadas.\r\n" + 
				"\r\n" + 
				"COMPROBACI�N ANTIFRAUDE\r\n" + 
				"\r\n" + 
				"La compra del cliente puede ser aplazada para la comprobaci�n antifraude. Tambi�n puede ser suspendida por m�s tiempo para una investigaci�n m�s rigurosa, para evitar transacciones fraudulentas.\r\n" + 
				"\r\n" + 
				"PRIVACIDAD\r\n" + 
				"\r\n" + 
				"Este sitio web www.bidaion.eus garantiza que la informaci�n personal que usted env�a cuenta con la seguridad necesaria. Los datos ingresados por usuario o en el caso de requerir una validaci�n de los pedidos no ser�n entregados a terceros, salvo que deba ser revelada en cumplimiento a una orden judicial o requerimientos legales.\r\n" + 
				"\r\n" + 
				"La suscripci�n a boletines de correos electr�nicos publicitarios es voluntaria y podr�a ser seleccionada al momento de crear su cuenta.\r\n" + 
				"\r\n" + 
				"BiDai-On S.A. reserva los derechos de cambiar o de modificar estos t�rminos sin previo aviso.\r\n" + 
				"\r\n" + 
				"Estas terminos y condiciones se han generado en terminosycondicionesdeusoejemplo.com.";
		
		
		assertEquals(bases, gestor.leerBases("BasesLegales.txt"));
	}
}
