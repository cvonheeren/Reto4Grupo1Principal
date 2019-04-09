package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AlojamientoLista {

	protected ArrayList<Alojamiento> alojamientoLista;
	
	public AlojamientoLista() {
	}

	public ArrayList<Alojamiento> getAlojamientoLista() {
		return alojamientoLista;
	}

	public void setAlojamientoLista() {
		this.alojamientoLista = CargarListaAlojamientos();
	}
	
	public ArrayList<Alojamiento> CargarListaAlojamientos()
	{
		//ResultSet rs=controlador.miModelo.gestorBBDD.HacerConsulta("SELECT * FROM HOTEL ORDER BY UBICACION");
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		/*
		try {
			while (rs.next()) {
				int cod_alojamiento=rs.getInt("COD_HOTEL");
				String ubicacion=rs.getString("UBICACION");
				String nombre=rs.getString("NOMBRE");
				//float precio=rs.getFloat("PRECIO");
				alojamientos.add(new Alojamiento(cod_alojamiento, nombre, ubicacion));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return alojamientos;
	}
	
}
