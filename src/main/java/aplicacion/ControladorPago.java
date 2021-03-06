package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

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
import modelo.Modelo;

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
    
    /**
     * Es un metodo que devuelve el modelo
     * @return
     */
	private Modelo modelo() {return Principal.modelo;}
	
	/**
	 * Es un metodo que devuelve la aplicacion
	 * @return
	 */
	private Aplicacion aplicacion() {return Principal.aplicacion;}
    
	/**
	 * Guarda un array de monedas y billetes
	 */
    private float[] monedasBilletes = { 500, 200, 100, 50, 20, 10, 5, 2, 1, 0.50f, 0.20f, 0.10f, 0.05f, 0.02f, 0.01f };
    
    /**
     * Crea array billetes
     */
    public JFXButton[] botonesMonedasBilletes = new JFXButton[15];	
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	aplicacion().controladorPago=this;
    	precio.setText(Float.toString(modelo().gestorDinero.getPrecioConDescuento()) + " €");
    	actulizarTarifa();
    	crearBotones();
    }
    
    @FXML
    void validarCodPromo(ActionEvent event) {
    	float descuentoPorcenajeFloat = modelo().gestorBBDD.validarCodPromo(textFieldCodPromo.getText(), modelo().cliente.getUser(), modelo().reserva.getAlojamiento().getCodAlojamiento());
    	if(descuentoPorcenajeFloat > 0) {
    		float descuento = modelo().gestorDinero.getPrecio() * descuentoPorcenajeFloat;
        	modelo().gestorDinero.setDescuento(descuento);
        	int descuentoPorcentajeint = (int)(descuentoPorcenajeFloat*100);
    		textFieldCodPromo.setDisable(true);
    		textFieldCodPromo.setText("Codigo promocional valido");
    		btnValidarCodPromo.setDisable(true);
    		descuentoPorcentaje.setText("Descuento(" + descuentoPorcentajeint + "%)");
    		descuentoPrecio.setText(descuento + "€");
    		precioTotal.setText(modelo().gestorDinero.getPrecioConDescuento() + "€");
    		actulizarTarifa();
    	} else {
    		aplicacion().mostrarMensaje(aplicacion().controladorPasos.anchorPaneBase, "Codigo promocional no valido");
    	}
    }
    
    /**
     * Actualiza el precio
     */
    public void actulizarTarifa() {
    	introducido.setText("0 €");
    	restante.setText(Float.toString(modelo().gestorDinero.getPrecioConDescuento()) + " €");
    }
    
    /**
     * Crea los botones de la interfaz de las monedas y billetes disponibles
     */
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
    
    /**
     * 
     * @param btn
     * @param num
     * @return
     */
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
		if (modelo().gestorDinero.calcularDineroRestante() == 0) {
			aplicacion().mostrarMensaje(aplicacion().controladorPasos.anchorPaneBase, "Ya ha introducido todo el dinero");
		} else {
			String dineroIntroducido = Float.toString(modelo().gestorDinero.sumarDinero(importe));
			String dineroRestante =  Float.toString(modelo().gestorDinero.calcularDineroRestante());
			introducido.setText(dineroIntroducido + " €");
			restante.setText(dineroRestante + " €");
		}
	}
	
	/**
	 * Comprueba si se ha introducido todo el dinero necesario para pagar
	 * y habilita y deshabilita los botones del panel segun es necesario
	 */
	public void comprobarTodoIntroducido() {
		if (modelo().gestorDinero.calcularDineroRestante() == 0) {
    		aplicacion().CambiarScene("Factura.fxml");	
    	} else {
    		aplicacion().mostrarMensaje(aplicacion().controladorPasos.anchorPaneBase, "Aún no ha introducido todo el dinero");
    	}
	}

}
