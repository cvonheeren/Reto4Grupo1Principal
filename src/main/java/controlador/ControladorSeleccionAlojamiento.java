package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Alojamiento;
import modelo.Hotel;

public class ControladorSeleccionAlojamiento {
	
	Controlador controlador;

	public ControladorSeleccionAlojamiento(Controlador controlador)
	{
		this.controlador=controlador;
	}
	
	public ArrayList<Alojamiento> CargarListaAlojamientos(Controlador controlador)
	{

		ResultSet rs;
		
		rs=controlador.miModelo.gestorBBDD.HacerConsulta("SELECT * FROM HOTEL");
		
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		
		try {
			while (rs.next()) {
				int cod_alojamiento=rs.getInt("COD_HOTEL");
				String ubicacion=rs.getString("UBICACION");
				String nombre=rs.getString("NOMBRE");
				float precio=rs.getFloat("PRECIO");
				alojamientos.add(new Alojamiento(cod_alojamiento, nombre, ubicacion));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return alojamientos;
	}
	
}
