package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import modelo.Habitacion;

public class ControladorPago implements Initializable {



    @FXML
    private Pane paneBase;

    @FXML
    private Pane tabInfo;

    @FXML
    private Label cod;

    @FXML
    private Label tipo;

    @FXML
    private Label nombre;

    @FXML
    private Label fechaEntrada;

    @FXML
    private Label fechaSalida;

    @FXML
    private Label adultos;

    @FXML
    private Label ninos;

    @FXML
    private Label habitaciones;

    @FXML
    private Label precio1;

    @FXML
    private Pane tabPago;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private Label precio;

    @FXML
    private Label introducido;

    @FXML
    private Label restante;

    @FXML
    private JFXButton sigiuente;

    @FXML
    private JFXButton atras;
   
    @FXML
    private WebView mapa;
    
    private float[] monedasBilletes = {500, 200, 100, 50, 20, 10, 5, 2, 1, 0.50f, 0.20f, 0.10f, 0.05f, 0.02f, 0.01f };
    public JFXButton[] botonesMonedasBilletes = new JFXButton[15];
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	precio.setText(Float.toString(Principal.modelo.pago.getPrecioTotal()) + " €");
    	introducido.setText("0 €");
    	restante.setText(Float.toString(Principal.modelo.pago.getPrecioTotal()) + " €");
    	crearBotones();
    	
    	JFXTabPane tabPane = new JFXTabPane();
    	tabPane.setPrefSize(1150, 664);
    	Tab tab = new Tab();
    	tab.setText("tab Info");
    	tab.setContent(tabInfo);
    	tabPane.getTabs().add(tab);
    	Tab tab2 = new Tab();
    	tab2.setText("tab Pago");
    	tab2.setContent(tabPago);
    	tabPane.getTabs().add(tab2);
    	tabPane.getSelectionModel().select(1);
    	
    	paneBase.getChildren().add(tabPane);
    	
		mapa.getEngine().loadContent("<iframe src=\"https://maps.google.com/maps?q=" + Principal.modelo.alojamiento.getLongitud() + "," + Principal.modelo.alojamiento.getLatitud() + "&hl=es;z=14&amp;output=embed\" width=\"350\" height=\"250\" frameborder=\"0\" style=\"border:0\"></iframe>", "text/html");
		cod.setText(Integer.toString(Principal.modelo.alojamiento.getCodAlojamiento()));
		tipo.setText("Hotel");
		nombre.setText(Principal.modelo.alojamiento.getNombre());
		precio.setText(Principal.modelo.alojamiento.getTarifaNormal() + "€");
		fechaEntrada.setText(Principal.modelo.fechaEntrada.toLocalDate().toString());
		fechaSalida.setText(Principal.modelo.fechaSalida.toLocalDate().toString());
		String str = "";
		for (Habitacion h: Principal.modelo.habitacionesReservadas) {
		    str += h.getNombre() + " x " + h.getCantidad() + "\n";
		}
		habitaciones.setText(str);
		precio.setText(Float.toString(Principal.modelo.pago.getPrecioTotal()));
    	
    	
    	
    }

    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("InfoReserva.fxml");
    }

    @FXML
    void siguiente(MouseEvent event) {
    	comprobarTodoIntroducido();
    	Principal.modelo.gestorBBDD.insertarReserva(Principal.modelo.alojamiento, Principal.modelo.cliente, Principal.modelo.pago, Principal.modelo.fechaEntrada, Principal.modelo.fechaSalida);
    }
    
    public void crearBotones() {
    	AnchorPane anchorPane = new AnchorPane();
    	int x = 400;
    	int y = 200;
    	for (int i = 0; i < monedasBilletes.length; i++ ) {
			if (monedasBilletes[i] > 0.5) {
				botonesMonedasBilletes[i] = new JFXButton((int)monedasBilletes[i] + " \u20AC");
			} else {
				botonesMonedasBilletes[i] = new JFXButton(monedasBilletes[i] + " \u20AC");
			}
			botonesMonedasBilletes[i].setId(Float.toString(monedasBilletes[i]));
			if (i == 3 || i == 7 || i == 11) {
				y += 50;
				x = 400;
			}
			x += 70;
			botonesMonedasBilletes[i].setLayoutX(x);
			botonesMonedasBilletes[i].setLayoutY(y);
			botonesMonedasBilletes[i].setPrefSize(60, 40);
			botonesMonedasBilletes[i].setStyle("-fx-background-color: #f57c00; -fx-text-fill: white;");
			botonesMonedasBilletes[i] = añadirListenerSeleccion(botonesMonedasBilletes[i], Float.toString(monedasBilletes[i]));
			anchorPane.getChildren().addAll(botonesMonedasBilletes[i]);
		}
    	contenedor.getChildren().setAll(anchorPane);
    }
    
    public JFXButton añadirListenerSeleccion(JFXButton btn, String num) {
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				float importe = Float.parseFloat(num);
				sumarDinero(importe);
			}
		});
		return btn;
	}
    
    /**
	 * Suma el dinero introducido. Se utiliza en los botones de monedas y billetes
	 * @param importe Valor a sumar
	 */
	public void sumarDinero(float importe) {
		String dineroIntroducido = Float.toString(Principal.modelo.pago.sumarDinero(importe));
		String dineroRestante =  Float.toString(Principal.modelo.pago.calcularDineroRestante());
		introducido.setText(dineroIntroducido + " €");
		restante.setText(dineroRestante + " €");
		//comprobarTodoIntroducido();
	}
	
	/**
	 * Comprueba si se ha introducido todo el dinero necesario para pagar
	 * y habilita y deshabilita los botones del panel segun es necesario
	 */
	public void comprobarTodoIntroducido() {
		if (Principal.modelo.pago.calcularDineroRestante() == 0) {
    		Principal.aplicacion.CambiarScene("Factura.fxml");	
    	} else {
    		JOptionPane.showMessageDialog(new JFrame(), "Aun no ha introducido todo el dinero", "Error",JOptionPane.ERROR_MESSAGE);
    	}
	}

}
