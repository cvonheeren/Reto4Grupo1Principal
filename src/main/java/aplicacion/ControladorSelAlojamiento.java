package aplicacion;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


import org.controlsfx.control.RangeSlider;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modelo.Alojamiento;
import modelo.Habitacion;
import modelo.Servicio;
import vista.CardAlojamiento;

public class ControladorSelAlojamiento implements Initializable {
	
    @FXML
    private AnchorPane paneBase, contenedor;
    
    @FXML
    private RangeSlider filtroEstrellas;

    @FXML
    private Pane paneBusqueda, paneFiltros, paneOpciones;

    @FXML
    private JFXTextField textCiudad;
    
    @FXML
    private JFXDatePicker fechaEntrada, fechaSalida;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private Label lblSaludo;
    
    @FXML
    private ScrollPane scrolpane;

    @FXML
    private Hyperlink lblSesion;
    
    @FXML
    private Text busqueda;
   
    @FXML
    private GridPane gridServicios;
    
    @FXML
    private JFXComboBox<Label> comboBoxOrdenBusqueda;
    
    @FXML
    private JFXCheckBox chkBoxHotel, chkBoxApartamento, chkBoxCasa;
   
    @FXML
    private JFXSlider sliderCantidad;
    
    @FXML
    private ImageView flecha;
    
    private boolean activarAnimacionCards = true;
    private ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
    private ArrayList<Servicio> serviciosSeleccionados = new ArrayList<Servicio>();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	JFXDepthManager.setDepth(paneFiltros, 2);
    	JFXDepthManager.setDepth(paneBusqueda, 1);
    	JFXDepthManager.setDepth(paneOpciones, 2);

		fechaEntrada.setValue(LocalDate.now());
		fechaSalida.setValue(LocalDate.now().plusDays(1));
		
		fechaEntrada = deshabilitarFechas(fechaEntrada, LocalDate.now());
		fechaSalida = deshabilitarFechas(fechaSalida, LocalDate.now().plusDays(1));
		
		RellenarFiltros();
		InizializarAnimaciones();
		
    	comprobarSesionIniciada();
		cargarAutocompletar();
		
