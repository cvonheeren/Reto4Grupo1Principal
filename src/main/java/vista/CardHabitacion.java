package vista;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

import modelo.Habitacion;

public class CardHabitacion extends AnchorPane implements Initializable {
	
	@FXML
    private AnchorPane card;

    @FXML
    private Label descripcion, tipo, precio, camas;
    
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
		LocalDate fecha1 = Principal.modelo.reserva.getFechaEntrada().toLocalDate();
		LocalDate fecha2 = Principal.modelo.reserva.getFechaSalida().toLocalDate();
		float precio = Principal.modelo.gestorDinero.getPrecioTotalHabitacion(habitacion, fecha1, fecha2);
		
		this.tipo.setText(this.habitacion.getNombre());
		this.descripcion.setText(this.habitacion.getDescripcion());
		this.precio.setText(precio + "€ \n" + Principal.modelo.gestorFechas.setDiasSeleccionados(fecha1, fecha2).size() + " noches");
		this.camas.setText(textoCamas());
		
		ArrayList<Integer> numHab = new ArrayList<Integer>();
		for (int j = 0; j <= habitacion.getCantidad(); j++) {
			numHab.add(j);
		}
		cantidad.getItems().addAll(numHab);
		cantidad.getSelectionModel().selectFirst();
		if (Principal.modelo.reserva != null && Principal.modelo.reserva.getHabitacionesSeleccionadas() != null && Principal.modelo.reserva.getHabitacionesSeleccionadas().size() > 0) {
			for ( Habitacion h: Principal.modelo.reserva.getHabitacionesSeleccionadas()) {
				if (h.getNombre().equals(habitacion.getNombre())) {
					cantidad.setValue(h.getCantidad());
				}
			}
		}
		cantidad.setOnAction((e) -> {
			if(cantidad.getValue()>0) {
				guardarHabitacion(habitacion, cantidad.getValue());
				Principal.aplicacion.controladorPasos.actualizarCarrito();
				float precioTotal = Principal.modelo.gestorDinero.calcularPrecioConDescuentos(Principal.modelo.reserva.getHabitacionesSeleccionadas(), fecha1, fecha2);
				Principal.modelo.gestorDinero.setPrecio(precioTotal);
			} else {
				Principal.modelo.reserva.removeHabitacion(habitacion);
			}
			
        });
		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
//				if(cb.getValue() < habitacion.getCantidad()) {
				cantidad.getSelectionModel().selectNext();
				guardarHabitacion(habitacion, cantidad.getValue());
				Principal.aplicacion.controladorPasos.actualizarCarrito();
//				} else {
//					Principal.aplicacion.mostrarMensaje(main, "No quedan más habitaciones");
//				}
			}
		});
	}
	
	/**
	 * Actualiza el selector de habitacion
	 * @param cantidad
	 */
	public void actualizarCantidad(int cantidad) {
		this.cantidad.setValue(cantidad);
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
     * Guarda el tipo de habitacion seleccionada y su cantidad en el objeto reserva
     * @param habitacion
     * @param cantidad
     */
    public void guardarHabitacion(Habitacion habitacion, int cantidad) {
    	Principal.aplicacion.controladorPasos.AnimacionCama();
    	ArrayList<Habitacion> habitacionesReservadas = Principal.modelo.reserva.getHabitacionesSeleccionadas();
		if (habitacionesReservadas.size() == 0) {
			Principal.modelo.reserva.addHabitacion(habitacion);
			Principal.modelo.reserva.getHabitacionesSeleccionadas().get(habitacionesReservadas.size()-1).setCantidad(cantidad);
			return;
		}
		for (int i = 0;i < habitacionesReservadas.size(); i++) {
			if (habitacionesReservadas.get(i).getNombre().equals(habitacion.getNombre())) {
				habitacionesReservadas.get(i).setCantidad(cantidad);
				return;
			}
		}
		Principal.modelo.reserva.addHabitacion(habitacion);
		Principal.modelo.reserva.getHabitacionesSeleccionadas().get(Principal.modelo.reserva.getHabitacionesSeleccionadas().size()-1).setCantidad(cantidad);
		Principal.aplicacion.controladorPasos.carrito.setText(Integer.toString(cantidad));
	}
	
}