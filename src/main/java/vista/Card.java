package vista;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;
import modelo.Hotel;

public class Card extends AnchorPane implements Initializable {
	
	@FXML
    private AnchorPane card;

    @FXML
    private ImageView imagen;

    @FXML
    private Text nombre, ubicacion, precio, habitaciones, estancias;

    @FXML
    private Hyperlink mapa;

    @FXML
    private Label descripcion, lblHabitaciones, lblEstancias;
    
    @FXML
    private JFXButton btnVer;
    
    private Alojamiento alojamiento;
    
    private ArrayList<Habitacion> ArrayHabitaciones;
    
    private JFXDatePicker fechaEntrada, fechaSalida;

    @FXML
    void verAlojamiento(ActionEvent event) {
    	verInfo(this.alojamiento);
    }
    
	public Card(Alojamiento alojamiento, ArrayList<Habitacion> habitaciones, JFXDatePicker fechaEntrada, JFXDatePicker fechaSalida) {
		
		this.alojamiento = alojamiento;
		this.ArrayHabitaciones = habitaciones;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Alojamiento.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setNombre(this.alojamiento.getNombre());
		setImagen(this.alojamiento.getImgurl());
		setUbicacion(this.alojamiento.getUbicacion());
		setMapa(this.alojamiento);
		setDescripcion(this.alojamiento.getDescripcion());
		setPrecio(this.alojamiento.getPrecioHabBarata() + "€");
		setIconoAloj(this.alojamiento);
		setHabitaciones(this.alojamiento, this.ArrayHabitaciones);
		setEstancias(this.alojamiento);
		setListener();
	}
	
	/**
	 * 
	 * @param card
	 * @param alojamiento
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public AnchorPane añadirListenerSeleccion(AnchorPane card, Alojamiento alojamiento) {
		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				verInfo(alojamiento);
			}
		});
		return card;
	}
	
	public void setListener() {
		this.card = añadirListenerSeleccion(this.card, this.alojamiento);
	}

	public void setImagen(String url) {
		this.imagen.setImage(new Image(url));
	}

	public void setNombre(String nombre) {
		this.nombre.setText(nombre);
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion.setText(ubicacion); 
	}
	
	public void setMapa(Alojamiento alojamiento) {
		int tamanoUbicacion = (int) ((int) this.ubicacion.getBoundsInLocal().getMaxX()+this.ubicacion.getLayoutX() + 5);
		mapa.setLayoutX(tamanoUbicacion);
		mapa.setOnAction((e) -> {
			Principal.modelo.reserva.setAlojamiento(alojamiento);
			Principal.aplicacion.verMapa("Mapa.fxml");
        }); 
	}
	
	public void setDescripcion(String desc) {
		this.descripcion.setText(desc);
	}
	
	public void setPrecio(String precio) {
		this.precio.setText(precio);
	}
	
	public void setIconoAloj(Alojamiento alojamiento) {
		int coordX = (int) ((int) this.nombre.getBoundsInLocal().getMaxX()+this.nombre.getLayoutX()+10);
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
	
	public void setHabitaciones(Alojamiento alojamiento, ArrayList<Habitacion> habitaciones) {
		if(alojamiento instanceof Hotel) {
			String str = mostrarHabitaciones(habitaciones);
			this.habitaciones.setText(str);
		} else if(alojamiento instanceof Apartamento) {
			this.card.getChildren().remove(this.lblHabitaciones);
			this.card.getChildren().remove(this.habitaciones);
		} else if(alojamiento instanceof Casa) {	
			this.card.getChildren().remove(this.lblHabitaciones);
			this.card.getChildren().remove(this.habitaciones);
		}
	}
	
	public void setEstancias(Alojamiento alojamiento) {
		if(alojamiento instanceof Hotel) {
			this.card.getChildren().remove(this.lblEstancias);
			this.card.getChildren().remove(this.estancias);
		} else if(alojamiento instanceof Apartamento) {
			String str = mostrarEstancias(((Apartamento)alojamiento).getEstancias());
			this.estancias.setText(str);
		} else if(alojamiento instanceof Casa) {	
			String str = mostrarEstancias(((Casa)alojamiento).getEstancias());
			this.estancias.setText(str);
		}
	}
	
	public void setButton() {
//		this.btnVer.setLayoutY(card.getBoundsInLocal().getHeight()-20);
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
    public String mostrarEstancias(ArrayList<Estancia> estancias) {
    	String str = "";
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
     * 
     * @param alojamiento
     */
	public void verInfo(Alojamiento alojamiento) {
		Date fechaEntradaDate = Date.valueOf(this.fechaEntrada.getValue());
		Date fechaSalidaDate = Date.valueOf(this.fechaSalida.getValue());
		Principal.modelo.reserva.setAlojamiento(alojamiento);
		Principal.modelo.reserva.setFechaEntrada(fechaEntradaDate);
		Principal.modelo.reserva.setFechaSalida(fechaSalidaDate);
		Principal.aplicacion.CambiarScene("PaneInfo.fxml");
	}
	
}