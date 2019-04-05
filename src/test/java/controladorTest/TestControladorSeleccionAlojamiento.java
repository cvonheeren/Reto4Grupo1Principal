package controladorTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.Controlador;
import controlador.ControladorSeleccionAlojamiento;
import modelo.Modelo;
import vista.Vista;

public class TestControladorSeleccionAlojamiento {

	Modelo miModelo = new Modelo();
	Vista miVista = new Vista();
	Controlador controlador = new Controlador(miModelo, miVista);
	ControladorSeleccionAlojamiento controladorSeleccionAlojamiento = new ControladorSeleccionAlojamiento(controlador);
	
	@Test
	public void test() {
		controladorSeleccionAlojamiento.CargarListaAlojamientos(controlador);
	}

}
