package controlador;

import java.util.ArrayList;

import modelo.Alojamiento;
import modelo.Hotel;

public class ControladorSeleccionAlojamiento {

	public ControladorSeleccionAlojamiento()
	{
		
	}
	
	public ArrayList<Alojamiento> CargarListaAlojamientos()
	{
		//Implementar filtro de busquedas...
		//IMPORTANTE sin acabar
		//Hacer llamada al proyecto BBDD y retornar Array con alojamientos
		

		//Array temporal para hacer pruebas
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		Alojamiento h1 = new Hotel(0, "Hotel Miramar", "Castro Urdiales", 3);
		Alojamiento h2 = new Hotel(1, "Hotel Meliá", "Bilbao", 4);
		Alojamiento h3 = new Hotel(2, "Hotel Von Christian", "Bremen", 5);
		alojamientos.add(h1);
		alojamientos.add(h2);
		alojamientos.add(h3);
		
		return alojamientos;
	}
	
}
