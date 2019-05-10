package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


/**
 * Realiza los calculos del pago del programa
 */
public class Pago {
	
	private float precioTotal;
	private float dineroIntroducido;
	private ArrayList<Float> monedasIntroducidas;
	private float dineroRestante;
	
	public Pago() {
		this.precioTotal = 0;
		this.dineroIntroducido = 0;
		this.dineroRestante = 0;
		this.monedasIntroducidas = new ArrayList<Float>();
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public float getDineroIntroducido() {
		return dineroIntroducido;
	}
	
	public void setDineroIntroducido(float dineroIntroducido) {
		this.dineroIntroducido = dineroIntroducido;
	}
	
	public float getDineroRestante() {
		return dineroRestante;
	}

	public void setDineroRestante(float dineroRestante) {
		this.dineroRestante = dineroRestante;
	}

	/**
	 * Suma el valor pasado por parametro al dinero introducido en total
	 * @param monedaIntroducida valor que se debe sumar
	 * @return dinero total introducido despues de la operacion
	 */
	public float sumarDinero(float monedaIntroducida) {
		dineroIntroducido += monedaIntroducida;
		dineroIntroducido = redondear(dineroIntroducido, 2);
		monedasIntroducidas.add(monedaIntroducida);
		return dineroIntroducido;
	}
	
	/**
	 * Resta la última moneda introducida del dinero introducido y la quita del array de monedas
	 * @return dinero total introducido despues de la operacion
	 */
	public float devolverDinero() {
		float monedaDevolver = monedasIntroducidas.get(monedasIntroducidas.size() - 1);
		monedasIntroducidas.remove(monedasIntroducidas.size()-1);
		dineroIntroducido = dineroIntroducido - monedaDevolver;
		dineroIntroducido = redondear(dineroIntroducido, 2); 
		return dineroIntroducido;
	}
	
	/**
	 * Metodo que se encarga de calcular el dinero que falta por introducir
	 * @return Retorna el dinero que falta por introducir
	 */
	public float calcularDineroRestante() {
		dineroRestante = precioTotal - dineroIntroducido;
		dineroRestante = redondear(dineroRestante, 2);
		if (dineroRestante < 0) {
			dineroRestante = 0;
		}
		return dineroRestante;
	}
	
	/**
	 * Metodo que se encarga de calcular la cantidad de dinero sobrante al realizar el pago
	 * @return cantidad de dinero sobrante
	 */
	public float calcularDineroSobrante() {	
		return dineroIntroducido - precioTotal;
	}
	
	/**
	 * Comprueba si falta dinero por introducir para realizar el pago
	 * @return true en caso de que falte dinero por introducir, false en caso contrario
	 */
	public boolean comprobarFaltaDinero() {
		return (dineroIntroducido < precioTotal) ? true : false ;
	}
	
	/**	
	 * Metodo que se encarga de calcular el menor numero de monedas y billetes que se deben que dar de devolucion
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
	            	if (monedasBilletes[i] > 0.5) {
	            		devolver += Integer.toString(cont)+" monedas de " + Integer.toString((int)monedasBilletes[i]) + "€ \n" ;
	            	} else {
	            		devolver += Integer.toString(cont)+" monedas de " + Float.toString(monedasBilletes[i]) + "€ \n" ;
	            	}
	            }
			}
		}
		return devolver;
	}
	
	/**
	 * Redondea un numero con el numero de decimales especificado
	 * @param num Numero que se quiere redondear
	 * @param numDecimales Numero de decimales que se quiere obtener
	 * @return Numero redondeado
	 */
	public float redondear(float num, int numDecimales) {
		int mult = (int)Math.pow(10, numDecimales);
		num = Math.round(num*mult);
		num = num/mult;
		return num;
	}
	
	/**
	 * 
	 * @param alojamiento
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @param habReservadas
	 * @return
	 */
	public float calcularPrecio(Alojamiento alojamiento, Date fechaEntrada, Date fechaSalida, ArrayList<Habitacion> habReservadas) {
		float tarifaPDia = 0;
		GestorDeFechas gestorF = new GestorDeFechas();
		float numNoches = gestorF.diferenciaDias(fechaEntrada, fechaSalida);
		float contadorRecargo = 0;
		
		// tarifa alojamiento x nº de noches x nº de habitaciones
		for(int i=0;i<habReservadas.size();i++)
		{
			tarifaPDia = tarifaPDia + (habReservadas.get(i).getTarifaNormal() * habReservadas.get(i).getCantidad());
		}
		
		// cogemos los dias del periodo para comprobar sus tarifas extras
		LocalDate fecha1 = fechaEntrada.toLocalDate();
		LocalDate fecha2 = fechaSalida.toLocalDate(); 
	    ArrayList<LocalDate> lista = gestorF.setDiasSeleccionados(fecha1, fecha2);
	    
	    for (int i = 0; i < lista.size(); i++) {
			if(gestorF.comprobarSiEsVerano(lista.get(i)))
				contadorRecargo = contadorRecargo + habReservadas.get(i).getTarifaVerano();
			if(gestorF.tipoDeFecha(lista.get(i)))
				contadorRecargo = contadorRecargo + habReservadas.get(i).getTarifaFestivo();
		}
		
		return (tarifaPDia * numNoches) + contadorRecargo;
		}
}
