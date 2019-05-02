package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import modelo.Alojamiento;
import modelo.Habitacion;

public class ControladorPasos implements Initializable {

	int panelSeleccionado = 0;
	
	private ControladorLogin controladorLogin;
	private ControladorInformacionAloj controladorInformacionAloj;
	private ControladorSelHabitacion controladorSelHabitacion;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private AnchorPane paneInfo;

    @FXML
    private WebView img;

    @FXML
    private Text nombAloj;

    @FXML
    private TextArea descAloj;

    @FXML
    private AnchorPane paneHabitacion;

    @FXML
    private AnchorPane paneServ;

    @FXML
    private AnchorPane tabLogin;

    @FXML
    private Label textoAviso;

    @FXML
    private JFXTextField textFieldDNI;

    @FXML
    private JFXPasswordField contrasena;

    @FXML
    private Hyperlink linkRegistro;

    @FXML
    private AnchorPane panePago;

    @FXML
    private AnchorPane paneFinal;

    @FXML
    private JFXButton btnSiguiente;

    @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXButton btnInformacion;

    @FXML
    void registrar(ActionEvent event) {

    }

    @FXML
    void Informacion(ActionEvent event) {
    	tabPane.getSelectionModel().select(0);
    	panelSeleccionado=0;
    }

    @FXML
    void siguiente(ActionEvent event) {
    	if(panelSeleccionado<tabPane.getTabs().size())
    	{
    		boolean sigTab=true;
    		if(tabPane.getSelectionModel().getSelectedIndex()==3)
    		{
    			sigTab=controladorLogin.logear();
    		}
    		if(sigTab)
    		{
    			panelSeleccionado++;
            	tabPane.getSelectionModel().select(panelSeleccionado);
    		}
    		
    	}
    }

    @FXML
    void volver(ActionEvent event) {
    	if(panelSeleccionado>0)
    	{
    		panelSeleccionado--;
        	tabPane.getSelectionModel().select(panelSeleccionado);
    	}
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controladorInformacionAloj = new ControladorInformacionAloj(img, nombAloj, descAloj, paneInfo);
		controladorInformacionAloj.cargarInfoAloj(Principal.modelo.reserva.getAlojamiento());
		
		controladorSelHabitacion = new ControladorSelHabitacion(paneHabitacion);
		controladorSelHabitacion.cargarHabitaciones(Principal.modelo.reserva.getAlojamiento());
		
		controladorLogin = new ControladorLogin(textoAviso, textFieldDNI, contrasena, linkRegistro);
	}
	
}
