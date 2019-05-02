package aplicacion;

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





	public ControladorInformacionAloj(WebView img, Text nombAloj, TextArea descAloj, AnchorPane paneInfo) {
		super();
		this.img = img;
		this.nombAloj = nombAloj;
		this.descAloj = descAloj;
		this.paneInfo = paneInfo;
	}


	void cargarInfoAloj(Alojamiento alojamiento) {
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		img.getEngine().loadContent("<img src=\"" + alojamiento.getImgurl() + "\" width=\"450\" height=\"350\" frameborder=\"0\" style=\"border:0\"></img>", "text/html");
	}
	
}
