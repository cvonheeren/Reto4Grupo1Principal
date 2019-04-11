package aplicacion;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Aplicacion{

	//public Bienvenida bienvenida;

	private Stage stage;
	
	public Aplicacion(Stage stage) {
		
		this.stage=stage;
		
		//Codigo para cambiar de escena
		CambiarScene("Bienvenida.fxml");
		
		// centra el stage (frame principal) en la pantalla
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		
	}
	
	public void CambiarScene(String FXMLLink)
	{
		Parent FXML = null;
		try {
			FXML = FXMLLoader.load(getClass().getResource("/vista/" + FXMLLink));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(FXML);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

}
