package modelo;

import java.util.ArrayList;
import Reto4Grupo1BBDD.AlojamientoBBDD;
import Reto4Grupo1BBDD.ClienteBBDD;
import Reto4Grupo1BBDD.ModificarBBDD;

public class GestorBBDD {
	
	ModificarBBDD modificarBBDD = null;

	public GestorBBDD() {
		modificarBBDD = new ModificarBBDD();
	}
	
	public ArrayList<Alojamiento> cargarListaAlojamientos(String string) {
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		ArrayList<AlojamientoBBDD> alojamientosBBDD = modificarBBDD.cargarListaAlojamientos(string);
		for (int i = 0; i < alojamientosBBDD.size(); i++) {
			alojamientos.add(new Alojamiento(
				alojamientosBBDD.get(i).getCodAlojamiento(),
				alojamientosBBDD.get(i).getNombre(),
				alojamientosBBDD.get(i).getUbicacion())
			);
		}
		return alojamientos;
	}
	
	public ArrayList<String> cargarListaDestinos() {
		return modificarBBDD.cargarListaDestinos();
	}
	
	public Cliente cargarCliente(int dni) {
		ClienteBBDD clienteBBDD = modificarBBDD.cargarCliente(dni);
		Cliente cliente = new Cliente(
			clienteBBDD.getDni(),
			clienteBBDD.getPassword()
		);
		return cliente;
	}
	
	/*public int insertarDatos(String string) {
		return modificarBBDD.insertarDatosBD(string);
	}*/
	
}
