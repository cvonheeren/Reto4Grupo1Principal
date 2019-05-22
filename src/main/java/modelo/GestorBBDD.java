package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Reto4Grupo1BBDD.ModificarBBDD;

public class GestorBBDD {
	
	public ModificarBBDD modificarBBDD = null;
	private ResultSet ultimaBusqueda;
	private ArrayList<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();
	
	public GestorBBDD() {
		modificarBBDD = new ModificarBBDD();
	}
	
	/**
	 * Obtiene una lista con los nombres de todas las ciudades
	 * @return ArrayList<String> Lista de ciudades 
	 */
	public ArrayList<String> cargarNombresDestinos() {
		ResultSet result = modificarBBDD.cargarNombresDestinos();
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
	 * Obttiene el nombre los alojamientos
	 * @return
	 */
	public ArrayList<String> cargarNombresAlojamientos() {
		ResultSet result = modificarBBDD.cargarNombresAlojamientos();
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
	 * @param ciudad
	 * @param estrellasMin
	 * @param estrellasMax
	 * @param tipoAlojSel
	 * @param tipoOrden
	 * @param ordenAscendente
	 * @param cantidad
	 * @return
	 */
	public ArrayList<Alojamiento> realizarBusquedaAlojamientos(String ciudad, int estrellasMin, int estrellasMax, String[] tipoAlojSel, char tipoOrden, boolean ordenAscendente, int cantidad, int[] servSeleccionados) {
		ultimaBusqueda = obtenerResultSetAlojamientos(ciudad, estrellasMin, estrellasMax, tipoAlojSel, tipoOrden, ordenAscendente, servSeleccionados);
		return obtenerListaAlojamientos(ultimaBusqueda, cantidad);
	}
	
	/**
	 * Borra la ultima busqueda
	 */
	public void borrarUltimaBusqueda() {
		ultimaBusqueda = null;
		listaAlojamientos = new ArrayList<Alojamiento>();
	}
	
	/**
	 * Obtiene el resulset de alojanietos de la BBDD
	 * @param ciudad
	 * @param estrellasMin
	 * @param estrellasMax
	 * @param tipoAlojSel
	 * @param tipoOrden
	 * @param ordenAscendente
	 * @return
	 */
	private ResultSet obtenerResultSetAlojamientos(String ciudad, int estrellasMin, int estrellasMax, String[] tipoAlojSel, char tipoOrden, boolean ordenAscendente, int[] servSeleccionados) {	
		return modificarBBDD.cargarAlojamientos(ciudad, estrellasMin, estrellasMax, tipoAlojSel, tipoOrden, ordenAscendente, servSeleccionados);
	}
	
	/**
	 * Muestra mas alojamientos
	 * @param cantidad
	 * @return
	 */
	public ArrayList<Alojamiento> mostrarMasAlojamientos(int cantidad) {
		return obtenerListaAlojamientos(ultimaBusqueda, cantidad);
	}
	
	/**
	 * Obtiene los alojamientos ubicados en la ciudad especificada
	 * @param ciudad Nombre de la ciudad por la que se quiere restringir la busqueda
	 * @return ArrayList<Alojamiento> Lista de alojamientos
	 */
	private ArrayList<Alojamiento> obtenerListaAlojamientos(ResultSet result, int cantidad) {
		int contador = 0;
		try {
			while (result.next() && contador < cantidad) {
				
				int codAlojamiento = result.getInt("COD_ALOJAMIENTO");
				String ubicacion = result.getString("UBICACIONES.NOMBRE");
				String nombre = result.getString("ALOJAMIENTOS.NOMBRE");
				String desc = result.getString("DESCRIPCION");
				float longitud = result.getFloat("LONGITUD");
				float latitud = result.getFloat("LATITUD");
				String tipoAloj = result.getString("TIPO");
				String imgurl = result.getString("IMGURL");
				if(tipoAloj.equals("H"))
				{
					int estrellas = result.getInt("ESTRELLAS");
					listaAlojamientos.add(new Hotel(codAlojamiento, ubicacion, nombre, desc, longitud, latitud, imgurl, estrellas));
				}
				else if(tipoAloj.equals("A"))
				{
					int piso = result.getInt("ALTURA");
					ArrayList<Estancia> estancias = cargarEstancias(codAlojamiento);
					listaAlojamientos.add(new Apartamento(codAlojamiento, ubicacion, nombre, desc, longitud, latitud, imgurl, piso, estancias));
				}
				else if(tipoAloj.equals("C"))
				{
					ArrayList<Estancia> estancias = cargarEstancias(codAlojamiento);
					listaAlojamientos.add(new Casa(codAlojamiento, ubicacion, nombre, desc, longitud, latitud, imgurl, estancias));
				}
				else
				{
					listaAlojamientos.add(new Alojamiento(codAlojamiento, ubicacion, nombre, desc, longitud, latitud,  imgurl));
				}
				contador++;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlojamientos;
	}
	
	/**
	 * Carga habitaciones
	 * @param codAlojamiento
	 * @return
	 */
	public ArrayList<Habitacion> cargarHabitaciones(int codAlojamiento) {
		ResultSet result = modificarBBDD.cargarHabitaciones(codAlojamiento);
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try {
			while (result.next()) {
				int codHabitacion = result.getInt("COD_HABITACION");
				String nombre = result.getString("NOMBRE");
				int ctaCamasSimples = result.getInt("CTD_CAMAS_SIMPLES");
				int ctaCamasMatrimonio = result.getInt("CTD_CAMAS_MATRIMONIO");
				int ctaCamasInfantil = result.getInt("CTD_CAMAS_INFANTIL");
				float tamano = result.getFloat("TAMANO");
				int cantidad = result.getInt("CANTIDAD");
				float tarifaNormal = result.getFloat("TARIFA_NORMAL");
				float tarifaVerano = result.getFloat("TARIFA_VERANO");
				float tarifaFestivo = result.getFloat("TARIFA_FESTIVO");
				String descripcion = result.getString("DESCRIPCION");
				habitaciones.add(new Habitacion(codHabitacion, nombre, tamano, ctaCamasSimples, ctaCamasMatrimonio, ctaCamasInfantil, cantidad, tarifaNormal, tarifaVerano, tarifaFestivo, descripcion));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}
	
	/**
	 * Obtiene las habitaciones reservadas del alojamiento indicado?
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
				int codEstancia = result.getInt("COD_HABITACION");
				int cantidad = result.getInt("CANTIDAD");
				habitaciones.add(new Habitacion(codEstancia, cantidad));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}
	
	/**
	 * Carga las habitaciones disponibles de un alojamiento en las fechas seleccionadas
	 * @param codAlojamiento
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public ArrayList<Habitacion> habitacionesDisponibles(int codAlojamiento, Date fechaEntrada, Date fechaSalida) {
		ArrayList<Habitacion> habitaciones = cargarHabitaciones(codAlojamiento);
		ArrayList<Habitacion> habitacionesReservadas = cargarHabitacionesReservadas(codAlojamiento, fechaEntrada, fechaSalida);
		for (int i = 0; i < habitaciones.size(); i++) {
			for (int j = 0; j < habitacionesReservadas.size(); j++) {
				if (habitaciones.get(i).getCodEstancia() == habitacionesReservadas.get(j).getCodEstancia()) {
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
	 * Carga las estancias de un alojamiento?
	 * @param codAlojamiento
	 * @return
	 */
	public ArrayList<Estancia> cargarEstancias(int codAlojamiento) {
		ResultSet result = modificarBBDD.cargarEstancias(codAlojamiento);
		ArrayList<Estancia> estancias = new ArrayList<Estancia>();
		try {
			while (result.next()) {
				int codEstancia = result.getInt("COD_ESTANCIA");
				String nombre = result.getString("NOMBRE");
				float tamano = result.getFloat("TAMANO");
				int cantidad = result.getInt("CANTIDAD");
				estancias.add(new Estancia(codEstancia, nombre, tamano, cantidad));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estancias;
	}
	
	/**
	 * Devuelve la lista de todos los servicios de un alojamiento por su codigo
	 * @param codAlojamiento
	 * @return
	 */
	public ArrayList<Servicio> obtenerServicios(int codAlojamiento) {
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		ResultSet result = modificarBBDD.obtenerServicios(codAlojamiento);
		
		try {
			while (result.next()) {
				int codServicio = result.getInt("COD_SERVICIO");
				String nombre = result.getString("NOMBRE");
				float precio = result.getFloat("PRECIO");
				String icon = result.getString("FONTAWESOMEICON");
				servicios.add(new Servicio(codServicio, nombre, precio, icon));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	/**
	 * Devuelve la lista de los servicios de pago de un alojamiento por su codigo
	 * @param codAlojamiento
	 * @return
	 */
	public ArrayList<Servicio> obtenerServiciosPago(int codAlojamiento) {
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		ResultSet result = modificarBBDD.obtenerServiciosPago(codAlojamiento);
		
		try {
			while (result.next()) {
				int codServicio = result.getInt("COD_SERVICIO");
				String nombre = result.getString("NOMBRE");
				float precio = result.getFloat("PRECIO");
				String icon = result.getString("FONTAWESOMEICON");
				servicios.add(new Servicio(codServicio, nombre, precio, icon));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	/**
	 * Obtiene una lista de todos los servicios disponibles
	 * @return
	 */
	public ArrayList<Servicio> obtenerTodosServicios() {
		ArrayList<Servicio> servicios = new ArrayList<Servicio>();
		ResultSet result = modificarBBDD.obtenerTodosServicios();
		
		try {
			while (result.next()) {
				int codServicio = result.getInt("COD_SERVICIO");
				String nombre = result.getString("NOMBRE");
				String icon = result.getString("FONTAWESOMEICON");
				servicios.add(new Servicio(codServicio, nombre, -1, icon));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	/**
	 * Obtiene el codigo del cliente segun su nombre de usuario
	 * @param username
	 * @return
	 */
	public int obtenerCodCliente(String username) {
		int codCliente = -1;
		ResultSet result = modificarBBDD.obtenerCodCliente(username);

		try {
			result.next();
			codCliente = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codCliente;
	}
	
	/**
	 * Comprueba si el cliente con el dni y contrasena indicados existe en la BBDD
	 * @param dni DNI que se quiere comprobar
	 * @param password Contrasena que queremos comprobar
	 * @return 
	 */
	public boolean comprobarCliente(String user, String password) {
		ResultSet result = modificarBBDD.comprobarCliente(user, password);	
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
	 * Valida del codigo promocional
	 * @param codPromo
	 * @param user
	 * @param codAlojamiento
	 * @return
	 */
	public float validarCodPromo(String codPromo, String user, int codAlojamiento) {
		ResultSet result = modificarBBDD.validarCodPromo(codPromo, user, codAlojamiento);
		float descuento = 0;
		try {
			while (result.next()) {
				if(result.getString("CODPROMO").equals(codPromo)) {
					descuento = result.getFloat("DESCUENTO");
					modificarBBDD.BorrarPromocion(codPromo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descuento;
	}
	
	/**
	 * 
	 * Inserta una reserva en la BBDD
	 * @param reserva Reserva que se quiere insertar
	 * @return Devuelve el codigo de la reserva que se genera en sql
	 */
	public int insertarReserva(Cliente cliente, Reserva reserva, GestorDinero gestorDinero) {
		int codReserva = -1;
		ResultSet result = modificarBBDD.insertarReserva(reserva.getAlojamiento().getCodAlojamiento(), gestorDinero.getPrecio(), reserva.getFechaCompra(), reserva.getFechaEntrada(), reserva.getFechaSalida(), cliente.getCodCliente());

		try {
			result.next();
			codReserva = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codReserva;
	}
	
	/**
	 * Inserta un nuevo cliente en la BBDD
	 * @param dni Su dni
	 * @param password Su contrasenia
	 * @param nombre Su nombre etc.
	 * @param apellido
	 * @param fechaNac Su fecha de nacimiento
	 * @param mail
	 * @return Devuelve el codigo de la reserva que se genera en sql
	 */
	public int insertarCliente(String user, String dni, String password, String nombre, String apellido, Date fechaNac, String mail) {
		int codReserva = -1;
		ResultSet result = modificarBBDD.insertarCliente(user, dni, password, nombre, apellido, fechaNac, mail);

		try {
			result.next();
			codReserva = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codReserva;
	}
	
}
