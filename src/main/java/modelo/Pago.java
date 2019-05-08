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
		// tarifa alojamiento x nº de noches x nº de habitaciones
		float tarifaPDia = 0;
		for(int i=0;i<habReservadas.size();i++)
		{
			tarifaPDia = tarifaPDia + (habReservadas.get(i).tarifaNormal * habReservadas.get(i).getCantidad());
		}
		
		float numNoches = diferenciaDias(fechaEntrada, fechaSalida);
		float contadorRecargo = 0;
		
		Calendar fecha1 =  new GregorianCalendar();
		fecha1 = toCalendar(fechaEntrada);
        Calendar fecha2 =  new GregorianCalendar();
        fecha2 = toCalendar(fechaSalida);
        
        //ArrayList<Calendar> lista = setDiasSeleccionados(fecha1, fecha2);
        
        System.out.println(fechaEntrada);
       /* System.out.println(lista.get(0));
        System.out.println(lista.get(1));
        System.out.println(lista.get(2));
        
        GestorDeFechas gestorF = new GestorDeFechas();
        
        for (int i = 0; i < lista.size(); i++) {
			if(gestorF.comprobarSiEsVerano(lista.get(i)))
				contadorRecargo = contadorRecargo + (tarifa * alojamiento.tarifaVerano);
			if(gestorF.tipoDeFecha(lista.get(i)))
				contadorRecargo = contadorRecargo + (tarifa * alojamiento.recargo);
		}*/
		
		return (tarifaPDia * numNoches) + contadorRecargo;
	}
	
	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public float diferenciaDias(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
	
	/**
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	public ArrayList<LocalDate> setDiasSeleccionados(LocalDate fecha1, LocalDate fecha2) {
		ArrayList<LocalDate> dias = new ArrayList<LocalDate>();
		LocalDate fechaAux;
		fechaAux = fecha1;
		
		for (int i = 0; i < fecha2.getDayOfYear() - fecha1.getDayOfYear(); i++) {
			fechaAux.plusDays(i);
			dias.add(fechaAux);
		}
		
		return dias;
	}
}
