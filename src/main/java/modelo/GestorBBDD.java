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
	
	public ArrayList<Alojamiento> cargarListaAlojamientos(String string) {
		ArrayList<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();
		ResultSet result = modificarBBDD.cargarListaAlojamientos(string);
		try {
			while (result.next()) {
				int cod_alojamiento = result.getInt("COD_HOTEL");
				String ubicacion = result.getString("UBICACION");
				String nombre = result.getString("NOMBRE");
				listaAlojamientos.add(new Alojamiento(cod_alojamiento, nombre, ubicacion));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlojamientos;
	}
	
	public ArrayList<String> cargarListaDestinos() {
		ResultSet result = modificarBBDD.cargarListaDestinos();
		ArrayList<String> destinos = new ArrayList<String>();
		try {
			while (result.next()) {
				String ubicacion = result.getString("UBICACION");
				destinos.add(ubicacion);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return destinos;
	}
	
	public Cliente cargarCliente(String dni) {
		ResultSet result = modificarBBDD.cargarCliente(dni);
		Cliente cliente = null;
		try {
			while (result.next()) {
				String dniCliente = result.getString("dni");
				String nombre = result.getString("nombre");
				cliente = new Cliente(dniCliente, nombre);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;
	}
	
	/*public int insertarDatos(String string) {
		return modificarBBDD.insertarDatosBD(string);
	}*/
	
}
