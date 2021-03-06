package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import core.Principal;


/**
 * Realiza los calculos del pago del programa
 */
public class GestorDinero {
	
	private float dineroIntroducido;
	private ArrayList<Float> monedasIntroducidas;
	private float dineroRestante;
	private float descuento;
	private float precio;
	
	public GestorDinero() {
		this.dineroIntroducido = 0;
		this.dineroRestante = 0;
		this.descuento = 0;
		this.monedasIntroducidas = new ArrayList<Float>();
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public float getPrecioConDescuento(){
		return precio-descuento;
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
	 * Resta la �ltima moneda introducida del dinero introducido y la quita del array de monedas
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
		dineroRestante = precio - dineroIntroducido;
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
		return dineroIntroducido - precio;
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
	 * Calcula el precio final con descuentos
	 * @param habReservadas
	 * @return
	 */
	public float calcularPrecioConDescuentos(ArrayList<Habitacion> habReservadas, ArrayList<Servicio> servicios, LocalDate fecha1, LocalDate fecha2) {
		
		float precioTotal = 0;
		float tarifaDiaNormal = 0;
		float tarifaDiaVerano = 0;
		float tarifaDiaFestivo = 0;
		float precioServicios = 0;
		
		// calcular tarifa por dia normal
		for(int i=0;i<habReservadas.size();i++) {
			tarifaDiaNormal += habReservadas.get(i).getTarifaNormal() * habReservadas.get(i).getCantidad();
		}
		
		// calcular tarifa por dia verano
		for(int i=0;i<habReservadas.size();i++) {
			tarifaDiaVerano += habReservadas.get(i).getTarifaVerano() * habReservadas.get(i).getCantidad();
		}
		
		// calcular tarifa por dia festivo
		for(int i=0;i<habReservadas.size();i++) {
			tarifaDiaFestivo += habReservadas.get(i).getTarifaFestivo() * habReservadas.get(i).getCantidad();
		}
		
		// cogemos los dias del periodo para comprobar sus tarifas extras
	    ArrayList<LocalDate> lista = Principal.modelo.gestorFechas.setDiasSeleccionados(fecha1, fecha2);
	    
	    for (int i = 0; i < lista.size(); i++) {
			if(Principal.modelo.gestorFechas.comprobarFestivo(lista.get(i))) {
				precioTotal += tarifaDiaFestivo;
			} else if(Principal.modelo.gestorFechas.comprobarSiEsVerano(lista.get(i))) {
				precioTotal += tarifaDiaVerano;
			} else {
				precioTotal += tarifaDiaNormal;
			}
		}
	    
	    if (servicios != null) {
	    	for (int i = 0; i < servicios.size(); i++) {
	    		precioServicios += servicios.get(i).getPrecio();
	    	}
	    }

	    return precioTotal + precioServicios - descuento;
	}
	
	/**
	 * calcula el precio total de la estancia para una habitacion
	 * @param habReservadas
	 * @return
	 */
	public float getPrecioTotalHabitacion(Habitacion habitacion, LocalDate fecha1, LocalDate fecha2) {
		float precioTotal = 0;
		
		// cogemos los dias del periodo para comprobar sus tarifas extras
	    ArrayList<LocalDate> lista = Principal.modelo.gestorFechas.setDiasSeleccionados(fecha1, fecha2);
	    
	    for (int i = 0; i < lista.size(); i++) {
			if(Principal.modelo.gestorFechas.comprobarFestivo(lista.get(i))) {
				precioTotal += habitacion.getTarifaFestivo();
			} else if(Principal.modelo.gestorFechas.comprobarSiEsVerano(lista.get(i))) {
				precioTotal += habitacion.getTarifaVerano();
			} else {
				precioTotal += habitacion.getTarifaNormal();
			}
		}
	    
	    return precioTotal;
	}
	
	/**
	 * Carga la tarifa de habitacion para un dia concreto
	 * @param habitacion
	 * @return
	 */
    public float getPrecioDiaHabitacion(Habitacion habitacion, LocalDate fecha) {
    	if(Principal.modelo.gestorFechas.comprobarFestivo(fecha)) {
    		return habitacion.getTarifaFestivo();
    	} else if (Principal.modelo.gestorFechas.comprobarSiEsVerano(fecha)){
    		return habitacion.getTarifaVerano();
    	} else {
    		return habitacion.getTarifaNormal();
    	}
    }
    
    /**
     * Busca la habitacion mas barata de un alojamiento
     * @param habitaciones
     * @return
     */
	public Habitacion getHabBarata(ArrayList<Habitacion> habitaciones, LocalDate fecha){
		int pos = 0;
		for(int i = 0; i < habitaciones.size(); i++) {
			if(Principal.modelo.gestorFechas.comprobarFestivo(fecha)) {
				if(habitaciones.get(i).getTarifaFestivo() < habitaciones.get(pos).getTarifaFestivo()) {
					pos = i;
				}
	    	} else if (Principal.modelo.gestorFechas.comprobarSiEsVerano(fecha)){
	    		if(habitaciones.get(i).getTarifaVerano() < habitaciones.get(pos).getTarifaVerano()) {
					pos = i;
				}
	    	} else {
	    		if(habitaciones.get(i).getTarifaNormal() < habitaciones.get(pos).getTarifaNormal()) {
					pos = i;
				}
	    	}
		}
		return habitaciones.get(pos);
	}

}
