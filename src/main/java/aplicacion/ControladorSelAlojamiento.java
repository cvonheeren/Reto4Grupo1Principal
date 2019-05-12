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
import javafx.util.Callback;
import modelo.Alojamiento;
import modelo.Habitacion;
import vista.CardAlojamiento;

public class ControladorSelAlojamiento implements Initializable {
	
	@FXML
    private AnchorPane contenedor;

    @FXML
    private Pane paneBusqueda, paneFiltros;

    @FXML
    private JFXTextField textCiudad;
    
    @FXML
    private JFXDatePicker fechaEntrada, fechaSalida;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private Label lblSaludo;

    @FXML
    private Hyperlink lblSesion;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	JFXDepthManager.setDepth(paneFiltros, 2);
    	JFXDepthManager.setDepth(paneBusqueda, 1);

		fechaEntrada.setValue(LocalDate.now());
		fechaSalida.setValue(LocalDate.now().plusDays(1));
		
		fechaEntrada = deshabilitarFechas(fechaEntrada, LocalDate.now());
		fechaSalida = deshabilitarFechas(fechaSalida, LocalDate.now().plusDays(1));
		
		Text busqueda = new Text("No ha realizado ninguna busqueda aun.");
		busqueda.setLayoutX(245);
		busqueda.setLayoutY(70);
		busqueda.setFont(new Font("arial",16));
		contenedor.getChildren().setAll(busqueda);
		
    	comprobarSesionIniciada();
		cargarAutocompletar();
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
    		ejecutarBusqueda();
    	}
    }

    @FXML
    void BtnBuscarPulsado(MouseEvent event) {
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
    public void cargarAutocompletar() {
    	ArrayList<String> autocompletar = Principal.modelo.gestorBBDD.cargarNombresDestinos();
    	autocompletar.addAll(Principal.modelo.gestorBBDD.cargarNombresAlojamientos());
		TextFields.bindAutoCompletion( textCiudad, autocompletar );
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
			Principal.aplicacion.mostrarMensaje(contenedor, "Debe introducir algun valor en el campo de busqueda");
			return;
		}
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
    
		ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarAlojamientos(textCiudad.getText());
    	GridPane grid = crearGrid();
        
    	for(int i = 0; i<alojamientos.size(); i++) {
    		
    		Alojamiento alojamiento = alojamientos.get(i);
    		ArrayList<Habitacion> habitaciones = buscarHabDisponibles(alojamiento);
    		
    		if (habitaciones.size() > 0) {
    			// guarda las habitaciones en el alojamiento
        		alojamiento.setHabitaciones(buscarHabDisponibles(alojamiento));
        		
        		// crea la tarjeta con la informacion del alojamiento
        		CardAlojamiento card = new CardAlojamiento(alojamiento);
        		
        		// a�ade listener a la tarjeta
        		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
        			@Override
        			public void handle(Event event) {
        				Principal.modelo.reserva.setAlojamiento(alojamiento);
        				Principal.aplicacion.CambiarScene("PaneInfo.fxml");
        			}
        		});
        		
        		// a�ade el efecto rippler a la tarjeta
            	AnchorPane paneSuperior = new AnchorPane();
            	JFXRippler rippler = new JFXRippler(card);
        		JFXDepthManager.setDepth(card, 1);
        		paneSuperior.getChildren().addAll(card);
            	paneSuperior.getChildren().add(rippler);
            	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));  
        		
            	// a�ade la tarjeta al grid
    			grid.add(card, 0, i); 
    		}	

    	}
	}
	
	/**
     * Crea y a�ade el grid al anchorpane 'contenedor', creado por defecto
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
     */
    public void comprobarSesionIniciada() {
		if(Principal.modelo.cliente != null) {
			lblSaludo.setText("Hola, " + Principal.modelo.cliente.getDni());
			lblSesion.setText("Cerrar Sesion");
		} else {
			lblSaludo.setText("Hola, Anonimo");
			lblSesion.setText("Identifiquese");
		}
	}

}