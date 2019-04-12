package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import modelo.Alojamiento;

public class ControladorSelAlojamiento implements Initializable {
	

	@FXML
    private AnchorPane contenedor;

    @FXML
    private Pane paneBusqueda;

    @FXML
    private JFXTextField textCiudad;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private Pane paneFiltros;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	JFXDepthManager.setDepth(paneFiltros, 2);
    	JFXDepthManager.setDepth(paneBusqueda, 1);
    	
    	ArrayList<String> ciudades = Principal.modelo.gestorBBDD.cargarListaDestinos();
		ciudades.addAll(Principal.modelo.gestorBBDD.cargarListaAlojamientos());
		TextFields.bindAutoCompletion( textCiudad, ciudades );
	}
    
    @FXML
    void AutoBuscar(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER)
    	{
    		Buscar();
    	}
    }

    @FXML
    void BtnBuscarPulsado(MouseEvent event) {
    	Buscar();
    }	
	
	void Buscar() {
    	
    	// crea y añade el grid al anchorpane 'contenedor', creado por defecto
    	GridPane grid = new GridPane();
    	contenedor.getChildren().setAll(grid);
    	
    	// hace que la columna 1 del grid ocupe todo el espacio disponible en el padre
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	grid.setStyle("-fx-background-color: transparent;");
    	// hace que los anchorpanes rellenen todo el espacio disponible en el padre
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        
        
		// cargamos la arraylist de alojamientos
		ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarListaAlojamientos(textCiudad.getText());
        
    	for(int i=0; i<alojamientos.size(); i++) {
    		
    		Alojamiento alojamiento = alojamientos.get(i);
    		AnchorPane anchorPane = new AnchorPane();
    		
    		// añadimos la accion que se ejecutara al clickar el panel
    		anchorPane = añadirListenerSeleccion(anchorPane, alojamiento);
    		
    		// label - nombre del alojamiento
    		Text nombreHotel = new Text(alojamiento.getNombre());
    		nombreHotel.setLayoutX(170);
    		nombreHotel.setLayoutY(30);
    		nombreHotel.setFill(Color.BLACK);
    		nombreHotel.setStyle("-fx-font: 25 arial;");
    		nombreHotel.setLayoutX(170);
    		nombreHotel.setLayoutY(35);
    		
    		// Ubicacion del alojamiento
    		Text ubicacion = new Text(alojamiento.getUbicacion());
    		ubicacion.setLayoutX(200);
    		ubicacion.setLayoutY(60);
    		
    		// label - descripcion del alojamiento
    		Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(170);
    		descripcion.setLayoutY(85);
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHotel, descripcion, ubicacion);
        	
        	AnchorPane paneSuperior = new AnchorPane();
        	paneSuperior.getChildren().addAll(anchorPane);
        	
        	JFXRippler rippler = new JFXRippler(anchorPane);
        	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));
        	paneSuperior.getChildren().add(rippler);
        	
    		anchorPane.setStyle("-fx-background-color: #fff; -fx-padding: 5px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-border-radius:  1 1 1 1; -fx-background-radius: 5 5 5 5;");
    		JFXDepthManager.setDepth(anchorPane, 1);
    		anchorPane.setPrefWidth(contenedor.getWidth()-20);
    		anchorPane.setMaxWidth(contenedor.getWidth()-20);
    		// añade el anchorpane al grid
    		grid.add(paneSuperior, 0, i);
    	}
	}
	
	public AnchorPane añadirListenerSeleccion(AnchorPane anchorPane, Alojamiento alojamiento) {
		anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				Principal.modelo.alojamiento=alojamiento;
				Principal.aplicacion.CambiarScene("InfoReserva.fxml");
			}
		});
		return anchorPane;
	}

}