package aplicacion;

import core.Principal;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import modelo.Alojamiento;

public class ControladorInformacionAloj {

	private WebView img;
	private Text nombAloj;
	private TextArea descAloj;
	private AnchorPane paneInfo;
	private WebView mapa;





	public ControladorInformacionAloj(WebView img, Text nombAloj, TextArea descAloj, AnchorPane paneInfo, WebView mapa) {
		super();
		this.img = img;
		this.nombAloj = nombAloj;
		this.descAloj = descAloj;
		this.paneInfo = paneInfo;
		this.mapa = mapa;
	}


	void cargarInfoAloj(Alojamiento alojamiento) {
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		mapa.getEngine().loadContent("<iframe src=\"https://maps.google.com/maps?q=" + Principal.modelo.reserva.getAlojamiento().getLongitud() + "," + Principal.modelo.reserva.getAlojamiento().getLatitud() + "&hl=es;z=14&amp;output=embed\" width=\"400\" height=\"200\" frameborder=\"0\" style=\"border:0\" scrolling=\"no\"></iframe>", "text/html");
		img.getEngine().loadContent("<img src=\"" + alojamiento.getImgurl() + "\" width=\"450\" height=\"350\" frameborder=\"0\" style=\"border:0\"></img>", "text/html");
	}
	
}
