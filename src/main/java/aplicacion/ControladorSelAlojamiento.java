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
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Alojamiento;
import modelo.Habitacion;
import vista.CardAlojamiento;

public class ControladorSelAlojamiento implements Initializable {
	
    @FXML
    private AnchorPane paneBase;
    
    @FXML
    private RangeSlider filtroEstrellas;
	
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
    
    @FXML
    private Text busqueda;
    
    @FXML
    private JFXCheckBox chkBoxHotel;

    @FXML
    private JFXCheckBox chkBoxApartamento;

    @FXML
    private JFXCheckBox chkBoxCasa;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	JFXDepthManager.setDepth(paneFiltros, 2);
    	JFXDepthManager.setDepth(paneBusqueda, 1);

		fechaEntrada.setValue(LocalDate.now());
		fechaSalida.setValue(LocalDate.now().plusDays(1));
		
		fechaEntrada = deshabilitarFechas(fechaEntrada, LocalDate.now());
		fechaSalida = deshabilitarFechas(fechaSalida, LocalDate.now().plusDays(1));
		
		RellenarFiltros();
		
		
		
    	comprobarSesionIniciada();
		cargarAutocompletar();
		
		try{textCiudad.setText(Principal.aplicacion.textoBusqueda);
		if(Principal.aplicacion.textoBusqueda!="")
			cargarAlojamientos();
		}
		catch(Exception e) {}
	}
   

    private void RellenarFiltros() {
    	filtroEstrellas.setLowValue(1f);
		filtroEstrellas.setHighValue(5f);
		
		
	}


	@FXML
    void filtrarME(MouseEvent event) {
    	ejecutarBusqueda();
    }
	
	@FXML
    void filtrarAE(ActionEvent event) {
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
    	if (textCiudad.getText().equals("")) {
			Principal.aplicacion.mostrarMensaje(paneBase, "Debe introducir algun valor en el campo de busqueda");
			return;
		}
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
		ArrayList<Alojamiento> alojamientos = Principal.modelo.gestorBBDD.cargarAlojamientos(textCiudad.getText(), (int) filtroEstrellas.getLowValue(), (int) filtroEstrellas.getHighValue(), TiposAlojamientoSeleccionados());
		Principal.aplicacion.busquedaAlojamientos=alojamientos;
		Principal.aplicacion.textoBusqueda=textCiudad.getText().concat("");
    	GridPane grid = crearGrid();
        
    	for(int i = 0; i<alojamientos.size(); i++) {
    		
    		Alojamiento alojamiento = alojamientos.get(i);
    		ArrayList<Habitacion> habitaciones = buscarHabDisponibles(alojamiento);
    		
    		if (habitaciones.size() > 0) {
    			// guarda las habitaciones en el alojamiento
        		alojamiento.setHabitaciones(buscarHabDisponibles(alojamiento));
        		
        		// crea la tarjeta con la informacion del alojamiento
        		CardAlojamiento card = new CardAlojamiento(alojamiento);
        		
        		// añade listener a la tarjeta
        		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
        			@Override
        			public void handle(Event event) {
        				Principal.modelo.reserva.setAlojamiento(alojamiento);
        				Principal.aplicacion.CambiarScene("PaneInfo.fxml");
        			}
        		});
        		
        		// añade el efecto rippler a la tarjeta
            	AnchorPane paneSuperior = new AnchorPane();
            	JFXRippler rippler = new JFXRippler(card);
        		JFXDepthManager.setDepth(card, 1);
        		paneSuperior.getChildren().addAll(card);
            	paneSuperior.getChildren().add(rippler);
            	rippler.setRipplerFill(Paint.valueOf("#AAAAAA"));  
        		
            	// añade la tarjeta al grid
    			grid.add(paneSuperior, 0, i); 
    		}	

    	}
	}
	
	private String[] TiposAlojamientoSeleccionados()
	{
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
			lblSaludo.setText("Hola, " + Principal.modelo.cliente.getUser());
			lblSesion.setText("Cerrar Sesion");
		} else {
			lblSaludo.setText("Hola, Anonimo");
			lblSesion.setText("Identifiquese");
		}
	}

}