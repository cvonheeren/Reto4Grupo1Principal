package vista;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Servicio;

public class CardAlojamiento extends AnchorPane implements Initializable {
	
	@FXML
    private AnchorPane card;
	
	@FXML
    private AnchorPane paneBase;
	
	@FXML
    private FlowPane servicios;

    @FXML
    private WebView imagen;

    @FXML
    private Text nombre, ubicacion, precio, habitaciones, estancias;

    @FXML
    private Hyperlink mapa;

    @FXML
    private Label descripcion, lblHabitaciones, lblEstancias;
    
    @FXML
    private JFXButton btnVer;
    
    @FXML
    private Label tamano;
    
    private Alojamiento alojamiento;
    
    private float tAnimacion;
    
	public CardAlojamiento(Alojamiento alojamiento, float tAnimacion, boolean animacionActivada) {
		
		this.alojamiento = alojamiento;
		
		this.tAnimacion = tAnimacion;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Alojamiento.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    
	    if(animacionActivada) 
	    	InizializarAnimacion();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LocalDate fecha1 = Principal.modelo.reserva.getFechaEntrada().toLocalDate();
		LocalDate fecha2 = Principal.modelo.reserva.getFechaSalida().toLocalDate();
		Habitacion habitacion = Principal.modelo.gestorDinero.getHabBarata(alojamiento.getHabitaciones(), fecha1);
		float precio = Principal.modelo.gestorDinero.getPrecioTotalHabitacion(habitacion, fecha1, fecha2);
		
		this.nombre.setText(this.alojamiento.getNombre());
		this.ubicacion.setText(this.alojamiento.getUbicacion());
		this.descripcion.setText(this.alojamiento.getDescripcion());
		this.precio.setText(precio + "€\n" + Principal.modelo.gestorFechas.setDiasSeleccionados(fecha1, fecha2).size() + " noches");	
		this.imagen.getEngine().loadContent("<html><body style=\"padding:0px; margin:0px;\"><img src=" + alojamiento.getImgurl() + " width=190px height=190px></img></body></html>");
		JFXDepthManager.setDepth(imagen, 1);

		setDatos();
		setMapa();
		setIconoAloj();
		setHabitaciones();
		setEstancias();
		verServicios(this.alojamiento);
	}
	
	@FXML
    void verAlojamiento(ActionEvent event) {
    	Principal.modelo.reserva.setAlojamiento(this.alojamiento);
		Principal.aplicacion.CambiarScene("PaneInfo.fxml");
    }
	
	/**
	 * 
	 */
    private void InizializarAnimacion() {
    	TranslateTransition transicion = new TranslateTransition();
    	transicion.setFromY(300);
    	transicion.setToY(0);
    	transicion.setDuration(Duration.seconds(tAnimacion));
    	transicion.setNode(paneBase);
    	transicion.play();
		
	}
    
	/**
	 * 
	 */
    public void setDatos() {
    	if (alojamiento instanceof Casa) {
			this.tamano.setText(Float.toString(((Casa) alojamiento).calcularArea()) + " m²");
		} else if(alojamiento instanceof Apartamento) {
			this.tamano.setText(("Piso " + ((Apartamento) alojamiento).getPiso() ));
		}
    }
	
	/*
	 * 
	 */
	public void setMapa() {
		mapa.setLayoutX((int) ubicacion.getBoundsInLocal().getMaxX() + ubicacion.getLayoutX() + 5);
		mapa.setOnAction((e) -> {
			Principal.modelo.reserva.setAlojamiento(alojamiento);
			Principal.aplicacion.verMapa("Mapa.fxml");
        });
	}
	
