package core;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import aplicacion.Aplicacion;
import core.Principal;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Modelo;

public class Principal extends Application {

	public static Modelo modelo;
	public static Aplicacion aplicacion;
	private static Stage stage;
	
	/**
	 * Método que inicia el programa
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Inicia el programa
	 */
	@Override
    public void start(Stage stage) {
		Principal.stage = stage;
		iniciarPrograma();
    }
	
	/**
	 * Carga el modelo y el controlador
	 */
	public static void iniciarPrograma() {
		try{
			modelo = new Modelo();
			aplicacion = new Aplicacion(stage);
		} catch(Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
	}
	
}