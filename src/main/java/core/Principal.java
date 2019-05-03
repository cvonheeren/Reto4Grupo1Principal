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
	 * Método que crea el modelo y la vista y las instancia al constructor del controlador
	 */
	
	@Override
    public void start(Stage stage)
    {
		this.stage=stage;
		IniciarPrograma();
    }
	
	public static void IniciarPrograma()
	{
		modelo = new Modelo();
		aplicacion = new Aplicacion(stage);
	}
}
