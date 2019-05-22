package aplicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.jfoenix.controls.JFXDecorator;
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
import modelo.Alojamiento;

public class Aplicacion {

	public Stage stage;
	public Stage stageLogin;
	public Stage stagePopupInfo;
	public Stage stageDialogo;
	public Stage stageCargando;
	
	public ControladorPasos controladorPasos;
	public ControladorSelAlojamiento controladorSelAlojamiento;
	public ControladorPago controladorPago;
	public ControladorInformacionAloj controladorInformacionAloj;
	public ControladorFactura controladorFactura;
	public ControladorLoginRegistro controladorLoginRegistro;
	
	private JFXSnackbar snackbar;
	public ArrayList<Alojamiento> busquedaAlojamientos;
	public String textoBusqueda = "";
	public String textoTemporal;
	
	
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
	 * Cetra el popup de info
	 * @param bounds
	 */
	public void centrarPopup(Bounds bounds) {
		stagePopupInfo.setX(stage.getX()+720);
		stagePopupInfo.setY(stage.getY()+110);
	}
	
	/**
	 * Cambia de escena (vista)
	 * @param FXMLLink
	 */
	public void CambiarScene(String FXMLLink) {
		Parent FXML = loadFXML(FXMLLink);
		if (FXML != null) {
			Stage stageAntiguo = stage;
			stage = new Stage();
			JFXDecorator decorator = new JFXDecorator(stage, FXML, false, false, true);
			decorator.getStylesheets().add("reto4.css");
			Scene scene = new Scene(decorator);
			scene.getStylesheets().add("reto4.css");
			stage.setTitle("BiDaiOn ALPHA");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			// evento de cierre de la app
			stage.setOnCloseRequest(confirmCloseEventHandler);
			stageAntiguo.close();
		}
	}
	
	/**
	 * Muestra el mapa
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
	 * Abre la ventana de las bases legales
	 */
    public void verBases() {
		Parent FXML = loadFXML("Bases.fxml");
		if (FXML != null) {
			Stage stageBases = new Stage();
			Scene scene = new Scene(FXML);
			try{stageBases.close();}catch(Exception e) {}
			stageBases.setTitle("Bases legales");
			stageBases.setScene(scene);
			stageBases.show();
		}
    }
	
	/**
	 * Carga la info del popup de información
	 * @param bounds
	 */
	public void cargarPopupInfo(Bounds bounds) {
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
        else
        {
        	System.exit(0);
        }
    };

    /**
     * 
     * @param anchorpane
     * @param mensaje
     */
    public void mostrarMensaje(AnchorPane anchorpane, String mensaje) {
		Text nodo = new Text(mensaje);
		nodo.setFill(Color.WHITE);
		snackbar = new JFXSnackbar(anchorpane);
		snackbar.enqueue(new SnackbarEvent(nodo));
		snackbar.getStylesheets().setAll("reto4.css");
		//AbrirDialogo("PRUEBA");
	}
    
    /**
     * 
     * @param texto
     */
    public void AbrirDialogo(String texto)
    {
    	textoTemporal=texto;
    	Parent FXML = loadFXML("Dialogo.fxml");
		if (FXML != null) {
			try{stageDialogo.close();}catch(Exception e) {}
			Scene scene = new Scene(FXML);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add("reto4.css");
			stageDialogo = new Stage(StageStyle.TRANSPARENT);
			stageDialogo.setTitle("Informacion");
			stageDialogo.setScene(scene);
			stageDialogo.setAlwaysOnTop(true);
			stageDialogo.show();
			stageDialogo.setX(stage.getX()+8);
			stageDialogo.setY(stage.getY()+31);
		}
    }
    
    /**
     * 
     * @param pregunta
     * @return
     */
    public boolean ventanaSiNo(String pregunta)
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