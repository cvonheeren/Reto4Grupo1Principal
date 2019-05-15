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
import modelo.Modelo;
import modelo.Reserva;
import modelo.GestorDinero;
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
    
	private Modelo modelo() {return Principal.modelo;}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Principal.aplicacion.controladorFactura = this;
		//actualizarDatos();
	}
    
	@FXML
    void verMapa(ActionEvent event) {
		Principal.aplicacion.verMapa("Mapa.fxml");
    }

    @FXML
    void imprimir(ActionEvent event) {
    	String pathReserva = modelo().gestorArchivos.preguntarGuadar();
    	if (pathReserva != null) {
    		modelo().gestorArchivos.crearTxtReserva(pathReserva, modelo().cliente, modelo().reserva, modelo().gestorDinero);
    	}
    }
    
    @FXML
    void iniciar(ActionEvent event) {
    	Principal.aplicacion.textoBusqueda = "";
		Principal.modelo.reserva = new Reserva();
		Principal.modelo.gestorDinero = new GestorDinero();
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
    }
	
	public void actualizarDatos() {
		nombre.setText(modelo().reserva.getAlojamiento().getNombre());
		ubicacion.setText(modelo().reserva.getAlojamiento().getUbicacion());
		fechaEntrada.setText(modelo().reserva.getFechaEntrada().toLocalDate().toString());
		fechaSalida.setText(modelo().reserva.getFechaSalida().toLocalDate().toString());
		precio.setText(modelo().gestorDinero.getPrecioConDescuento() + "€");
		imagen.setImage(new Image (modelo().reserva.getAlojamiento().getImgurl()));
		for (Habitacion h: modelo().reserva.getHabitacionesSeleccionadas()) {
			Label textoHabitacion = new Label(h.getNombre() + " x " + h.getCantidad());
			textoHabitacion.setMinWidth(370);
			habitaciones.getChildren().add(textoHabitacion);
		}
		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX() + ubicacion.getLayoutX() + 5);
		mapa.setLayoutX(tamanoUbicacion);
	}

}