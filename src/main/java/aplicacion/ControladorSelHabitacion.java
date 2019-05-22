package aplicacion;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import com.jfoenix.effects.JFXDepthManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import core.Principal;
import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Hotel;
import vista.CardHabitacion;

public class ControladorSelHabitacion implements Initializable {
    
	@FXML
	private AnchorPane paneHabitacion, main;
	
	@FXML
	private Pane panePrincipal;
	
	@FXML
	private Label titulo;
	
	@FXML
	private JFXCheckBox reservarTodo;
	
	private Alojamiento alojamiento;
	
	ArrayList<CardHabitacion> cardHabitaciones;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cardHabitaciones = new ArrayList<CardHabitacion>();
    	this.alojamiento = Principal.modelo.reserva.getAlojamiento();
		cargarHabitaciones(alojamiento);
		if (alojamiento instanceof Hotel) {
			panePrincipal.getChildren().remove(reservarTodo);
		}
	}
    
    @FXML
    void reservarTodo(ActionEvent event) {
    	Date fechaEntrada = Principal.modelo.reserva.getFechaEntrada();
    	Date fechaSalida = Principal.modelo.reserva.getFechaSalida();
    	if(reservarTodo.isSelected()) {
    		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), fechaEntrada, fechaSalida);
        	Principal.modelo.reserva.setHabitacionesSeleccionadas(habitaciones);
        	paneHabitacion.setDisable(true);
        	for(int i=0; i<cardHabitaciones.size(); i++) {
        		cardHabitaciones.get(i).actualizarCantidad(habitaciones.get(i).getCantidad());
        	}
    	} else {
    		Principal.modelo.reserva.setHabitacionesSeleccionadas(null);
        	paneHabitacion.setDisable(false);
    	}	
    }
    
    public void cargarHabitaciones(Alojamiento alojamiento) {
    	
    	GridPane grid = crearGrid();
    	Date fechaEntrada = Principal.modelo.reserva.getFechaEntrada();
    	Date fechaSalida = Principal.modelo.reserva.getFechaSalida();
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), fechaEntrada, fechaSalida);
        
    	for(int i=0; i<habitaciones.size(); i++) {
    		
    		Habitacion habitacion = habitaciones.get(i);

    		// crea la tarjeta con la informacion del alojamiento
    		CardHabitacion card = new CardHabitacion(habitacion);
    		cardHabitaciones.add(card);
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