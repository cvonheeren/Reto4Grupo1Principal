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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Modelo;

public class ControladorSelHabitacion implements Initializable {
    
    @FXML
    private AnchorPane contenedor;

    @FXML
    private Label titulo;

    @FXML
    private JFXButton atras;

    @FXML
    private JFXButton siguiente;

    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
    }

    @FXML
    void siguiente(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("InfoReserva.fxml");
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cargarHabitaciones(Principal.modelo.alojamiento);
	}	
	
	void cargarHabitaciones(Alojamiento alojamiento) {
    	
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
        
		// cargamos la arraylist de habitaciones
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), Principal.modelo.fechaEntrada, Principal.modelo.fechaSalida);
        
    	for(int i=0; i<habitaciones.size(); i++) {
    		
    		Habitacion habitacion = habitaciones.get(i);
    		AnchorPane anchorPane = new AnchorPane();
    		
    		String str = "";
		    if (habitacion.getCtaCamasSimples() > 0 ) {
		    	str += "Camas Individuales: " + habitacion.getCtaCamasSimples() + "\n";
		    }
		    if (habitacion.getCtaCamasMatrimonio() > 0 ) {
		    	str += "Camas Matrimonio: " + habitacion.getCtaCamasMatrimonio() + "\n";
		    }
		    if (habitacion.getCtaCamasInfantil() > 0 ) {
		    	str += "Camas Infantiles: " + habitacion.getCtaCamasInfantil();
		    }
    		
    		// añadimos la accion que se ejecutara al clickar el panel
    		//anchorPane = añadirListenerSeleccion(anchorPane, habitacion);
    		
    		// label - nombre de la habitacion
    		Text nombreHab = new Text(habitacion.getNombre());
    		nombreHab.setLayoutX(170);
    		nombreHab.setLayoutY(30);
    		nombreHab.setFill(Paint.valueOf("#07c"));
    		nombreHab.setStyle("-fx-font: 25 arial;");
    		nombreHab.setLayoutX(170);
    		nombreHab.setLayoutY(35);
    		
    		FontAwesomeIconView iconoCama = new FontAwesomeIconView(FontAwesomeIcon.BED);
    		iconoCama.setLayoutX(180);
    		iconoCama.setLayoutY(60);
    		iconoCama.setSize("20");
    		iconoCama.setFill(Paint.valueOf("#555555"));		
    		
    		Text camas = new Text(str);
    		camas.setLayoutX(200);
    		camas.setLayoutY(60);
    		
    		// precio
    		Text precio = new Text("100€");
    		precio.setLayoutX(675);
    		precio.setLayoutY(80);
    		precio.setStyle("-fx-font: 20 arial;");
    		precio.setFill(Paint.valueOf("#0ab21b"));
    		
    		// label - descripcion del habitacion
    		Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(170);
    		descripcion.setLayoutY(85);
    		
    		// ChoiceBox - Cantidad de habitaciones
    		ArrayList<Integer> numHab = new ArrayList<Integer>();
    		numHab.add(0);
    		for (int j = 1; j <= habitacion.getCantidad(); j++) {
    			numHab.add(j);
    		}
    		ChoiceBox<Integer> cb = new ChoiceBox<Integer>(FXCollections.observableArrayList(numHab));
    		cb.getSelectionModel().selectFirst();
    		cb.setLayoutX(675);
    		cb.setLayoutY(120);
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHab, descripcion, camas, iconoCama, precio, cb);
        	
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
	
	public AnchorPane añadirListenerSeleccion(AnchorPane anchorPane) {
		anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				Principal.aplicacion.CambiarScene("InfoReserva.fxml");
			}
		});
		return anchorPane;
	}

}