package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import modelo.Alojamiento;

public class ControladorInformacionAloj implements Initializable {
	
	@FXML
	private ImageView img;
	
	@FXML
	private Text nombAloj;
	
	@FXML
	private Label descAloj;
	
	@FXML
	private WebView mapa;
	
    @FXML
    private Label habitaciones;
	
    @FXML
    private JFXButton atras;

    @FXML
    private JFXButton reservar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Alojamiento alojamiento = Principal.modelo.reserva.getAlojamiento();
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		mapa.getEngine().loadContent("<iframe src=\"https://maps.google.com/maps?q=" + Principal.modelo.reserva.getAlojamiento().getLongitud() + "," + Principal.modelo.reserva.getAlojamiento().getLatitud() + "&hl=es;z=14&amp;output=embed\" width=\"400\" height=\"200\" frameborder=\"0\" style=\"border:0\" scrolling=\"no\"></iframe>", "text/html");
//		img.getEngine().loadContent("<img src=\"" + alojamiento.getImgurl() + "\" width=\"450\" height=\"350\" frameborder=\"0\" style=\"border:0\"></img>", "text/html");
		img.setImage(new Image (alojamiento.getImgurl()));
	}
	
	 @FXML
	 void atras(ActionEvent event) {
		 Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	 }
	 
	 @FXML
	 void reservar(ActionEvent event) {
		 Principal.aplicacion.CambiarScene("Pasos.fxml");
	 }
	
}