package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Reto4Grupo1BBDD.ModificarBBDD;
import Reto4Grupo1BBDD.Pool;

public class GestorBBDD {
	
	Connection conn = null;
	Pool pool = null;

	public GestorBBDD() {
		pool = new Pool();
	}
	
	private Connection conectar() {
		try {
			conn = pool.getConnection();
		} catch (SQLException e) {
			//Implementar logger?
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return conn;
	}
	
	public ResultSet HacerConsulta(String string) {
		ModificarBBDD modificarBBDD = new ModificarBBDD();
		return modificarBBDD.hacerConsultaBD(conectar(), string);
	}
	
	/*public int insertarDatos(String string) {
		ModificarBBDD modificarBBDD = new ModificarBBDD();
		return modificarBBDD.insertarDatosBD(conectar(), string);
	}*/
	
}
