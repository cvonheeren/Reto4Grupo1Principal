package aplicacion;

import java.io.IOException;
import java.util.Optional;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Aplicacion {

	public Stage stage;
	public Stage stageLogin;
	private JFXSnackbar snackbar;
	public ControladorPasos controladorPasos;
	public Stage stagePopupInfo;
	public ControladorSelAlojamiento controladorSelAlojamiento;
	public ControladorPago controladorPago;
	public ControladorInformacionAloj controladorInformacionAloj;
	public ControladorFactura controladorFactura;
	public ControladorLoginRegistro controladorLoginRegistro;
	private Scene sceneLogin;
	
	@FXML
    private Pane anchorPaneBase;
	
	/**
	 * Setea algunos datos de la aplicacion
	 * @param stage El objeto principal de la vista
	 */
	public Aplicacion(Stage stage) {
		
		this.stage = stage;
		
		// Codigo para cambiar de escena
		CambiarScene("SeleccionAlojamiento.fxml");
		
		// centra el stage (frame principal) en la pantalla
		centrarStage();
		
		// evento de cierre de la app
		stage.setOnCloseRequest(confirmCloseEventHandler);
	}
	
	/**
	 * Centra el stage (frame principal) en la pantalla
	 */
	public void centrarStage() {
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
	/**
	 * 
	 * @param bounds
	 */
	public void centrarPopup(Bounds bounds) {
		stagePopupInfo.setX(stage.getX()+700);
		stagePopupInfo.setY(stage.getY()+525);
	}
	
	/**
	 * Cambia de escena (vista)
	 * @param FXMLLink
	 */
	public void CambiarScene(String FXMLLink) {
		Parent FXML = loadFXML(FXMLLink);
		if (FXML != null) {
			Scene scene = new Scene(FXML);
			scene.getStylesheets().add("reto4.css");
			stage.setTitle("BiDaiOn ALPHA");
			stage.setScene(scene);
			stage.show();
		}
	}
	
	/**
	 * 
	 * @param FXMLLink
	 */
	public void verMapa(String FXMLLink) {
		Parent FXML = loadFXML(FXMLLink);
		if (FXML != null) {
			Stage stage = new Stage();
			Scene scene = new Scene(FXML);
			scene.getStylesheets().add("reto4.css");
			stage.setTitle("Mapa");
			stage.setScene(scene);
			stage.show();
		}
	}

	/**
	 * 
	 */
	public void CargarSceneLogin()
	{
		Parent FXML = loadFXML("LoginRegistro.fxml");
		if (FXML != null) {
			try{stageLogin.close();}catch(Exception e) {}
			sceneLogin = new Scene(FXML);
			sceneLogin.getStylesheets().add("reto4.css");
			stageLogin = new Stage();
			stageLogin.setTitle("Iniciar sesion");
			stageLogin.setScene(sceneLogin);
			stageLogin.show();
		}
	}
	
	/**
	 * 
	 * @param bounds
	 */
	public void CargarpopupInfo(Bounds bounds)
	{
		Parent FXML = loadFXML("PopupInfo.fxml");
		if (FXML != null) {
			try{stagePopupInfo.close();}catch(Exception e) {}
			Scene scene = new Scene(FXML);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add("reto4.css");
			stagePopupInfo = new Stage(StageStyle.TRANSPARENT);
			stagePopupInfo.setTitle("Informacion");
			stagePopupInfo.setScene(scene);
			stagePopupInfo.setAlwaysOnTop(true);
			stagePopupInfo.show();
			centrarPopup(bounds);
		}
	}
	
	/**
	 * Carga un archivo FXML
	 * @param FXMLLink
	 * @return
	 */
	public Parent loadFXML(String FXMLLink) {
		Parent FXML = null;
		try {
			FXML = FXMLLoader.load(getClass().getResource("/vista/" + FXMLLink));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FXML;
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
	

	
    /**
     * 
     * @param anchorpane
     * @param mensaje
     */
    public void mostrarMensaje(AnchorPane anchorpane, String mensaje) {
		Text nodo = new Text(mensaje);
		snackbar = new JFXSnackbar(anchorpane);
		snackbar.enqueue(new SnackbarEvent(nodo));
		snackbar.getStylesheets().setAll("reto4.css");
	}
    
    
    /**
     * 
     * @param pregunta
     * @return
     */
    public boolean VentanaSiNo(String pregunta)
    {
    	Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                pregunta
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Si");

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            return false;
        }
        else
        	return true;
    }

}