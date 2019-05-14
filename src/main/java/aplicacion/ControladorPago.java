package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Reserva;

public class ControladorPago implements Initializable {

	@FXML
    private Label precio, introducido, restante;

    @FXML
    private JFXButton sigiuente, atras;
    
    @FXML
    private JFXTextField textFieldCodPromo;
    
    @FXML
    private JFXButton btnValidarCodPromo;
    
    @FXML
    private Label descuentoPorcentaje;

    @FXML
    private Label descuentoPrecio;
    
    @FXML
    private Label precioTotal;
    
    @FXML
    private AnchorPane contenedor;
    
    private float[] monedasBilletes = { 500, 200, 100, 50, 20, 10, 5, 2, 1, 0.50f, 0.20f, 0.10f, 0.05f, 0.02f, 0.01f };
    public JFXButton[] botonesMonedasBilletes = new JFXButton[15];	
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	Principal.aplicacion.controladorPago=this;
    	precio.setText(Float.toString(Principal.modelo.pago.getPrecioTotal()) + " €");
    	actulizarTarifa();
    	crearBotones();
    }
    
    @FXML
    void validarCodPromo(ActionEvent event) {
    	Reserva reserva = Principal.modelo.reserva;
    	float descuento = Principal.modelo.gestorBBDD.modificarBBDD.ValidarCodPromo(textFieldCodPromo.getText(), Principal.modelo.cliente.getUser());
    	int descuentoPorcentajeint = (int)(descuento*100);
    	float precioDescuento = reserva.getPrecio()*descuento;
    	if(descuento > 0) {
    		textFieldCodPromo.setDisable(true);
    		textFieldCodPromo.setText("Codigo promocional valido");
    		btnValidarCodPromo.setDisable(true);
    		descuentoPorcentaje.setText("Descuento(" + Integer.toString(descuentoPorcentajeint) + "%)");
    		descuentoPrecio.setText(Float.toString(precioDescuento) + "€");
    		precioTotal.setText(reserva.getPrecio() - precioDescuento + "€");
    		reserva.setPrecio(reserva.getPrecio() - precioDescuento);
    		Principal.modelo.pago.setDineroRestante(reserva.getPrecio());
    		Principal.modelo.pago.setPrecioTotal(reserva.getPrecio());
    		actulizarTarifa();
    	} else {
    		Principal.aplicacion.mostrarMensaje(Principal.aplicacion.controladorPasos.anchorPaneBase, "Codigo promocional no valido");
    	}
    }
    
    public void actulizarTarifa() {
    	introducido.setText("0 €");
    	restante.setText(Float.toString(Principal.modelo.pago.getPrecioTotal()) + " €");
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
				textFieldCodPromo.setDisable(true);
				btnValidarCodPromo.setDisable(true);
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
		if (Principal.modelo.pago.calcularDineroRestante() == 0) {
			Principal.aplicacion.mostrarMensaje(contenedor, "Ya ha introducido todo el dinero");
		} else {
			String dineroIntroducido = Float.toString(Principal.modelo.pago.sumarDinero(importe));
			String dineroRestante =  Float.toString(Principal.modelo.pago.calcularDineroRestante());
			introducido.setText(dineroIntroducido + " €");
			restante.setText(dineroRestante + " €");
		}
	}
	
	/**
	 * Comprueba si se ha introducido todo el dinero necesario para pagar
	 * y habilita y deshabilita los botones del panel segun es necesario
	 */
	public void comprobarTodoIntroducido() {
		if (Principal.modelo.pago.calcularDineroRestante() == 0) {
    		Principal.aplicacion.CambiarScene("Factura.fxml");	
    	} else {
    		Principal.aplicacion.mostrarMensaje(Principal.aplicacion.controladorPasos.anchorPaneBase, "Aun no ha introducido todo el dinero");
    	}
	}

}
