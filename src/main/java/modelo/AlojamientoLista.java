package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.Principal;


public class AlojamientoLista {

	protected ArrayList<Alojamiento> alojamientoLista;
	
	public AlojamientoLista() {
	}

	public ArrayList<Alojamiento> getAlojamientoLista() {
		return alojamientoLista;
	}

	public void setAlojamientoLista(String ubicacion) {
		this.alojamientoLista = CargarListaAlojamientos(ubicacion);
	}
	
	public ArrayList<Alojamiento> CargarListaAlojamientos(String ciudad)
	{
		ResultSet rs=Principal.modelo.gestorBBDD.HacerConsulta("SELECT * FROM HOTEL WHERE NOMBRE LIKE UPPER('%" + ciudad + "%') OR UBICACION LIKE UPPER('%" + ciudad + "%')");
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		
		try {
			while (rs.next()) {
				int cod_alojamiento=rs.getInt("COD_HOTEL");
				String ubicacion=rs.getString("UBICACION");
				String nombre=rs.getString("NOMBRE");
				//float precio=rs.getFloat("PRECIO");
				alojamientos.add(new Alojamiento(cod_alojamiento, nombre, ubicacion));
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alojamientos;
	}
	
	public ArrayList<String> CargarListaDestinos()
	{
		ResultSet rs=Principal.modelo.gestorBBDD.HacerConsulta("SELECT DISTINCT UBICACION FROM HOTEL ORDER BY UBICACION ASC;");
		ArrayList<String> ciudades = new ArrayList<String>();
		
		try {
			while (rs.next()) {
				String ubicacion=rs.getString("UBICACION");
				ciudades.add(ubicacion);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciudades;
	}
	
	
	
}
