package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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

    }

    @FXML
    void siguiente(MouseEvent event) {

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
    	grid.setGridLinesVisible(true);
    	
    	// hace que los anchorpanes rellenen todo el espacio disponible en el padre
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        
		// cargamos la arraylist de alojamientos
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.cargarListaHabitaciones(alojamiento.getCodAlojamiento());
        
    	for(int i=0; i<habitaciones.size(); i++) {
    		
    		// FALTA
    		// copiar de controladorSelAlojamiento
    		
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