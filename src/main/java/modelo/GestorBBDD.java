package modelo;

import java.sql.Connection;
import java.sql.ResultSet;

import Reto4Grupo1BBDD.ConexionBBDD;
import Reto4Grupo1BBDD.ModificarBBDD;
import Reto4Grupo1BBDD.Textos;

public class GestorBBDD {
	

	public GestorBBDD()
	{
		
	}
	
	private Connection conectar()
	{
		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Textos textos = new Textos();
		return conexionBBDD.conectarBD(textos);
	}
	
	public ResultSet HacerConsulta(String string)
	{
		ModificarBBDD modificarBBDD = new ModificarBBDD();
		return modificarBBDD.hacerConsultaBD(conectar(), string);
	}

	
}
