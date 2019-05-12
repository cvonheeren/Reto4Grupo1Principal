package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import core.Principal;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import modelo.Habitacion;

public class ControladorFactura implements Initializable {

	@FXML
    private Label nombre, fechaEntrada, fechaSalida, precio;
	
	@FXML
	private Text ubicacion;
   
	@FXML
	private ImageView imagen;
   
	@FXML
	private Hyperlink mapa;
	
    @FXML
    private FlowPane habitaciones;

    @FXML
    private JFXButton btnimprimir;
    
    @FXML
    private JFXButton inicio;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Principal.aplicacion.controladorFactura = this;
		actualizarDatos();
	}
    
	@FXML
    void verMapa(ActionEvent event) {
		Principal.aplicacion.verMapa("Mapa.fxml");
    }

    @FXML
    void imprimir(ActionEvent event) {
    	String pathReserva = Principal.modelo.gestorArchivos.preguntarGuadar();
    	if (pathReserva != null) {
    		Principal.modelo.gestorArchivos.crearTxtReserva(pathReserva, Principal.modelo.reserva);
    	}
    }
    
    @FXML
    void iniciar(ActionEvent event) {
    	Principal.iniciarPrograma();
    }
	
	public void actualizarDatos() {
		nombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		ubicacion.setText(Principal.modelo.reserva.getAlojamiento().getUbicacion());
		fechaEntrada.setText(Principal.modelo.reserva.getFechaEntrada().toLocalDate().toString());
		fechaSalida.setText(Principal.modelo.reserva.getFechaSalida().toLocalDate().toString());
		precio.setText(Principal.modelo.reserva.getPrecio() + "€");
		imagen.setImage(new Image (Principal.modelo.reserva.getAlojamiento().getImgurl()));
		for (Habitacion h: Principal.modelo.reserva.getHabitacionesReservadas()) {
			Label textoHabitacion = new Label(h.getNombre() + " x " + h.getCantidad());
			textoHabitacion.setMinWidth(370);
			habitaciones.getChildren().add(textoHabitacion);
		}
		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX() + ubicacion.getLayoutX() + 5);
		mapa.setLayoutX(tamanoUbicacion);
	}

}
