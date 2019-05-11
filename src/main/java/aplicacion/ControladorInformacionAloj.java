package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;
import modelo.Hotel;

public class ControladorInformacionAloj implements Initializable {
	
	@FXML
	private ImageView img;
	
	@FXML
	private Text nombAloj, ubicacion;
	
	@FXML
	private Label descAloj, habitaciones, tituloHab, estancias, precio;
    
    @FXML
    private Hyperlink verMapa;
	
    @FXML
    private JFXButton atras, reservar;
    
    @FXML
    private Label lblSaludo;

    @FXML
    private Hyperlink lblSesion;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Alojamiento alojamiento = Principal.modelo.reserva.getAlojamiento();
		
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		ubicacion.setText(alojamiento.getUbicacion());
		precio.setText(alojamiento.getPrecioHabBarata() + "€");
		img.setImage(new Image(alojamiento.getImgurl()));
		
		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX() + ubicacion.getLayoutX() + 5);
		verMapa.setLayoutX(tamanoUbicacion);
		
		comprobarSesionIniciada();
		setHabitaciones(alojamiento);
	}
    
    @FXML
    void iniciarCerrar(ActionEvent event) {
    	Principal.aplicacion.controladorInformacionAloj=this;
    	if(Principal.modelo.cliente == null) {
    		Principal.aplicacion.CambiarScene("LoginRegistro.fxml");
			Principal.aplicacion.controladorLoginRegistro.setPantallaAnterior("PaneInfo.fxml");
		} else {
			Principal.modelo.cliente = null;
			comprobarSesionIniciada();
		}
    }
	
	@FXML
    void verMapa(ActionEvent event) {
		Principal.aplicacion.verMapa("Mapa.fxml");
    }
	
	@FXML
	void atras(ActionEvent event) {
		Principal.aplicacion.CambiarScene("SeleccionAlojamiento.fxml");
	}
	 
	@FXML
	void reservar(ActionEvent event) {
		Principal.aplicacion.CambiarScene("Pasos.fxml");
	}
	
	/**
	 * 
	 * @param alojamiento
	 */
	public void setHabitaciones(Alojamiento alojamiento) {
		if(alojamiento instanceof Hotel) {
	    	
			tituloHab.setText("Habitaciones disponibles:");
			ArrayList<Habitacion> habitacionesAloj = alojamiento.getHabitaciones();
			String str = mostrarHabitaciones(habitacionesAloj);
			habitaciones.setText(str);
			
		} else if(alojamiento instanceof Apartamento) {
			tituloHab.setText("Estancias:");
			ArrayList<Estancia> estanciasAloj = ((Apartamento) alojamiento).getEstancias();
			String str1 = mostrarEstancias(estanciasAloj);
			estancias.setText(str1);
			
			ArrayList<Habitacion> habitacionesAloj = alojamiento.getHabitaciones();
			String str2 = mostrarHabitaciones(habitacionesAloj);
			habitaciones.setText(str2);
			
		} else if(alojamiento instanceof Casa) {
			tituloHab.setText("Estancias:");
			ArrayList<Estancia> estanciasAloj = ((Casa) alojamiento).getEstancias();
			String str1 = mostrarEstancias(estanciasAloj);
			estancias.setText(str1);
			
			ArrayList<Habitacion> habitacionesAloj = alojamiento.getHabitaciones();
			String str2 = mostrarHabitaciones(habitacionesAloj);
			habitaciones.setText(str2);
		}
	}
	
    /**
     * 
     * @param habitaciones
     * @return
     */
    public String mostrarHabitaciones(ArrayList<Habitacion> habitaciones) {
    	String str = "";
		for (Habitacion s : habitaciones) {
			str += s.getNombre() + "\n";
		    if (s.getCtaCamasSimples() > 0 ) {
		    	str += "- " + s.getCtaCamasSimples() + "x  Cama Individual \n";
		    }
		    if (s.getCtaCamasMatrimonio() > 0 ) {
		    	str += "- " + s.getCtaCamasMatrimonio() + "x Cama Matrimonio \n";
		    }
		    if (s.getCtaCamasInfantil() > 0 ) {
		    	str += "- " + s.getCtaCamasInfantil() + "x Cama Infantil \n";
		    }
		    str += "\n";
		}
		return str;
    }
    
    /**
     * 
     * @param habitaciones
     * @return
     */
    public String mostrarEstancias(ArrayList<Estancia> estancias) {
    	String str = "";
		for (Estancia s : estancias) {
			str += s.getNombre() + " x "+ s.getCantidad() + "\n";
		}
		return str;
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