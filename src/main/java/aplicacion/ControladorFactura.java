package aplicacion;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

public class ControladorFactura {

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
    private Label precio;

    @FXML
    private JFXButton siguiente;

    @FXML
    private JFXButton atras;

    @FXML
    private WebView mapa;

    @FXML
    void atras(MouseEvent event) {

    }

    @FXML
    void siguiente(MouseEvent event) {

    }

}
