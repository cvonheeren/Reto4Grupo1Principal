package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Hotel;

public class ControladorInformacionAloj implements Initializable {
	
	@FXML
	private ImageView img;
	
	@FXML
	private Text nombAloj, ubicacion;
	
	@FXML
	private Label descAloj, habitaciones, tituloHab;
    
    @FXML
    private Hyperlink verMapa;
	
    @FXML
    private JFXButton atras, reservar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Alojamiento alojamiento = Principal.modelo.reserva.getAlojamiento();
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		img.setImage(new Image (alojamiento.getImgurl()));
		ubicacion.setText(alojamiento.getUbicacion());
		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX()+600);
		verMapa.setLayoutX(tamanoUbicacion);
		if(alojamiento instanceof Hotel) {
			ArrayList<Habitacion> habitacionesAloj = alojamiento.getHabitaciones();
			String str = mostrarHabitaciones(habitacionesAloj);
			habitaciones.setText(str);
		} else  {
			tituloHab.setText("Estancias del alojamiento:");
		}
	}
	
	@FXML
    void verMapa(ActionEvent event) {
		Principal.aplicacion.verMapa("Mapa.fxml");
    }
	
	@FXML
	void atras(ActionEvent event) {
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	}
	 
	@FXML
	void reservar(ActionEvent event) {
		Principal.aplicacion.CambiarScene("Pasos.fxml");
	}
	
    /**
     * 
     * @param habitaciones
     * @return
     */
    public String mostrarHabitaciones(ArrayList<Habitacion> habitaciones) {
    	String str = "";
		for (Habitacion s : habitaciones) {
			str += s.getNombre() + " - ";
		    if (s.getCtaCamasSimples() > 0 ) {
		    	str += "Cama Individual x " + s.getCtaCamasSimples() + " - ";
		    }
		    if (s.getCtaCamasMatrimonio() > 0 ) {
		    	str += "Cama Matrimonio x " + s.getCtaCamasMatrimonio() + " - ";
		    }
		    if (s.getCtaCamasInfantil() > 0 ) {
		    	str += "Cama Infantil x " + s.getCtaCamasInfantil() + " - ";
		    }
		    str += "Cantidad: " + s.getCantidad() + "\n";
		}
		return str;
    }
	
}