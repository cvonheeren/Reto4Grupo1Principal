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
		String bases = "Términos y Condiciones de Uso\r\n" + 
				" \r\n" + 
				"\r\n" + 
				"INFORMACIÓN RELEVANTE\r\n" + 
				"\r\n" + 
				"Es requisito necesario para la adquisición de los productos que se ofrecen en este sitio, que lea y acepte los siguientes Términos y Condiciones que a continuación se redactan. El uso de nuestros servicios así como la compra de nuestros productos implicará que usted ha leído y aceptado los Términos y Condiciones de Uso en el presente documento. Todas los productos  que son ofrecidos por nuestro sitio web pudieran ser creadas, cobradas, enviadas o presentadas por una página web tercera y en tal caso estarían sujetas a sus propios Términos y Condiciones. En algunos casos, para adquirir un producto, será necesario el registro por parte del usuario, con ingreso de datos personales fidedignos y definición de una contraseña.\r\n" + 
				"\r\n" + 
				"El usuario puede elegir y cambiar la clave para su acceso de administración de la cuenta en cualquier momento, en caso de que se haya registrado y que sea necesario para la compra de alguno de nuestros productos. www.bidaion.eus no asume la responsabilidad en caso de que entregue dicha clave a terceros.\r\n" + 
				"\r\n" + 
				"Todas las compras y transacciones que se lleven a cabo por medio de este sitio web, están sujetas a un proceso de confirmación y verificación, el cual podría incluir la verificación del stock y disponibilidad de producto, validación de la forma de pago, validación de la factura (en caso de existir) y el cumplimiento de las condiciones requeridas por el medio de pago seleccionado. En algunos casos puede que se requiera una verificación por medio de correo electrónico.\r\n" + 
				"\r\n" + 
				"Los precios de los productos ofrecidos en esta Tienda Online es válido solamente en las compras realizadas en este sitio web.\r\n" + 
				"\r\n" + 
				"LICENCIA\r\n" + 
				"\r\n" + 
				"BiDai-On S.A.  a través de su sitio web concede una licencia para que los usuarios utilicen  los productos que son vendidos en este sitio web de acuerdo a los Términos y Condiciones que se describen en este documento.\r\n" + 
				"\r\n" + 
				"USO NO AUTORIZADO\r\n" + 
				"\r\n" + 
				"En caso de que aplique (para venta de software, templetes, u otro producto de diseño y programación) usted no puede colocar uno de nuestros productos, modificado o sin modificar, en un CD, sitio web o ningún otro medio y ofrecerlos para la redistribución o la reventa de ningún tipo.\r\n" + 
				"\r\n" + 
				"PROPIEDAD\r\n" + 
				"\r\n" + 
				"Usted no puede declarar propiedad intelectual o exclusiva a ninguno de nuestros productos, modificado o sin modificar. Todos los productos son propiedad  de los proveedores del contenido. En caso de que no se especifique lo contrario, nuestros productos se proporcionan  sin ningún tipo de garantía, expresa o implícita. En ningún esta compañía será  responsables de ningún daño incluyendo, pero no limitado a, daños directos, indirectos, especiales, fortuitos o consecuentes u otras pérdidas resultantes del uso o de la imposibilidad de utilizar nuestros productos.\r\n" + 
				"\r\n" + 
				"POLÍTICA DE REEMBOLSO Y GARANTÍA\r\n" + 
				"\r\n" + 
				"En el caso de productos que sean  mercancías irrevocables no-tangibles, no realizamos reembolsos después de que se envíe el producto, usted tiene la responsabilidad de entender antes de comprarlo.  Le pedimos que lea cuidadosamente antes de comprarlo. Hacemos solamente excepciones con esta regla cuando la descripción no se ajusta al producto. Hay algunos productos que pudieran tener garantía y posibilidad de reembolso pero este será especificado al comprar el producto. En tales casos la garantía solo cubrirá fallas de fábrica y sólo se hará efectiva cuando el producto se haya usado correctamente. La garantía no cubre averías o daños ocasionados por uso indebido. Los términos de la garantía están asociados a fallas de fabricación y funcionamiento en condiciones normales de los productos y sólo se harán efectivos estos términos si el equipo ha sido usado correctamente. Esto incluye:\r\n" + 
				"\r\n" + 
				"– De acuerdo a las especificaciones técnicas indicadas para cada producto.\r\n" + 
				"– En condiciones ambientales acorde con las especificaciones indicadas por el fabricante.\r\n" + 
				"– En uso específico para la función con que fue diseñado de fábrica.\r\n" + 
				"– En condiciones de operación eléctricas acorde con las especificaciones y tolerancias indicadas.\r\n" + 
				"\r\n" + 
				"COMPROBACIÓN ANTIFRAUDE\r\n" + 
				"\r\n" + 
				"La compra del cliente puede ser aplazada para la comprobación antifraude. También puede ser suspendida por más tiempo para una investigación más rigurosa, para evitar transacciones fraudulentas.\r\n" + 
				"\r\n" + 
				"PRIVACIDAD\r\n" + 
				"\r\n" + 
				"Este sitio web www.bidaion.eus garantiza que la información personal que usted envía cuenta con la seguridad necesaria. Los datos ingresados por usuario o en el caso de requerir una validación de los pedidos no serán entregados a terceros, salvo que deba ser revelada en cumplimiento a una orden judicial o requerimientos legales.\r\n" + 
				"\r\n" + 
				"La suscripción a boletines de correos electrónicos publicitarios es voluntaria y podría ser seleccionada al momento de crear su cuenta.\r\n" + 
				"\r\n" + 
				"BiDai-On S.A. reserva los derechos de cambiar o de modificar estos términos sin previo aviso.\r\n" + 
				"\r\n" + 
				"Estas terminos y condiciones se han generado en terminosycondicionesdeusoejemplo.com.";
		
		
		assertEquals(bases, gestor.leerBases("BasesLegales.txt"));
	}
}
