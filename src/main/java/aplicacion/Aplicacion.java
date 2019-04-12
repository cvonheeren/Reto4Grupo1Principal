package aplicacion;

import java.io.IOException;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Aplicacion{

	public Stage stage;
	
	/**
	 * Lanza la aplicacion
	 * @param stage El objeto que inicia la app
	 */
	public Aplicacion(Stage stage) {
		
		this.stage=stage;
		
		//Codigo para cambiar de escena
		CambiarScene("Bienvenida.fxml");
		
		// centra el stage (frame principal) en la pantalla
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		
		//evento de cierre de la app
		stage.setOnCloseRequest(confirmCloseEventHandler);
	}
	
	/**
	 * Cambia las escenas
	 * @param FXMLLink
	 */
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
	
	/**
	 * Controla el evento cuando se cierra la app
	 */
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "La aplicación se va a cerrar, ¿está seguro?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Salir");
        closeConfirmation.setHeaderText("Saliendo");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(stage);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };

}