		try {
			if (Principal.aplicacion.textoBusqueda != "") {
				textCiudad.setText(Principal.aplicacion.textoBusqueda);
				fechaEntrada.setValue(Principal.modelo.reserva.getFechaEntrada().toLocalDate());
				fechaSalida.setValue(Principal.modelo.reserva.getFechaSalida().toLocalDate());
				cargarAlojamientos();
			}
		} catch(Exception e) {
			
		}
	} 
    
    @FXML
    void mostrarPaneOpciones(MouseEvent event) {
    	TranslateTransition transicion = new TranslateTransition();
		transicion.setDuration(Duration.millis(200));
		transicion.setToY(0);
		transicion.setNode(paneOpciones);
		transicion.play();
    }

    @FXML
    void ocultarPaneOpciones(MouseEvent event) {
		TranslateTransition transicion = new TranslateTransition();
		transicion.setDuration(Duration.millis(200));
		transicion.setToY(-55);
		transicion.setNode(paneOpciones);
		transicion.play();
    }  

	@FXML
    void filtrarME(MouseEvent event) {
		Principal.modelo.gestorBBDD.borrarUltimaBusqueda();
		activarAnimacionCards = true;
		ejecutarBusqueda();
    }
	
	@FXML
    void filtrarAE(ActionEvent event) {
		Principal.modelo.gestorBBDD.borrarUltimaBusqueda();
		activarAnimacionCards = true;
		ejecutarBusqueda();
    }
    
    @FXML
    void seleccion(ActionEvent event) {
    	fechaSalida = deshabilitarFechas(fechaSalida, fechaEntrada.getValue().plusDays(1));
		fechaSalida.setValue(fechaEntrada.getValue().plusDays(1));
		fechaSalida.show();
    }
    
    @FXML
    void AutoBuscar(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		Principal.modelo.gestorBBDD.borrarUltimaBusqueda();
    		activarAnimacionCards = true;
    		ejecutarBusqueda();
    	}
    }

    @FXML
    void BtnBuscarPulsado(MouseEvent event) {
    	Principal.modelo.gestorBBDD.borrarUltimaBusqueda();
    	activarAnimacionCards = true;
    	ejecutarBusqueda();
    }
    
    @FXML
    void IniciarCerrar(ActionEvent event) {
    	Principal.aplicacion.controladorSelAlojamiento = this;
    	if(Principal.modelo.cliente == null) {
    		Principal.aplicacion.CambiarScene("LoginRegistro.fxml");
			Principal.aplicacion.controladorLoginRegistro.setPantallaAnterior("SeleccionAlojamiento.fxml");
		} else {
			Principal.modelo.cliente = null;
			comprobarSesionIniciada();
		}
    }
    
    /**
     * 
     */
    private void InizializarAnimaciones() {
		TranslateTransition transicion = new TranslateTransition();
		transicion.setToY(-55);
		transicion.setNode(paneOpciones);
		transicion.play();
		
	}
   
	/**
	 * 
	 */
    private void RellenarFiltros() {
    	filtroEstrellas.setLowValue(1f);
		filtroEstrellas.setHighValue(5f);
		
		Label lblPopulares = new Label("Mas populares");
    	lblPopulares.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.STAR));
    	Label lblPrecioBajo = new Label("Precio mas bajo");
    	lblPrecioBajo.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.MONEY));
    	Label lblPrecioAlto = new Label("Precio mas alto");
    	lblPrecioAlto.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.DIAMOND));
    	
    	comboBoxOrdenBusqueda.getItems().add(lblPopulares);
    	comboBoxOrdenBusqueda.getItems().add(lblPrecioBajo);
    	comboBoxOrdenBusqueda.getItems().add(lblPrecioAlto);
    	comboBoxOrdenBusqueda.getSelectionModel().select(0);
    	
    	ArrayList<Servicio> servicios = Principal.modelo.gestorBBDD.obtenerTodosServicios();
    	
    	for(int i=0;i<servicios.size();i++)
    	{
    		Servicio servicio = servicios.get(i);
    		JFXCheckBox serv = new JFXCheckBox(servicio.getNombre());
    		serv.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.valueOf(servicio.getIcon())));
    		serv.setPrefHeight(30);
    		serv.setOnAction(new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event) {
                     if(serv.isSelected()) {
                    	 serviciosSeleccionados.add(servicio);
                    	 Principal.modelo.gestorBBDD.borrarUltimaBusqueda();
                    	 activarAnimacionCards = true;
                    	 ejecutarBusqueda();
                     } else {
                    	 serviciosSeleccionados.remove(servicio);
                    	 Principal.modelo.gestorBBDD.borrarUltimaBusqueda();
                    	 activarAnimacionCards = true;
                    	 ejecutarBusqueda();
                     }
                 }
            });
    		gridServicios.addRow(i, serv);
    	}

	} 
    
    /**
     * Autocompleta lo que se escribe según lo que hay en las BBDD
     */
    public void cargarAutocompletar() {
    	ArrayList<String> autocompletar = Principal.modelo.gestorBBDD.cargarNombresDestinos();
    	autocompletar.addAll(Principal.modelo.gestorBBDD.cargarNombresAlojamientos());
		TextFields.bindAutoCompletion( textCiudad, autocompletar );
    }
    
    /**
     * Deshabilitar dias anteriores a la fecha que se pasa por parametro
     * @param datePicker El objeto propio de FXML para calendarios
     * @param fecha La fecha de hoy
     * @return Un DatePicker con las restricciones que queremos
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
     * Hace la busqueda de los alojamientos según lo que se introduzca
     */
    public void ejecutarBusqueda() {
    	if (textCiudad.getText().equals("")) {
			Principal.aplicacion.mostrarMensaje(paneBase, "Debe introducir algún valor en el campo de búsqueda");
			return;
		}
    	if(activarAnimacionCards)
    	{
    		scrolpane.setVvalue(0);
    	}
    	alojamientos = new ArrayList<Alojamiento>();
    	contenedor.getChildren().remove(busqueda);
		Date fechaEntradaDate = Date.valueOf(this.fechaEntrada.getValue());
		Date fechaSalidaDate = Date.valueOf(this.fechaSalida.getValue());
		Principal.modelo.reserva.setFechaEntrada(fechaEntradaDate);
		Principal.modelo.reserva.setFechaSalida(fechaSalidaDate);
		cargarAlojamientos();
    }
	
    /**
     * Carga y muestra los alojamientos correspondientes a la busqueda
     */
	public void cargarAlojamientos() {
		char tipoOrden = 0;
		boolean ordenAscendente = false;
		switch(comboBoxOrdenBusqueda.getSelectionModel().getSelectedIndex()) {
			case 0:
				tipoOrden='P'; ordenAscendente=false;
				break;
			case 1:
				tipoOrden='D'; ordenAscendente=true;
				break;
			case 2:
				tipoOrden='D'; ordenAscendente=false;
				break;
		}
		
		if(alojamientos.isEmpty()) {
			alojamientos = Principal.modelo.gestorBBDD.realizarBusquedaAlojamientos(textCiudad.getText(), (int) filtroEstrellas.getLowValue(), (int) filtroEstrellas.getHighValue(), TiposAlojamientoSeleccionados(), tipoOrden, ordenAscendente, (int) sliderCantidad.getValue(), ServiciosSeleccionados());
		}
		
		Principal.aplicacion.busquedaAlojamientos = alojamientos;
		Principal.aplicacion.textoBusqueda = textCiudad.getText().concat("");
    	GridPane grid = crearGrid();
    	
        int i = 0;
    	for(i = 0; i<alojamientos.size(); i++) {
    		
    		Alojamiento alojamiento = alojamientos.get(i);
    		ArrayList<Habitacion> habitaciones = buscarHabDisponibles(alojamiento);
    		
    		if (habitaciones.size() > 0) {
    			// guarda las habitaciones en el alojamiento
        		alojamiento.setHabitaciones(buscarHabDisponibles(alojamiento));
        		
        		// crea la tarjeta con la informacion del alojamiento
        		CardAlojamiento card = new CardAlojamiento(alojamiento, (float)(i)/16 + 1F, activarAnimacionCards);
        		
        		// añade listener a la tarjeta
        		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
        			@Override
        			public void handle(Event event) {
        				Principal.modelo.reserva.setAlojamiento(alojamiento);
        				Principal.aplicacion.CambiarScene("PaneInfo.fxml");
        			}
        		});
        		
        		JFXDepthManager.setDepth(card, 1);
        		
            	// añade la tarjeta al grid
    			grid.add(card, 0, i); 
    		}	

    	}
    	
    	Pane paneMostrarMas = new Pane();
    	Hyperlink btnMostrarMas = new Hyperlink("Mostrar mas...");
    	btnMostrarMas.setFont(new javafx.scene.text.Font("System", 30));
    	btnMostrarMas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				Principal.modelo.gestorBBDD.mostrarMasAlojamientos((int) sliderCantidad.getValue());
				activarAnimacionCards = false;
				cargarAlojamientos();
			}
		});
    	btnMostrarMas.setLayoutX(320);
    	paneMostrarMas.getChildren().add(btnMostrarMas);
    	
    	grid.add(paneMostrarMas, 0, i);
    	
	}
	
	/**
	 * Devuelve los tipos de alojamientos que hay en una búsqueda
	 * @return
	 */
	private String[] TiposAlojamientoSeleccionados() {
		String[] tiposAloj = new String[3];
		if(chkBoxHotel.isSelected())
			tiposAloj[0]="H";
		if(chkBoxApartamento.isSelected())
			tiposAloj[1]="A";
		if(chkBoxCasa.isSelected())
			tiposAloj[2]="C";
		return tiposAloj;
	}
	
	/**
	 * 
	 * @return
	 */
	private int[] ServiciosSeleccionados() {
		int[] servSeleccionadosString = new int[serviciosSeleccionados.size()];
		for(int i=0;i<serviciosSeleccionados.size();i++) {
			servSeleccionadosString[i]=serviciosSeleccionados.get(i).getCodServicio();
		}
		return servSeleccionadosString;
	}
	
	/**
     * Crea y añade el grid al anchorpane 'contenedor', creado por defecto
     * @return
     */
    public GridPane crearGrid() {
    	GridPane grid = new GridPane();
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	grid.setStyle("-fx-background-color: transparent;");
    	contenedor.getChildren().setAll(grid);
    	return grid;
    }
    
    /**
     * Devuelve un arraylist de la habitaciones disponibles en un alojamiento
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
     * Comprueba si la sesión está iniciada
     */
    public void comprobarSesionIniciada() {
		if(Principal.modelo.cliente != null) {
			lblSaludo.setText("Hola, " + Principal.modelo.cliente.getUser());
			lblSesion.setText("Cerrar Sesion");
		} else {
			lblSaludo.setText("Hola, Anónimo");
			lblSesion.setText("Identifíquese");
		}
	}

}