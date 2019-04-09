package core;

import aplicacion.Aplicacion;
import core.Principal;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Modelo;

public class Principal extends Application {

	public static Modelo modelo;
	public static Aplicacion aplicacion;
	
	/**
	 * Método que inicia el programa
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Método que crea el modelo y la vista y las instancia al constructor del controlador
	 */
	
	@Override
    public void start(Stage stage)
    {
		//modelo = new Modelo();
		aplicacion = new Aplicacion(stage);
    }
}
