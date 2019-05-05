package core;

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
		modelo = new Modelo();
		aplicacion = new Aplicacion(stage);
	}
	
}