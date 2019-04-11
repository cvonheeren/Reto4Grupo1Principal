package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import modelo.Alojamiento;

public class ControladorSelAlojamiento implements Initializable {

    @FXML
    private JFXTextField textCiudad;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private AnchorPane contenedor;

    @FXML
    void buscar(MouseEvent event) {
    	
    	ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarListaAlojamientos(textCiudad.getText());
    	
    	// añade el grid al anchorpane 'contenedor'
    	GridPane grid = new GridPane();
    	contenedor.getChildren().addAll(grid);
    	
    	// hace que el anchorpane rellene todo el espacio disponible en el padre
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
    	
    	// hace que la columna del grid ocupe todo el espacio disponible en el padre
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	
    	for(int i=0; i<alojamientos.size(); i++) {
    		
    		AnchorPane anchorPane = new AnchorPane();
    		anchorPane.setStyle("-fx-background-color: #fff");
    		
    		Text nombreHotel = new Text(alojamientos.get(i).getNombre());
    		nombreHotel.setLayoutX(170);
    		nombreHotel.setLayoutY(50);
    		nombreHotel.setFill(Color.BLACK);
    		nombreHotel.setStyle("-fx-font: 20 arial;");
    		
    		Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(170);
    		descripcion.setLayoutY(85);
    		
    		Button button = new Button("Reservar");
    		button.setLayoutX(170);
    		button.setLayoutY(150);
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHotel, descripcion, button);
        	
        	// añade el anchorpane al grid
    		grid.add(anchorPane, 0, i);
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> ciudades = Principal.modelo.gestorBBDD.cargarListaDestinos();
		TextFields.bindAutoCompletion( textCiudad, ciudades );
	}

}