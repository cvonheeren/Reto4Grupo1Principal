package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    private AnchorPane tabLogin;

    @FXML
    private AnchorPane panePago;

    @FXML
    private AnchorPane paneFinal;

    @FXML
    private JFXButton btnSiguiente;

    @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXButton btnInformacion;

    @FXML
    void Informacion(ActionEvent event) {
    	tabPane.getSelectionModel().select(0);
    	panelSeleccionado=0;
    }

    @FXML
    void siguiente(ActionEvent event) {
    	if(panelSeleccionado<tabPane.getTabs().size())
    	{
    		panelSeleccionado++;
        	tabPane.getSelectionModel().select(panelSeleccionado);
    	}
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
		cargarInfoAloj(Principal.modelo.alojamiento);
		cargarHabitaciones(Principal.modelo.alojamiento);
		
	}
	
	
	void cargarInfoAloj(Alojamiento alojamiento) {
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		img.getEngine().loadContent("<img src=\"" + alojamiento.getImgurl() + "\" width=\"450\" height=\"350\" frameborder=\"0\" style=\"border:0\"></img>", "text/html");
		
	}

	void cargarHabitaciones(Alojamiento alojamiento) {
    	
    	// crea y añade el grid al anchorpane 'contenedor', creado por defecto
    	GridPane grid = new GridPane();
    	paneHabitacion.getChildren().setAll(grid);
    	
    	// hace que la columna 1 del grid ocupe todo el espacio disponible en el padre
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	grid.setStyle("-fx-background-color: transparent;");
    	
    	// hace que los anchorpanes rellenen todo el espacio disponible en el padre
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        
		// cargamos la arraylist de habitaciones
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), Principal.modelo.fechaEntrada, Principal.modelo.fechaSalida);
        
    	for(int i=0; i<habitaciones.size(); i++) {
    		
    		Habitacion habitacion = habitaciones.get(i);
    		AnchorPane anchorPane = new AnchorPane();
    		
    		String str = "";
		    if (habitacion.getCtaCamasSimples() > 0 ) {
		    	str += "Cama Individual x " + habitacion.getCtaCamasSimples() + "\n";
		    }
		    if (habitacion.getCtaCamasMatrimonio() > 0 ) {
		    	str += "Cama Matrimonio x " + habitacion.getCtaCamasMatrimonio() + "\n";
		    }
		    if (habitacion.getCtaCamasInfantil() > 0 ) {
		    	str += "Cama Infantil x " + habitacion.getCtaCamasInfantil();
		    }
    		
    		// label - nombre de la habitacion
    		Text nombreHab = new Text(habitacion.getNombre());
    		nombreHab.setLayoutX(170);
    		nombreHab.setLayoutY(30);
    		nombreHab.setFill(Paint.valueOf("#07c"));
    		nombreHab.setStyle("-fx-font: 25 arial;");
    		nombreHab.setLayoutX(170);
    		nombreHab.setLayoutY(35);
    		
    		FontAwesomeIconView iconoCama = new FontAwesomeIconView(FontAwesomeIcon.BED);
    		iconoCama.setLayoutX(180);
    		iconoCama.setLayoutY(60);
    		iconoCama.setSize("20");
    		iconoCama.setFill(Paint.valueOf("#555555"));		
    		
    		Text camas = new Text(str);
    		camas.setLayoutX(200);
    		camas.setLayoutY(60);
    		
    		// precio
    		Text precio = new Text("100€");
    		precio.setLayoutX(675);
    		precio.setLayoutY(80);
    		precio.setStyle("-fx-font: 20 arial;");
    		precio.setFill(Paint.valueOf("#0ab21b"));
    		
    		// label - descripcion del habitacion
    		Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(170);
    		descripcion.setLayoutY(85);
    		
    		// ChoiceBox - Cantidad de habitaciones
    		ArrayList<Integer> numHab = new ArrayList<Integer>();
    		for (int j = 0; j <= habitacion.getCantidad(); j++) {
    			numHab.add(j);
    		}
    		ChoiceBox<Integer> cb = new ChoiceBox<Integer>(FXCollections.observableArrayList(numHab));
    		cb.getSelectionModel().selectFirst();
    		cb.setLayoutX(675);
    		cb.setLayoutY(120);
    		cb.setOnAction((e) -> {
    			guardarHabitacion(habitacion, cb.getValue());
            });
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHab, descripcion, camas, iconoCama, precio, cb);
        	
        	AnchorPane paneSuperior = new AnchorPane();
        	paneSuperior.getChildren().addAll(anchorPane);
        	
        	JFXRippler rippler = new JFXRippler(anchorPane);
        	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));
        	paneSuperior.getChildren().add(rippler);
        	
    		anchorPane.setStyle("-fx-background-color: #fff; -fx-padding: 5px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-border-radius:  1 1 1 1; -fx-background-radius: 5 5 5 5;");
    		JFXDepthManager.setDepth(anchorPane, 1);
    		anchorPane.setPrefWidth(paneHabitacion.getWidth()-20);
    		anchorPane.setMaxWidth(paneHabitacion.getWidth()-20);
    		// añade el anchorpane al grid
    		grid.add(paneSuperior, 0, i);
    		
    	}
    	
    	
	}
	
	public void guardarHabitacion(Habitacion habitacion, int cantidad) {
		ArrayList<Habitacion> habitacionesReservadas = Principal.modelo.habitacionesReservadas;
		if (habitacionesReservadas.size() == 0) {
			Principal.modelo.habitacionesReservadas.add(habitacion);
			Principal.modelo.habitacionesReservadas.get(habitacionesReservadas.size()-1).setCantidad(cantidad);
			return;
		}
		for (int i = 0;i < habitacionesReservadas.size(); i++) {
			if (habitacionesReservadas.get(i).getNombre().equals(habitacion.getNombre())) {
				habitacionesReservadas.get(i).setCantidad(cantidad);
				return;
			}
		}
		Principal.modelo.habitacionesReservadas.add(habitacion);
		Principal.modelo.habitacionesReservadas.get(habitacionesReservadas.size()-1).setCantidad(cantidad);
	}

}
