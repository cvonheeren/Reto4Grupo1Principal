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

public class ControladorPasos implements Initializable {

	int panelSeleccionado = 0;
	
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
    private Tab tabLogin;

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
    private WebView mapa;
    
    @FXML
    void cancelar(ActionEvent event) {
    	Principal.IniciarPrograma();
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
    		switch(tabPane.getSelectionModel().getSelectedIndex())
    		{
    		case 1:
    			sigTab = comprobarHabitacionSeleccionada();
    		case 2:
    			if(!(Principal.modelo.cliente==null))
    			{
    				panelSeleccionado=4;
    				tabPane.getSelectionModel().select(panelSeleccionado);
    				sigTab=false;
    			}
    			break;
    		case 3:
    			sigTab=controladorLogin.logear();
    			break;
    		
    		case 5:
    			Principal.IniciarPrograma();
    			break;
    		}
    		
    		if(sigTab)
    		{
    			panelSeleccionado++;
            	tabPane.getSelectionModel().select(panelSeleccionado);
    		}
    		
    		
    	}
    }
    
    @FXML
    void registrarse(MouseEvent event) {
    	controladorRegistro.registrarse();
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
		snackbar = new JFXSnackbar(anchorPaneBase);
		snackbar.getStylesheets().setAll("reto4.css");
		controladorInformacionAloj = new ControladorInformacionAloj(img, nombAloj, descAloj, paneInfo, mapa);
		controladorInformacionAloj.cargarInfoAloj(Principal.modelo.reserva.getAlojamiento());
		
		controladorSelHabitacion = new ControladorSelHabitacion(paneHabitacion);
		controladorSelHabitacion.cargarHabitaciones(Principal.modelo.reserva.getAlojamiento());
		
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
