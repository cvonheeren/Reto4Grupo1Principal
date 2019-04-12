package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Reto4Grupo1BBDD.ModificarBBDD;

public class GestorBBDD {
	
	ModificarBBDD modificarBBDD = null;

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
				String ubicacion = result.getString("CIUDAD");
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
				String ubicacion = result.getString("CIUDAD");
				String nombre = result.getString("NOMBRE");
				listaAlojamientos.add(new Alojamiento(codAlojamiento, nombre, ubicacion));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlojamientos;
	}
	
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
	
	public ArrayList<Habitacion> cargarListaHabitaciones(int codAlojamiento) {
		ResultSet result = modificarBBDD.cargarListaHabitaciones(codAlojamiento);
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try {
			while (result.next()) {
				int codHabitacion = result.getInt("COD_DORMITORIO ");
				int ctaCamasSimples = result.getInt("CTD_CAMASSIMPLES");
				int ctaCamasMatrimonio = result.getInt("CTD_CAMASMATRIMONIO");
				int ctaCamasInfantil = result.getInt("CTD_CAMASINFANTIL");
				float tamano = result.getInt("TAMANO");
				habitaciones.add(new Habitacion(codHabitacion, ctaCamasSimples, ctaCamasMatrimonio, ctaCamasInfantil, tamano));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
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
	 * 
	 * Inserta una reserva en la BBDD
	 * @param reserva Reserva que se quiere insertar
	 * @return
	 */
	public int insertarReserva(Reserva reserva) {
		int codReserva = -1;
		ResultSet result = modificarBBDD.insertarReserva(reserva.getCodHotel(), reserva.getPrecio());

		try {
			result.next();
			codReserva = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codReserva;
	}
	
}
