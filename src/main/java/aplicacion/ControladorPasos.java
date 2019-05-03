package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
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
import javafx.scene.control.Tab;
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
import modelo.Hotel;

public class ControladorPasos implements Initializable {
	
	private ControladorLogin controladorLogin;
	private ControladorInformacionAloj controladorInformacionAloj;
	private ControladorSelHabitacion controladorSelHabitacion;
	private ControladorRegistro controladorRegistro;
	private ControladorPago controladorPago;
	private JFXSnackbar snackbar;

    @FXML
    private AnchorPane anchorPaneBase;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab idTabInfo;

    @FXML
    private AnchorPane paneInfo;

    @FXML
    private WebView img;

    @FXML
    private Text nombAloj;

    @FXML
    private TextArea descAloj;

    @FXML
    private WebView mapa;

    @FXML
    private Tab idTabHab;

    @FXML
    private AnchorPane paneHabitacion;

    @FXML
    private Tab idTabServ;

    @FXML
    private AnchorPane paneServ;

    @FXML
    private Tab idTabLogin;

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private JFXButton btnReg;

    @FXML
    private JFXTextField textFieldDNIReg;

    @FXML
    private JFXTextField textFieldNombreReg;

    @FXML
    private JFXTextField textFieldApellidoReg;

    @FXML
    private JFXPasswordField contrasenaReg;

    @FXML
    private JFXPasswordField contrasenaRegRep;

    @FXML
    private JFXTextField textFieldMailReg;

    @FXML
    private JFXDatePicker fechaNacReg;

    @FXML
    private Label textoAviso;

    @FXML
    private JFXTextField textFieldDNI;

    @FXML
    private JFXPasswordField contrasena;

    @FXML
    private Label labelError;

    @FXML
    private Tab idTabPago;

    @FXML
    private AnchorPane panePago;

    @FXML
    private Label lblPrecio;

    @FXML
    private Label lblIntroducido;

    @FXML
    private Label lblRestante;

    @FXML
    private AnchorPane contenedorPago;

    @FXML
    private Tab idTabFin;

    @FXML
    private AnchorPane paneFinal;

    @FXML
    private JFXButton btnSiguiente;

    @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXButton btnInformacion;

    @FXML
    private JFXButton btnCancelar;
    
    @FXML
    void cancelar(ActionEvent event) {
    	Principal.IniciarPrograma();
    }



    @FXML
    void Informacion(ActionEvent event) {
    	tabPane.getSelectionModel().select(idTabInfo);	
    }

    @FXML
    void siguiente(ActionEvent event) {
    		switch(tabPane.getSelectionModel().getSelectedItem().getId())
    		{   			
    		case "idTabInfo":
    			if(Principal.modelo.reserva.getAlojamiento() instanceof Hotel)
    				tabPane.getSelectionModel().select(idTabHab);
    			else
    				tabPane.getSelectionModel().select(idTabServ);
    		break;	
    		case "idTabHab":
    			if(comprobarHabitacionSeleccionada())
    				tabPane.getSelectionModel().select(idTabServ);
    		break;
    		case "idTabServ":
    			if(!(Principal.modelo.cliente==null))
    				tabPane.getSelectionModel().select(idTabPago);
    			else
    				tabPane.getSelectionModel().select(idTabLogin);
    			break;
    		case "idTabLogin":
    			if(controladorLogin.logear())
    			{
    				tabPane.getTabs().remove(idTabLogin);
    				tabPane.getSelectionModel().select(idTabPago);
    			}
    			break;
    		case "idTabPago":
    			tabPane.getSelectionModel().select(idTabFin);
    			break;
    			
    		case "idTabFin":
    			Principal.IniciarPrograma();
    			break;
    		}
    }
    
    @FXML
    void registrarse(MouseEvent event) {
    	controladorRegistro.registrarse();
    }

    @FXML
    void volver(ActionEvent event) {
    	if(tabPane.getSelectionModel().getSelectedIndex()>0)
    	{
        	tabPane.getSelectionModel().select(tabPane.getSelectionModel().getSelectedIndex()-1);
    	}
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		snackbar = new JFXSnackbar(anchorPaneBase);
		snackbar.getStylesheets().setAll("reto4.css");
		controladorInformacionAloj = new ControladorInformacionAloj(img, nombAloj, descAloj, paneInfo, mapa);
		controladorInformacionAloj.cargarInfoAloj(Principal.modelo.reserva.getAlojamiento());
		
		if(Principal.modelo.reserva.getAlojamiento() instanceof Hotel)
		{
			controladorSelHabitacion = new ControladorSelHabitacion(paneHabitacion);
			controladorSelHabitacion.cargarHabitaciones(Principal.modelo.reserva.getAlojamiento());
		}
		else
		{
			tabPane.getTabs().remove(idTabHab);
		}
		if(Principal.modelo.cliente!=null)
		{
			tabPane.getTabs().remove(idTabLogin);
		}
		
		controladorLogin = new ControladorLogin(textoAviso, textFieldDNI, contrasena, this);
		controladorRegistro = new  ControladorRegistro(textFieldDNIReg, textFieldNombreReg, textFieldApellidoReg, textFieldMailReg, contrasenaReg, contrasenaRegRep, fechaNacReg, btnReg, this);
		
		controladorPago = new ControladorPago(contenedorPago, lblPrecio, lblIntroducido, lblRestante);
		
	}
	
	public void MostrarMensaje(String mensaje)
	{
		Text nodo = new Text(mensaje);
		snackbar.enqueue(new SnackbarEvent(nodo));
	}
	
	public boolean comprobarHabitacionSeleccionada() {
		boolean sigTab = true;
		ArrayList<Habitacion> habitacionesReservadas = Principal.modelo.reserva.getHabitacionesReservadas();
		if (habitacionesReservadas.size() == 0) {
			sigTab = false;
			JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar al menos una habitación", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean select = false;
			for (int i = 0; i < habitacionesReservadas.size(); i++) {
				if (habitacionesReservadas.get(i).getCantidad() > 0) {
					select = true;
				}
			}
			if (!select) {
				sigTab = false;
				JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar al menos una habitación", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return sigTab;
	}
	
}