	/**
	 * muestra los iconos de los servicios
	 * @param alojamiento
	 */
	public void verServicios(Alojamiento alojamiento) {
		alojamiento.setServicios(Principal.modelo.gestorBBDD.obtenerServicios(alojamiento.getCodAlojamiento()));
		ArrayList<Servicio> aux = alojamiento.getServicios();
		
		for (Servicio unservicio : aux) {
			String auxS = unservicio.getIcon();
			Label lblServicios = new Label("");
			lblServicios.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.valueOf(auxS)));
			servicios.getChildren().add(lblServicios);
		}		
	}
	
	/**
	 * 
	 * @param alojamiento
	 */
	public void setIconoAloj() {
		int coordX = (int) ((int) nombre.getBoundsInLocal().getMaxX()+nombre.getLayoutX()+10);
		if(alojamiento instanceof Hotel) {
    		for(int i = 0; i < ((Hotel) alojamiento).getEstrellas(); i++) {
    			FontAwesomeIconView iconoEstrella = crearIconoEstrella(coordX);
		    	card.getChildren().add(iconoEstrella);
		    	coordX = coordX + 15;
	    	}
		} else if(alojamiento instanceof Apartamento) {
    		FontAwesomeIconView iconoLlave = crearIconoLlave(coordX);
	    	card.getChildren().add(iconoLlave);
		} else if(alojamiento instanceof Casa) {
    		FontAwesomeIconView iconoCasa = crearIconoCasa(coordX);
	    	card.getChildren().add(iconoCasa);
		}
	}
	
	/**
	 * 
	 * @param coordX
	 * @return
	 */
	public FontAwesomeIconView crearIconoEstrella(int coordX) {
		FontAwesomeIconView iconoEstrella = new FontAwesomeIconView(FontAwesomeIcon.STAR);
		iconoEstrella.setLayoutX(coordX);
    	iconoEstrella.setLayoutY(40);
    	iconoEstrella.setSize("15");
    	iconoEstrella.setFill(Paint.valueOf("#feba02"));
    	return iconoEstrella;
	}
	
	/**
	 * 
	 * @param coordX
	 * @return
	 */
	public FontAwesomeIconView crearIconoLlave(int coordX) {
		FontAwesomeIconView iconoLlave = new FontAwesomeIconView(FontAwesomeIcon.KEY);
		iconoLlave.setLayoutX(coordX);
    	iconoLlave.setLayoutY(40);
    	iconoLlave.setSize("15");
    	iconoLlave.setFill(Paint.valueOf("#555555"));
    	return iconoLlave;
	}
	
	/**
	 * 
	 * @param coordX
	 * @return
	 */
	public FontAwesomeIconView crearIconoCasa(int coordX) {
		FontAwesomeIconView iconoLlave = new FontAwesomeIconView(FontAwesomeIcon.HOME);
		iconoLlave.setLayoutX(coordX);
    	iconoLlave.setLayoutY(40);
    	iconoLlave.setSize("15");
    	iconoLlave.setFill(Paint.valueOf("#555555"));
    	return iconoLlave;
	}
	
	/**
	 * 
	 * @param alojamiento
	 * @param habitaciones
	 */
	public void setHabitaciones() {
		if(alojamiento instanceof Hotel) {
			String str = mostrarHabitaciones(alojamiento.getHabitaciones());
			this.habitaciones.setText(str);
		} else if(alojamiento instanceof Apartamento) {
			this.card.getChildren().remove(this.lblHabitaciones);
			this.card.getChildren().remove(this.habitaciones);
		} else if(alojamiento instanceof Casa) {	
			this.card.getChildren().remove(this.lblHabitaciones);
			this.card.getChildren().remove(this.habitaciones);
		}
	}
	
	/**
	 * 
	 * @param alojamiento
	 */
	public void setEstancias() {
		if(alojamiento instanceof Hotel) {
			this.card.getChildren().remove(this.lblEstancias);
			this.card.getChildren().remove(this.estancias);
		} else if(alojamiento instanceof Apartamento) {
			String str = mostrarEstancias(((Apartamento)alojamiento).getEstancias(), alojamiento.getHabitaciones());
			this.estancias.setText(str);
		} else if(alojamiento instanceof Casa) {	
			String str = mostrarEstancias(((Casa)alojamiento).getEstancias(), alojamiento.getHabitaciones());
			this.estancias.setText(str);
		}
	}
	
    /**
     * 
     * @param habitaciones
     * @return
     */
    public String mostrarHabitaciones(ArrayList<Habitacion> habitaciones) {
    	String str = "";
		for (Habitacion h : habitaciones) {
			str += h.getCantidad() + " ";
			str += h.getNombre().toLowerCase() + " - ";
			str += h.getNumAdultos() + " Adultos";
			if (h.getCtaCamasInfantil() > 0) {
				str += ", " + h.getCtaCamasInfantil() + " Niños";
			}
			str += "\n";
		}
		return str;
    }
    
    /**
     * 
     * @param estancias
     * @return
     */
    public String mostrarEstancias(ArrayList<Estancia> estancias, ArrayList<Habitacion> habitaciones) {
    	String str = numHabitaciones(habitaciones) + " x Dormitorios, ";
    	int cont = 1;
		for (Estancia e : estancias) {
			str += e.getCantidad() + " x " + e.getNombre().toLowerCase();
			if (cont < estancias.size()) {
				str += ", ";
			}
			cont++;
		}
		return str;
    }
    
    /**
     * Calcula el numero de habitaciones dormitorio de un alojamiento
     * @param habitaciones
     * @return
     */
    public int numHabitaciones(ArrayList<Habitacion> habitaciones) {
    	int suma = 0;
		for (Habitacion h : habitaciones) {
			suma += h.getCantidad();
		}
		return suma;
    }
	
}