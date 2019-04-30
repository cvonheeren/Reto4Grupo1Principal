package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Reto4Grupo1BBDD.ModificarBBDD;

public class GestorBBDD {
	
	public ModificarBBDD modificarBBDD = null;

	public GestorBBDD() {
		modificarBBDD = new ModificarBBDD();
	}
	
	/**
	 * Obtiene una lista con los nombres de todas las ciudades
	 * @return ArrayList<String> Lista de ciudades 
	 */
	public ArrayList<String> cargarListaDestinos() {
		ResultSet result = modificarBBDD.cargarListaDestinos();
		ArrayList<String> destinos = new ArrayList<String>();
		try {
			while (result.next()) {
				String ubicacion = result.getString("NOMBRE");
				destinos.add(ubicacion);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return destinos;
	}
	
	/**
	 * Obtiene los alojamientos ubicados en la ciudad especificada
	 * @param ciudad Nombre de la ciudad por la que se quiere restringir la busqueda
	 * @return ArrayList<Alojamiento> Lista de alojamientos
	 */
	public ArrayList<Alojamiento> cargarListaAlojamientos(String ciudad) {
		ArrayList<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();
		ResultSet result = modificarBBDD.cargarListaAlojamientos(ciudad);
		try {
			while (result.next()) {
				int codAlojamiento = result.getInt("COD_ALOJAMIENTO");
				String ubicacion = result.getString("UBICACIONES.NOMBRE");
				String nombre = result.getString("ALOJAMIENTOS.NOMBRE");
				String desc = result.getString("DESCRIPCION");
				float longitud = result.getFloat("LONGITUD");
				float latitud = result.getFloat("LATITUD");
				float tarifaNormal = result.getFloat("TARIFA_NORMAL");
				float tarifaVerano = result.getFloat("TARIFA_VERANO");
				float recargo = result.getFloat("REGARGO");
				float desayuno = result.getFloat("DESAYUNO");
				float mediaPension = result.getFloat("MEDIA_PENSION");
				float pensionCompleta = result.getFloat("PENSION_COMPLETA");
				String tipoAloj = result.getString("TIPO");
				String imgurl = result.getString("IMGURL");
				if(tipoAloj.equals("H"))
				{
					int estrellas = result.getInt("ESTRELLAS");
					listaAlojamientos.add(new Hotel(codAlojamiento, ubicacion, nombre, desc, longitud, latitud, tarifaNormal, tarifaVerano, recargo, desayuno, mediaPension, pensionCompleta, estrellas, imgurl));
				}
				else
				{
					listaAlojamientos.add(new Alojamiento(codAlojamiento, ubicacion, nombre, desc, longitud, latitud, tarifaNormal, tarifaVerano, recargo, desayuno, mediaPension, pensionCompleta, imgurl));
				}
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlojamientos;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> cargarListaAlojamientos() {
		ResultSet result = modificarBBDD.cargarListaAlojamientos();
		ArrayList<String> destinos = new ArrayList<String>();
		try {
			while (result.next()) {
				String nombre = result.getString("NOMBRE");
				destinos.add(nombre);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return destinos;
	}
	
	/**
	 * 
	 * @param codAlojamiento
	 * @return
	 */
	public ArrayList<Habitacion> cargarListaHabitaciones(int codAlojamiento) {
		ResultSet result = modificarBBDD.cargarListaHabitaciones(codAlojamiento);
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try {
			while (result.next()) {
				int codHabitacion = result.getInt("COD_DORMITORIO");
				String nombre = result.getString("NOMBRE");
				int ctaCamasSimples = result.getInt("CTD_CAMAS_SIMPLES");
				int ctaCamasMatrimonio = result.getInt("CTD_CAMAS_MATRIMONIO");
				int ctaCamasInfantil = result.getInt("CTD_CAMAS_INFANTIL");
				float tamano = result.getFloat("TAMANO");
				int cantidad = result.getInt("CANTIDAD");
				habitaciones.add(new Habitacion(codHabitacion, nombre, ctaCamasSimples, ctaCamasMatrimonio, ctaCamasInfantil, tamano, cantidad));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}
	
	/**
	 * 
	 * @param codAlojamiento
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public ArrayList<Habitacion> cargarHabitacionesReservadas(int codAlojamiento, Date fechaEntrada, Date fechaSalida) {
		ResultSet result = modificarBBDD.cargarHabitacionesReservadas(codAlojamiento, fechaEntrada, fechaSalida);
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try {
			while (result.next()) {
				int codHabitacion = result.getInt("COD_DORMITORIO");
				int cantidad = result.getInt("CANTIDAD");
				habitaciones.add(new Habitacion(codHabitacion, cantidad));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}
	
	/**
	 * 
	 * @param codAlojamiento
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public ArrayList<Habitacion> habitacionesDisponibles(int codAlojamiento, Date fechaEntrada, Date fechaSalida) {
		ArrayList<Habitacion> habitaciones = cargarListaHabitaciones(codAlojamiento);
		ArrayList<Habitacion> habitacionesReservadas = cargarHabitacionesReservadas(codAlojamiento, fechaEntrada, fechaSalida);
		for (int i = 0; i < habitaciones.size(); i++) {
			for (int j = 0; j < habitacionesReservadas.size(); j++) {
				if (habitaciones.get(i).getCodHabitacion() == habitacionesReservadas.get(j).getCodHabitacion()) {
					habitaciones.get(i).setCantidad(habitaciones.get(i).getCantidad()-habitacionesReservadas.get(j).getCantidad());
				}
			}
			if (habitaciones.get(i).getCantidad() == 0) {
				habitaciones.remove(i);
				i--;
			}
		}
		return habitaciones;
	}
	
	/**
	 * Comprueba si el cliente con el dni y contrasena indicados existe en la BBDD
	 * @param dni DNI que se quiere comprobar
	 * @param password Contrasena que queremos comprobar
	 * @return 
	 */
	public boolean comprobarCliente(String dni, String password) {
		ResultSet result = modificarBBDD.comprobarCliente(dni, password);	
		try {
			if (result.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Comprueba que un dni exista en la BBDD
	 * @param dni El dni a comprobar
	 * @return True si existe false si no
	 */
	public boolean comprobarDni(String dni) {
		ResultSet result = modificarBBDD.comprobarDni(dni);	
		try {
			if (result.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * Inserta una reserva en la BBDD
	 * @param reserva Reserva que se quiere insertar
	 * @return
	 */
	public int insertarReserva(Alojamiento alojamiento, Cliente cliente, Pago pago, Date fecha1, Date fecha2) {
		int codReserva = -1;
		ResultSet result = modificarBBDD.insertarReserva(alojamiento.getCodAlojamiento(), pago.getPrecioTotal(), fecha1, fecha2);

		try {
			result.next();
			codReserva = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codReserva;
	}
	
	public int insertarCliente(String dni, String password, String nombre, String apellido, Date fechaNac, String mail) {
		int codReserva = -1;
		ResultSet result = modificarBBDD.insertarCliente(dni, password, nombre, apellido, fechaNac, mail);

		try {
			result.next();
			codReserva = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codReserva;
	}
	
}
