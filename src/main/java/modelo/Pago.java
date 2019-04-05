package modelo;

/**
 * Esta clase se encarga de los calculos del dinero del programa
 */
public class Pago {
	
	/**
	 * Redimensiona un array que se le pasa por parametro a uno con una posicion mas
	 * @param array el array que se desea redimensionar
	 * @return el array nuevo donde se ha copiado el contenido del array que se queria redimensionar
	 */
	public float[] redimensionarArrayMayor(float[] array) {
		int tamano = array.length + 1;
		float[] arrayNuevo = new float[tamano];
		System.arraycopy(array, 0, arrayNuevo, 0, array.length);
		return arrayNuevo;
	}
	
	/**
	 * Redimensiona el array que se le pasa por parametro a uno con una posicion menos
	 * @param array el array que se desea redimensionar
	 * @return el array nuevo donde se ha copiado el contenido del array que se queria redimensionar
	 */
	public float[] redimensionarArrayMenor(float[] array) {
		int tamano = array.length - 1;
		float[] arrayNuevo = new float[tamano];
		System.arraycopy(array, 0, arrayNuevo, 0, array.length - 1);
		return arrayNuevo;
	}
	
	/**
	 * Metodo que se encarga de calcular el dinero faltante
	 * @param total Dinero total que se debe introducir
	 * @param dinero Dinero que se ha introducido hasta el momento
	 * @return Retorna el dinero que falta por introducir
	 */
	public float falta(float total, float dinero){	
		float falta = 0f;
		falta = total - dinero;
		falta = Math.round(falta*100);
		falta = falta/100;		 		
		return falta;						
	}
	
	/**
	 * Metodo que se encarga de calcular la cantidad de dinero sobrante al realizar el pago
	 * @param precioTotal Dinero total que se debe introducir
	 * @param dineroIntroducido Dinero total que se ha introducido
	 * @return cantidad de dinero sobrante
	 */
	public float sobra(float precioTotal, float dineroIntroducido){	
		return dineroIntroducido-precioTotal;		
	}
	
	/**	
	 * Metodo que se encarga de calcular el menor numero de monedas y billetes que se tienen que dar de devolucion
	 * @param sobra dinero sobrante tras realizar el pago
	 * @return string con el numero de monedas y billetes que se deben devolver
	 */
	public String calcularMonedasBilletes(float sobra){
		
		float[] monedasBilletes = {500, 200, 100, 50, 20, 10, 5, 2, 1, 0.50f, 0.20f, 0.10f, 0.05f, 0.02f, 0.01f };
		String devolver = "";
		int cont = 0;
		
		for(int i = 0; i < monedasBilletes.length;i++) {
			if(sobra >= monedasBilletes[i]) {
				cont = (int) (sobra/monedasBilletes[i]);
	            sobra = sobra%monedasBilletes[i];
	            if (monedasBilletes[i] >= 5) {
	            	devolver += Integer.toString(cont)+" billetes de " + Integer.toString((int)monedasBilletes[i]) + "€ \n" ;
	            } else {
	            	if (monedasBilletes[i] <= 0.5) {
	            		devolver += Integer.toString(cont)+" monedas de " + Float.toString(monedasBilletes[i]) + "€ \n" ;
	            	} else {
	            		devolver += Integer.toString(cont)+" monedas de " + Integer.toString((int)monedasBilletes[i]) + "€ \n" ;
	            	}
	            }
			}
		}

		return devolver;
	}

}