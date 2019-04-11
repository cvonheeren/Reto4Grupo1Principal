package aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControladorPago implements Initializable {

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
    private JFXButton uno;

    @FXML
    private JFXButton dos;

    @FXML
    private JFXButton cinco;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }

    @FXML
    void atras(MouseEvent event) {
    	Principal.aplicacion.CambiarScene("InfoReserva.fxml");
    }

    @FXML
    void siguiente(MouseEvent event) {
    	// Principal.aplicacion.CambiarScene("Factura.fxml");
    }

}
