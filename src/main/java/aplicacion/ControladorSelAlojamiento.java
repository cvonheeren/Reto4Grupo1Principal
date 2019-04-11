package aplicacion;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
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
    void AutoBuscar(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER)
    	{
    		Buscar();
    	}
    }

    @FXML
    void BtnBuscarPulsado(MouseEvent event) {
    	Buscar();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> ciudades = Principal.modelo.gestorBBDD.cargarListaDestinos();
		ciudades.addAll(Principal.modelo.gestorBBDD.cargarListaAlojamientos());
		TextFields.bindAutoCompletion( textCiudad, ciudades );
	}
	
	
	void Buscar()
	{
		ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarListaAlojamientos(textCiudad.getText());
    	
    	// añade el grid al anchorpane 'contenedor'
    	GridPane grid = new GridPane();
    	contenedor.getChildren().setAll(grid);
    	
    	// hace que el anchorpane rellene todo el espacio disponible en el padre
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
    	
    	// hace que la columna del grid ocupe todo el espacio disponible en el padre
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	grid.setGridLinesVisible(true);
    	for(int i=0; i<alojamientos.size(); i++) {
    		Alojamiento alojamiento = alojamientos.get(i);
    		AnchorPane anchorPane = new AnchorPane();
    		
    		
    		
    		
    		
    		 anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<Event>(){

				@Override
				public void handle(Event event) {
					System.out.println(alojamiento.getNombre());
					
				}
    		 });
    		
    		Text nombreHotel = new Text(alojamientos.get(i).getNombre());
    		nombreHotel.setLayoutX(170);
    		nombreHotel.setLayoutY(50);
    		nombreHotel.setFill(Color.BLACK);
    		nombreHotel.setStyle("-fx-font: 20 arial;");
    		
    		Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(170);
    		descripcion.setLayoutY(85);
    		
    		
    		
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHotel, descripcion);
        	
        	AnchorPane paneSuperior = new AnchorPane();
        	paneSuperior.getChildren().addAll(anchorPane);
        	JFXRippler rippler = new JFXRippler(anchorPane);
        	paneSuperior.getChildren().add(rippler);
    		paneSuperior.setStyle("-fx-background-color: #fff; -fx-border-color: #000; -fx-padding: 5px; -fx-border-insets: 5px; -fx-background-insets: 5px;");
    		// añade el anchorpane al grid
    		grid.add(paneSuperior, 0, i);
    	}
	}

}