package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Reserva {

	private int codReserva;
	private int codCliente;
	private int codAlojamiento;
	private Date fechaCompra;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float precio;
	private Alojamiento alojamiento = null;
	private ArrayList<Habitacion> habitacionesReservadas = null;
	private int ctdHabitaciones;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * 
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, int codCliente, int codAlojamiento, Date fechaCompra, Date fechaEntrada, Date fechaSalida, float precio) {
		this.codReserva = codReserva;
		this.codCliente = codCliente;
		this.codAlojamiento = codAlojamiento;
		this.fechaCompra = fechaEntrada;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaEntrada;
		this.precio = precio;
		this.habitacionesReservadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
	}
	
	public Reserva() {
		this.habitacionesReservadas = new ArrayList<Habitacion>();
		this.ctdHabitaciones = 0;
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public ArrayList<Habitacion> getHabitacionesReservadas() {
		return habitacionesReservadas;
	}

	public void setHabitacionesReservadas(ArrayList<Habitacion> habitacionesReservadas) {
		this.habitacionesReservadas = habitacionesReservadas;
	}
	
	public void addHabitacion(Habitacion habitacion) {
		this.habitacionesReservadas.add(habitacion);
		this.ctdHabitaciones++;
	}
	
	public void removeHabitacion(Habitacion habitacion) {
		this.habitacionesReservadas.remove(habitacion);
		this.ctdHabitaciones--;
	}
	
	/**
	 * Metodo que comprueba si una fecha es festivo
	 * @param fecha La fecha a comprobar
	 * @return true si es festivo, false si no lo es
	 */
	public boolean tipoDeFecha(Calendar fecha) {
		
		//Los dias que son festivos
		Calendar fecha1 = new GregorianCalendar();
        fecha1.set(2019, 5, 15);
		Calendar fecha2 = new GregorianCalendar();
        fecha2.set(2019, 6, 10);
		Calendar fecha3 = new GregorianCalendar();
        fecha3.set(2019, 6, 20);
		Calendar fecha4 = new GregorianCalendar();
        fecha4.set(2019, 7, 25);
		Calendar fecha5 = new GregorianCalendar();
        fecha5.set(2019, 8, 11);
		Calendar fecha6 = new GregorianCalendar();
        fecha6.set(2019, 8, 15);
		Calendar fecha7 = new GregorianCalendar();
        fecha7.set(2019, 10, 12);
		Calendar fecha8 = new GregorianCalendar();
        fecha8.set(2019, 10, 27);
		Calendar fecha9 = new GregorianCalendar();
        fecha9.set(2019, 11, 1);
		Calendar fecha10 = new GregorianCalendar();
        fecha10.set(2019, 11, 9);
		Calendar fecha11 = new GregorianCalendar();
        fecha11.set(2019, 12, 6);
		Calendar fecha12 = new GregorianCalendar();
        fecha12.set(2019, 12, 8);
		Calendar fecha13 = new GregorianCalendar();
        fecha13.set(2019, 12, 25);
        
        Calendar[] fechasFestivo = {fecha1, fecha2, fecha3, fecha4, fecha5, fecha6, fecha7, fecha8, fecha9, fecha10, fecha11, fecha12, fecha13};
		
		for (int i = 0; i < fechasFestivo.length; i++) {
			if (fecha.compareTo(fechasFestivo[i]) == 0)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Comprueba si una fecha esta en la epoca de verano
	 * @param fecha La fecha a comprobar
	 * @return True si esta en verano false si no lo esta
	 */
    public boolean comprobarSiEsVerano(Calendar fecha) {
    	Calendar fecha1 = new GregorianCalendar();
        fecha1.set(2019, 6, 30);
        Calendar fecha2 = new GregorianCalendar();
        fecha2.set(2019, 9, 1);
    	
        if (fecha.after(fecha1) && fecha.before(fecha2))
        	return true;
        else
        	return false;
    }
}
