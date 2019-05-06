package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class ControladorMapa implements Initializable {
	
    @FXML
    private WebView mapa;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mapa.getEngine().loadContent("<iframe src=\"https://maps.google.com/maps?q=" + Principal.modelo.reserva.getAlojamiento().getLongitud() + "," + Principal.modelo.reserva.getAlojamiento().getLatitud() + "&hl=es;z=14&amp;output=embed\" width=\"1130\" height=\"880\" frameborder=\"0\" style=\"border:0\" scrolling=\"no\"></iframe>", "text/html");
	}

}
