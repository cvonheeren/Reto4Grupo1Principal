package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.Controlador;

public class Alojamiento {

	protected int codAlojamiento;
	protected String nombre;
	protected String ubicacion;
	
	/**
	 * Objeto padre Alojamiento
	 * @param codAlojamiento Codigo principal del alojamiento
	 * @param nombre Nombre
	 * @param ubicacion Ubicacion
	 */
	public Alojamiento(int codAlojamiento, String nombre, String ubicacion) {
		this.codAlojamiento = codAlojamiento;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	public Alojamiento() {
		
	}

	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public ArrayList<Alojamiento> CargarListaAlojamientos(Controlador controlador)
	{
		ResultSet rs=controlador.miModelo.gestorBBDD.HacerConsulta("SELECT * FROM HOTEL ORDER BY UBICACION");
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		
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
		}
		return alojamientos;
	}
	 
	@Override
	public String toString()
	{
		return nombre + " (" + ubicacion + ")";
	}
}
