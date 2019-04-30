package aplicacion;

import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Hotel;

public class ControladorSelAlojamiento implements Initializable {
	

	@FXML
    private AnchorPane contenedor;

    @FXML
    private Pane paneBusqueda;

    @FXML
    private JFXTextField textCiudad;
    
    @FXML
    private JFXDatePicker fechaEntrada;
    
    @FXML
    private JFXDatePicker fechaSalida;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private Pane paneFiltros;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	JFXDepthManager.setDepth(paneFiltros, 2);
    	JFXDepthManager.setDepth(paneBusqueda, 1);
    	
    	ArrayList<String> ciudades = Principal.modelo.gestorBBDD.cargarListaDestinos();
		ciudades.addAll(Principal.modelo.gestorBBDD.cargarListaAlojamientos());
		TextFields.bindAutoCompletion( textCiudad, ciudades );

		fechaEntrada.setValue(LocalDate.now());
		fechaSalida.setValue(LocalDate.now().plusDays(1));
		
		// deshabilitar dias anteriores a hoy en ambos DatePickers
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override
		             public void updateItem(LocalDate date, boolean empty) {
		                 super.updateItem(date, empty);
		                 LocalDate today = LocalDate.now();
		                 setDisable(empty || date.compareTo(today) < 0 );
		             }
		         };
		     }
		 };
		 fechaEntrada.setDayCellFactory(dayCellFactory);
		 fechaSalida.setDayCellFactory(dayCellFactory);	
		 
		 Buscar();
	}
    
    @FXML
    void seleccion(ActionEvent event) {
   	 	// cambiar los dias deshabilitados en el datepicker 'fechaSalida'
		// cuando se selecciona una fecha en el datepicker 'fechaEntrada'
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override
		             public void updateItem(LocalDate date, boolean empty) {
		                 super.updateItem(date, empty);
		                 LocalDate today = fechaEntrada.getValue().plusDays(1);
		                 setDisable(empty || date.compareTo(today) < 0 );
		             }
		         };
		     }
		 };
		 fechaSalida.setDayCellFactory(dayCellFactory);
		 fechaSalida.setValue(fechaEntrada.getValue().plusDays(1));
    }
    
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
	
	void Buscar() {
		
		if (textCiudad.getText() == "") {
			JOptionPane.showMessageDialog(new JFrame(), "Debe introducir un destino", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (fechaEntrada.getValue() == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Debe introducir una fecha de entrada", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (fechaSalida.getValue() == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Debe introducir una fecha de salida", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
    	
    	// crea y añade el grid al anchorpane 'contenedor', creado por defecto
    	GridPane grid = new GridPane();
    	contenedor.getChildren().setAll(grid);
    	
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
        
        
		// cargamos la arraylist de alojamientos
		ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarListaAlojamientos(textCiudad.getText());
        
    	for(int i=0; i<alojamientos.size(); i++) {
    		
    		Alojamiento alojamiento = alojamientos.get(i);
    		
    		Date fechaEntradaDate = Date.valueOf(fechaEntrada.getValue());
    		Date fechaSalidaDate = Date.valueOf(fechaSalida.getValue());
    		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), fechaEntradaDate, fechaSalidaDate);
    		
    		String str = "";
    		str += "Habitaciones disponibles: \n";
    		for (Habitacion s : habitaciones) {
    			str += s.getNombre() + " - ";
    		    if (s.getCtaCamasSimples() > 0 ) {
    		    	str += "Cama Individual x " + s.getCtaCamasSimples() + " - ";
    		    }
    		    if (s.getCtaCamasMatrimonio() > 0 ) {
    		    	str += "Cama Matrimonio x " + s.getCtaCamasMatrimonio() + " - ";
    		    }
    		    if (s.getCtaCamasInfantil() > 0 ) {
    		    	str += "Cama Infantil x " + s.getCtaCamasInfantil() + " - ";
    		    }
    		    str += "Cantidad: " + s.getCantidad() + "\n";
    		}
    		
    		AnchorPane anchorPane = new AnchorPane();
    		
    		// añadimos la accion que se ejecutara al clickar el panel
    		anchorPane = añadirListenerSeleccion(anchorPane, alojamiento, fechaEntradaDate, fechaSalidaDate);
    		
    		int tamanoNombre;
    		// label - nombre del alojamiento
    		Text nombreHotel = new Text(alojamiento.getNombre());
    		nombreHotel.setFill(Paint.valueOf("#07c"));
    		nombreHotel.setFont(new Font("arial",25));
    		nombreHotel.setLayoutX(220);
    		nombreHotel.setLayoutY(35);
    		tamanoNombre=(int) ((int) nombreHotel.getBoundsInLocal().getMaxX()+220);
    		
    		//Icono del hotel
    		WebView imagen = new WebView();
    		imagen.setLayoutX(10);
    		imagen.setLayoutY(10);
    		imagen.setPrefSize(200, 200);
    		imagen.getEngine().loadContent("<img src=\"" + alojamiento.getImgurl() + "\" width=\"190\" height=\"180\" frameborder=\"0\" style=\"border:0\"></img>", "text/html");
    		anchorPane.getChildren().add(imagen);
    		
    		// Ubicacion del alojamiento
    		FontAwesomeIconView iconoUbicacion = new FontAwesomeIconView(FontAwesomeIcon.MAP_MARKER);
    		iconoUbicacion.setLayoutX(220);
    		iconoUbicacion.setLayoutY(60);
    		iconoUbicacion.setSize("20");
    		iconoUbicacion.setFill(Paint.valueOf("#555555"));
    		Text ubicacion = new Text(alojamiento.getUbicacion());
    		ubicacion.setLayoutX(240);
    		ubicacion.setLayoutY(60);
    		
    		//Estrellas del hotel (Si es un hotel claro)
	    	if(alojamiento instanceof Hotel)
	    	{
		    	int coordX=tamanoNombre+10;
	    		for(int e=0;e<((Hotel) alojamiento).getEstrellas();e++)
		    	{
		    		FontAwesomeIconView iconoEstrella = new FontAwesomeIconView(FontAwesomeIcon.STAR);
		    		iconoEstrella.setLayoutX(coordX);
			    	iconoEstrella.setLayoutY(35);
			    	iconoEstrella.setSize("15");
			    	iconoEstrella.setFill(Paint.valueOf("#feba02"));
			    	anchorPane.getChildren().add(iconoEstrella);
			    	coordX=coordX+15;
		    	}
	    	}
    		
    		// precio
    		Text precio = new Text("desde\n" + alojamiento.getTarifaNormal() + "€");
    		precio.setLayoutX(675);
    		precio.setLayoutY(80);
    		precio.setStyle("-fx-font: 20 arial;");
    		precio.setFill(Paint.valueOf("#0ab21b"));
    		
    		// label - descripcion del alojamiento
    		Text descripcion = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
    		descripcion.setLayoutX(220);
    		descripcion.setLayoutY(85);
    		
    		// label - habitaciones disponibles
    		Text habDisponibles = new Text(str);
    		habDisponibles.setLayoutX(220);
    		habDisponibles.setLayoutY(115);
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHotel, descripcion, habDisponibles, ubicacion, iconoUbicacion, precio);
        	
        	AnchorPane paneSuperior = new AnchorPane();
        	paneSuperior.getChildren().addAll(anchorPane);
        	
        	JFXRippler rippler = new JFXRippler(anchorPane);
        	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));
        	paneSuperior.getChildren().add(rippler);
        	
    		anchorPane.setStyle("-fx-background-color: #fff; -fx-padding: 5px; -fx-border-insets: 5px; -fx-background-insets: 5px; -fx-border-radius:  1 1 1 1; -fx-background-radius: 5 5 5 5;");
    		JFXDepthManager.setDepth(anchorPane, 1);
    		anchorPane.setPrefWidth(contenedor.getWidth()-20);
    		anchorPane.setMaxWidth(contenedor.getWidth()-20);
    		// añade el anchorpane al grid
    		grid.add(paneSuperior, 0, i);
    	}
	}
	
	public AnchorPane añadirListenerSeleccion(AnchorPane anchorPane, Alojamiento alojamiento, Date fechaEntrada, Date fechaSalida) {
		anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {

				Principal.modelo.reserva.setAlojamiento(alojamiento);
				Principal.modelo.reserva.setFechaEntrada(fechaEntrada);
				Principal.modelo.reserva.setFechaSalida(fechaSalida);
				Principal.aplicacion.CambiarScene("Pasos.fxml");
			}
		});
		return anchorPane;
	}

}