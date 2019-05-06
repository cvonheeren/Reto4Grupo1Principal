package aplicacion;

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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
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
    
    @FXML
    private Label lblSaludo;

    @FXML
    private Hyperlink lblSesion;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	if(Principal.modelo.cliente!=null)
    		SesionIniciada();
    	
    	JFXDepthManager.setDepth(paneFiltros, 2);
    	JFXDepthManager.setDepth(paneBusqueda, 1);

		fechaEntrada.setValue(LocalDate.now());
		fechaSalida.setValue(LocalDate.now().plusDays(1));
		
		fechaEntrada = deshabilitarFechas(fechaEntrada, LocalDate.now());
		fechaSalida = deshabilitarFechas(fechaSalida, LocalDate.now().plusDays(1));
		
		cargarAutocompletar();
		cargarAlojamientos();
	}
    
    @FXML
    void seleccion(ActionEvent event) {
   	 	// cambiar los dias deshabilitados en el datepicker 'fechaSalida'
		// cuando se selecciona una fecha en el datepicker 'fechaEntrada'
    	fechaSalida = deshabilitarFechas(fechaSalida, fechaEntrada.getValue().plusDays(1));
		fechaSalida.setValue(fechaEntrada.getValue().plusDays(1));
    }
    
    @FXML
    void AutoBuscar(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		ejecutarBusqueda();
    	}
    }

    @FXML
    void BtnBuscarPulsado(MouseEvent event) {
    	ejecutarBusqueda();
    }
    
    @FXML
    void IniciarCerrar(ActionEvent event) {
    	Principal.aplicacion.controladorSelAlojamiento=this;
    	if(Principal.modelo.cliente==null)
		{
			Principal.aplicacion.CargarSceneLogin();
		}
		else
		{
			Principal.iniciarPrograma();
		}
    }
    
    /**
     * 
     */
    public void cargarAutocompletar() {
    	ArrayList<String> ciudades = Principal.modelo.gestorBBDD.cargarListaDestinos();
		ciudades.addAll(Principal.modelo.gestorBBDD.cargarListaAlojamientos());
		TextFields.bindAutoCompletion( textCiudad, ciudades );
    }
    
    /**
     * Deshabilitar dias anteriores a la fecha que se pasa por parametro
     * @param datePicker
     * @param fecha
     * @return
     */
    public JFXDatePicker deshabilitarFechas(JFXDatePicker datePicker, LocalDate fecha) {
    	final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override
		             public void updateItem(LocalDate date, boolean empty) {
		                 super.updateItem(date, empty);
		                 setDisable(empty || date.compareTo(fecha) < 0 );
		             }
		         };
		     }
		 };
		 datePicker.setDayCellFactory(dayCellFactory);
		 return datePicker;
    }
    
    /**
     * 
     */
    public void ejecutarBusqueda() {
    	if (textCiudad.getText().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Debe introducir un destino", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
    	cargarAlojamientos();
    }
	
    /**
     * Carga y muestra los alojamientos correspondientes a la 
     */
	public void cargarAlojamientos() {
    
    	GridPane grid = crearGrid();
    	contenedor.getChildren().setAll(grid);
    	
    	// hace que los anchorpanes rellenen todo el espacio disponible en el padre
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
       
		ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarListaAlojamientos(textCiudad.getText());
        
    	for(int i=0; i<alojamientos.size(); i++) {
    		
    		Alojamiento alojamiento = alojamientos.get(i);
    		
    		AnchorPane anchorPane = new AnchorPane();
    		anchorPane = añadirListenerSeleccion(anchorPane, alojamiento);
    		
    		
    		// label - nombre del alojamiento
    		Text nombreHotel = crearNombre(alojamiento.getNombre());
    		
    		//Icono de ubicacion
    		WebView imagen = crearImagen(alojamiento.getImgurl());
    		
    		// Ubicacion del alojamiento
    		FontAwesomeIconView iconoUbicacion = crearIconoUbicacion();
    		
    		Text ubicacion = new Text(alojamiento.getUbicacion());
    		ubicacion.setLayoutX(240);
    		ubicacion.setLayoutY(60);
    		
    		Hyperlink mapa = new Hyperlink("Ver Mapa");
    		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX()+240+10);
    		mapa.setLayoutX(tamanoUbicacion);
    		mapa.setLayoutY(44);
    		mapa.setOnAction((e) -> {
    			verMapa(alojamiento);
            });
    		
    		//Estrellas del hotel (Si es un hotel claro)
    		int tamanoNombre = (int) ((int) nombreHotel.getBoundsInLocal().getMaxX()+220);
	    	if(alojamiento instanceof Hotel) {	
		    	int coordX=tamanoNombre+10;
	    		for(int e=0;e<((Hotel) alojamiento).getEstrellas();e++) {
		    		FontAwesomeIconView iconoEstrella = new FontAwesomeIconView(FontAwesomeIcon.STAR);
		    		iconoEstrella.setLayoutX(coordX);
			    	iconoEstrella.setLayoutY(35);
			    	iconoEstrella.setSize("15");
			    	iconoEstrella.setFill(Paint.valueOf("#feba02"));
			    	anchorPane.getChildren().add(iconoEstrella);
			    	coordX=coordX+15;
		    	}
	    		
	    		ArrayList<Habitacion> habitaciones = buscarHabDisponibles(alojamiento);
	    		alojamiento.setHabitaciones(habitaciones);
        		String str = mostrarHabitaciones(habitaciones);
        		
        		// label - habitaciones disponibles
        		Text habDisponibles = new Text(str);
        		habDisponibles.setLayoutX(220);
        		habDisponibles.setLayoutY(155);
        		habDisponibles.maxWidth(50);
        		anchorPane.getChildren().add(habDisponibles);
	    		
	    		
	    	} else if(alojamiento instanceof Apartamento) {
		    	int coordX = tamanoNombre + 10;
		    		FontAwesomeIconView iconoLlave = new FontAwesomeIconView(FontAwesomeIcon.KEY);
		    		iconoLlave.setLayoutX(coordX);
		    		iconoLlave.setLayoutY(35);
		    		iconoLlave.setSize("15");
		    		iconoLlave.setFill(Paint.valueOf("#555555"));
			    	anchorPane.getChildren().add(iconoLlave);
	    	} else if(alojamiento instanceof Casa) {
	    		int coordX=tamanoNombre+10;
	    		FontAwesomeIconView iconoCasa = new FontAwesomeIconView(FontAwesomeIcon.HOME);
	    		iconoCasa.setLayoutX(coordX);
	    		iconoCasa.setLayoutY(35);
	    		iconoCasa.setSize("15");
	    		iconoCasa.setFill(Paint.valueOf("#555555"));
		    	anchorPane.getChildren().add(iconoCasa);
	    	}
    		
    		// precio
    		Text precio = new Text("Desde\n" + alojamiento.getTarifaNormal() + "€");
    		precio.setLayoutX(675);
    		precio.setLayoutY(180);
    		precio.setStyle("-fx-font: 20 arial;");
    		precio.setFill(Paint.valueOf("#0ab21b"));
    		
    		// label - descripcion del alojamiento
    		Label descripcion = new Label(alojamiento.getDescripcion());
    		descripcion.setLayoutX(220);
    		descripcion.setLayoutY(85);
    		descripcion.setPrefWidth(500);
    		descripcion.setPrefHeight(50);
    		descripcion.setWrapText(true);
    		
    		// añade los componentes al anchorpane
        	anchorPane.getChildren().addAll(nombreHotel, descripcion, ubicacion, iconoUbicacion, precio, imagen, mapa);
        	
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
	
	 void verMapa(Alojamiento alojamiento) {
		Principal.modelo.reserva.setAlojamiento(alojamiento);
		Principal.aplicacion.verMapa("Mapa.fxml");
    }
	
	/**
     * Crea y añade el grid al anchorpane 'contenedor', creado por defecto
     * @return
     */
    public GridPane crearGrid() {
    	GridPane grid = new GridPane();
    	
    	// hace que la columna 1 del grid ocupe todo el espacio disponible en el padre
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	
    	// pone el color de fondo transparente
    	grid.setStyle("-fx-background-color: transparent;");
    	
    	return grid;
    }
    
    /**
     * 
     * @param nombre
     * @return
     */
    public Text crearNombre(String nombre) {
    	Text text = new Text(nombre);
		text.setFill(Paint.valueOf("#07c"));
		text.setFont(new Font("arial",25));
		text.setLayoutX(220);
		text.setLayoutY(35);
		return text;
    }
    
    /**
     * 
     * @param url
     * @return
     */
    public WebView crearImagen(String url) {
    	WebView imagen = new WebView();
		imagen.setLayoutX(10);
		imagen.setLayoutY(10);
		imagen.setPrefSize(200, 200);
		imagen.getEngine().loadContent("<img src=\"" + url + "\" width=\"190\" height=\"180\" frameborder=\"0\" style=\"border:0\"></img>", "text/html");
		return imagen;
    }
    
    /**
     * 
     * @return
     */
    public FontAwesomeIconView crearIconoUbicacion() {
    	FontAwesomeIconView iconoUbicacion = new FontAwesomeIconView(FontAwesomeIcon.MAP_MARKER);
		iconoUbicacion.setLayoutX(220);
		iconoUbicacion.setLayoutY(60);
		iconoUbicacion.setSize("20");
		iconoUbicacion.setFill(Paint.valueOf("#555555"));
		return iconoUbicacion;
    }
    
    /**
     * 
     * @param alojamiento
     * @return
     */
    public ArrayList<Habitacion> buscarHabDisponibles(Alojamiento alojamiento) {
    	Date fechaEntradaDate = Date.valueOf(fechaEntrada.getValue());
		Date fechaSalidaDate = Date.valueOf(fechaSalida.getValue());
		ArrayList<Habitacion> habitaciones = Principal.modelo.gestorBBDD.habitacionesDisponibles(alojamiento.getCodAlojamiento(), fechaEntradaDate, fechaSalidaDate);
		return habitaciones;
    }
    
    /**
     * 
     * @param habitaciones
     * @return
     */
    public String mostrarHabitaciones(ArrayList<Habitacion> habitaciones) {
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
		return str;
    }
	
	/**
	 * 
	 * @param anchorPane
	 * @param alojamiento
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public AnchorPane añadirListenerSeleccion(AnchorPane anchorPane, Alojamiento alojamiento) {
		anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				Date fechaEntradaDate = Date.valueOf(fechaEntrada.getValue());
				Date fechaSalidaDate = Date.valueOf(fechaSalida.getValue());
				Principal.modelo.reserva.setAlojamiento(alojamiento);
				Principal.modelo.reserva.setFechaEntrada(fechaEntradaDate);
				Principal.modelo.reserva.setFechaSalida(fechaSalidaDate);
				Principal.aplicacion.CambiarScene("PaneInfo.fxml");
			}
		});
		return anchorPane;
	}

	public void SesionIniciada() {
		lblSaludo.setText("Hola, " + Principal.modelo.cliente.getDni());
		lblSesion.setText("Cerrar Sesion");
	}

}