package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import core.Principal;
import modelo.Alojamiento;
import modelo.Habitacion;
import vista.CardHabitacion;

public class ControladorSelHabitacion implements Initializable {
    
	@FXML
	private AnchorPane paneHabitacion, main;
	
	@FXML
	private Label titulo;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cargarHabitaciones(Principal.modelo.reserva.getAlojamiento());
	}
    
    public void cargarHabitaciones(Alojamiento alojamiento) {
    	
    	GridPane grid = crearGrid();
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), Principal.modelo.reserva.getFechaEntrada(), Principal.modelo.reserva.getFechaSalida());
        
    	for(int i=0; i<habitaciones.size(); i++) {
    		
    		Habitacion habitacion = habitaciones.get(i);

    		// crea la tarjeta con la informacion del alojamiento
    		CardHabitacion card = new CardHabitacion(habitacion);
        	
        	AnchorPane paneSuperior = new AnchorPane();
        	JFXRippler rippler = new JFXRippler(card);
    		JFXDepthManager.setDepth(card, 1);
        	paneSuperior.getChildren().addAll(card);
        	paneSuperior.getChildren().add(rippler);
        	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));
    		
    		// añade el anchorpane al grid
    		grid.add(paneSuperior, 0, i);
    		
    	}
    	
    }
    
	/**
     * Crea y añade el grid al anchorpane 'contenedor', creado por defecto
     * @return
     */
    public GridPane crearGrid() {
    	GridPane grid = new GridPane();
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	grid.setStyle("-fx-background-color: transparent;");
    	paneHabitacion.getChildren().setAll(grid);
    	return grid;
    }

}