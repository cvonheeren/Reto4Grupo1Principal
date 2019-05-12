package vista;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;
import modelo.Hotel;

public class CardAlojamiento extends AnchorPane implements Initializable {
	
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
    
	public CardAlojamiento(Alojamiento alojamiento) {
		
		this.alojamiento = alojamiento;
		
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
		this.nombre.setText(this.alojamiento.getNombre());
		this.imagen.setImage(new Image(this.alojamiento.getImgurl()));
		JFXDepthManager.setDepth(imagen, 1);
		this.ubicacion.setText(this.alojamiento.getUbicacion());
		this.descripcion.setText(this.alojamiento.getDescripcion());
		this.precio.setText(this.alojamiento.getPrecioHabBarata() + "€");
		setMapa();
		setIconoAloj();
		setHabitaciones();
		setEstancias();
	}
	
    @FXML
    void verAlojamiento(ActionEvent event) {
    	Principal.modelo.reserva.setAlojamiento(this.alojamiento);
		Principal.aplicacion.CambiarScene("PaneInfo.fxml");
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
     * 
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