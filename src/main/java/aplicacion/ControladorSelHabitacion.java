package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import core.Principal;
import modelo.Alojamiento;
import modelo.Habitacion;

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
    	
    	// crea y añade el grid al anchorpane 'contenedor', creado por defecto
    	GridPane grid = new GridPane();
    	paneHabitacion.getChildren().setAll(grid);
    	
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
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), Principal.modelo.reserva.getFechaEntrada(), Principal.modelo.reserva.getFechaSalida());
        
    	for(int i=0; i<habitaciones.size(); i++) {
    		
    		Habitacion habitacion = habitaciones.get(i);
    		AnchorPane anchorPane = new AnchorPane();
    		
    		String str = "";
		    if (habitacion.getCtaCamasSimples() > 0 ) {
		    	str += "Cama Individual x " + habitacion.getCtaCamasSimples() + "\n";
		    }
		    if (habitacion.getCtaCamasMatrimonio() > 0 ) {
		    	str += "Cama Matrimonio x " + habitacion.getCtaCamasMatrimonio() + "\n";
		    }
		    if (habitacion.getCtaCamasInfantil() > 0 ) {
		    	str += "Cama Infantil x " + habitacion.getCtaCamasInfantil();
		    }
    		
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
    		/*Text precio = new Text(Principal.modelo.reserva);
    		precio.setLayoutX(675);
    		precio.setLayoutY(80);
    		precio.setStyle("-fx-font: 20 arial;");
    		precio.setFill(Paint.valueOf("#0ab21b"));*/
    		
    		// label - descripcion del habitacion
    		/*Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(170);
    		descripcion.setLayoutY(85);*/
    		
    		// ChoiceBox - Cantidad de habitaciones
    		ArrayList<Integer> numHab = new ArrayList<Integer>();
    		for (int j = 0; j <= habitacion.getCantidad(); j++) {
    			numHab.add(j);
    		}
    		ChoiceBox<Integer> cb = new ChoiceBox<Integer>(FXCollections.observableArrayList(numHab));
    		cb.getSelectionModel().selectFirst();
    		cb.setLayoutX(675);
    		cb.setLayoutY(120);
    		cb.setOnAction((e) -> {
    			if(cb.getValue()>0)
    				guardarHabitacion(habitacion, cb.getValue());
    			else
    				Principal.modelo.reserva.removeHabitacion(habitacion);
            });
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHab, camas, iconoCama, cb);
        	
        	AnchorPane paneSuperior = new AnchorPane();
        	paneSuperior.getChildren().addAll(anchorPane);
        	
        	JFXRippler rippler = new JFXRippler(anchorPane);
        	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));
        	paneSuperior.getChildren().add(rippler);
        	
    		anchorPane.setStyle("-fx-background-color: #fff; -fx-padding: 5px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-border-radius:  1 1 1 1; -fx-background-radius: 5 5 5 5;");
    		JFXDepthManager.setDepth(anchorPane, 1);
    		anchorPane.setPrefWidth(paneHabitacion.getWidth()-20);
    		anchorPane.setMaxWidth(paneHabitacion.getWidth()-20);
    		
    		// añade el anchorpane al grid
    		grid.add(paneSuperior, 0, i);
    	
    		
    		anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
    			@Override
    			public void handle(Event event) {
    				if(cb.getValue() < habitacion.getCantidad()) {
    					cb.getSelectionModel().selectNext();
    					guardarHabitacion(habitacion, cb.getValue());
    				} else {
    					Principal.aplicacion.mostrarMensaje(main, "No quedan más habitaciones");
    				}
    			}
    		});
    		
    	}
    	
    	
    	
    }
    
    /**
     * 
     * @param habitacion
     * @param cantidad
     */
    public void guardarHabitacion(Habitacion habitacion, int cantidad) {
		ArrayList<Habitacion> habitacionesReservadas = Principal.modelo.reserva.getHabitacionesReservadas();
		if (habitacionesReservadas.size() == 0) {
			Principal.modelo.reserva.addHabitacion(habitacion);
			Principal.modelo.reserva.getHabitacionesReservadas().get(habitacionesReservadas.size()-1).setCantidad(cantidad);
			return;
		}
		for (int i = 0;i < habitacionesReservadas.size(); i++) {
			if (habitacionesReservadas.get(i).getNombre().equals(habitacion.getNombre())) {
				habitacionesReservadas.get(i).setCantidad(cantidad);
				return;
			}
		}
		Principal.modelo.reserva.addHabitacion(habitacion);
		Principal.modelo.reserva.getHabitacionesReservadas().get(habitacionesReservadas.size()-1).setCantidad(cantidad);
	}

}