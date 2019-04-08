package aplicacion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import core.Principal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import modelo.Alojamiento;

public class ListenerSeleccionAlojamiento {

    @FXML
    private JFXComboBox<Alojamiento> comboListaAlojamientos;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnSiguiente;

    @FXML
    private JFXComboBox<Alojamiento> comboBoxDestinos;
    
    @FXML
    void Cancelar(MouseEvent event) {
    	System.out.println("Btn cancelar");
    	Principal.aplicacion.CambiarScene("Bienvenida.fxml");
    }
    
    
}


