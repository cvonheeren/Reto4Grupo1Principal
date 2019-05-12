package vista;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import core.Principal;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modelo.Habitacion;

public class CardHabitacion extends AnchorPane implements Initializable {
	
	@FXML
    private AnchorPane card;

	@FXML
	private Text tipo;

    @FXML
    private Label descripcion;

    @FXML
    private Text precio;

    @FXML
    private Text camas;
    
    @FXML
    private ChoiceBox<Integer> cantidad;
    
    private Habitacion habitacion;
    
	public CardHabitacion(Habitacion habitacion) {
		
		this.habitacion = habitacion;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Habitacion.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tipo.setText(this.habitacion.getNombre());
		this.descripcion.setText(this.habitacion.getDescripcion());
		this.precio.setText(this.habitacion.getTarifaNormal() + "€");
		this.camas.setText(textoCamas());
		
		// ChoiceBox - Cantidad de habitaciones
		ArrayList<Integer> numHab = new ArrayList<Integer>();
		for (int j = 0; j <= habitacion.getCantidad(); j++) {
			numHab.add(j);
		}
		cantidad.getItems().addAll(numHab);
		cantidad.getSelectionModel().selectFirst();
		cantidad.setOnAction((e) -> {
			if(cantidad.getValue()>0)
				guardarHabitacion(habitacion, cantidad.getValue());
			else
				Principal.modelo.reserva.removeHabitacion(habitacion);
        });
		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
//				if(cb.getValue() < habitacion.getCantidad()) {
				cantidad.getSelectionModel().selectNext();
					guardarHabitacion(habitacion, cantidad.getValue());
//				} else {
//					Principal.aplicacion.mostrarMensaje(main, "No quedan más habitaciones");
//				}
			}
		});
	}
	
	public String textoCamas() {
		String str = "";
		if (habitacion.getCtaCamasSimples() > 0 ) {
		    str += "- Cama Individual x " + habitacion.getCtaCamasSimples() + "\n";
		}
		if (habitacion.getCtaCamasMatrimonio() > 0 ) {
			str += "- Cama Matrimonio x " + habitacion.getCtaCamasMatrimonio() + "\n";
		}
		if (habitacion.getCtaCamasInfantil() > 0 ) {
			str += "- Cama Infantil x " + habitacion.getCtaCamasInfantil();
		}
	    return str;
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