package aplicacion;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Servicio;

public class ControladorInformacionAloj implements Initializable {
	
    @FXML
    private AnchorPane paneInfoAloj;
	
	@FXML
	private ImageView img;
	
	@FXML
	private Text nombAloj, ubicacion, estancias;
	
	@FXML
	private Label descAloj, habitaciones, tituloHab, lblEstancias, lblTamano, lblSaludo, tamano;
    
    @FXML
    private Hyperlink verMapa, lblSesion;
	
    @FXML
    private JFXButton atras, reservar;
    
    @FXML
    private VBox servicios;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Alojamiento alojamiento = Principal.modelo.reserva.getAlojamiento();
		
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		ubicacion.setText(alojamiento.getUbicacion());
		img.setImage(new Image(alojamiento.getImgurl()));
		
		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX() + ubicacion.getLayoutX() + 5);
		verMapa.setLayoutX(tamanoUbicacion);
		
		comprobarSesionIniciada();
		setHabitaciones(alojamiento);
		verServicios(alojamiento);
		
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
		new ControladorPasos();
		Principal.aplicacion.CambiarScene("Pasos.fxml");
	}	
	
	/**
	 * 
	 * @param alojamiento
	 */
	public void verServicios(Alojamiento alojamiento) {
		alojamiento.setServicios(Principal.modelo.gestorBBDD.obtenerServicios(alojamiento.getCodAlojamiento()));
		ArrayList<Servicio> aux = alojamiento.getServicios();
		for (Servicio unservicio : aux) {
			String auxS = unservicio.getIcon();
			Label lblServicios = new Label("- " + unservicio.getNombre() + "\n");
			lblServicios.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.valueOf(auxS)));
			servicios.getChildren().add(lblServicios);
		}		
	}
	
	/**
	 * Carga las habitaciones del alojamiento seleccionado y otra info del alojamiento
	 * @param alojamiento
	 */
	public void setHabitaciones(Alojamiento alojamiento) {
		
		tituloHab.setText("Habitaciones disponibles:");
		ArrayList<Habitacion> habitacionesAloj = alojamiento.getHabitaciones();
		String str = mostrarHabitaciones(habitacionesAloj);
		habitaciones.setText(str);
		
		if(alojamiento instanceof Hotel) {   	
			paneInfoAloj.getChildren().remove(this.lblEstancias);
			paneInfoAloj.getChildren().remove(this.estancias);
			paneInfoAloj.getChildren().remove(this.lblTamano);
			paneInfoAloj.getChildren().remove(this.tamano);		
		} else if(alojamiento instanceof Apartamento) {
			ArrayList<Estancia> estanciasAloj = ((Apartamento) alojamiento).getEstancias();
			String str1 = mostrarEstancias(estanciasAloj, alojamiento.getHabitaciones());
			estancias.setText(str1);
			lblTamano.setText("Altura");
			tamano.setText(("Piso " + ((Apartamento) alojamiento).getPiso() ));
		} else if(alojamiento instanceof Casa) {
			ArrayList<Estancia> estanciasAloj = ((Casa) alojamiento).getEstancias();
			String str1 = mostrarEstancias(estanciasAloj, alojamiento.getHabitaciones());
			estancias.setText(str1);
			tamano.setText(Float.toString(((Casa) alojamiento).calcularArea()) + " m²");
		}
	}
	
    /**
     * Muestra las habitaciones reservadas en un string
     * @param habitaciones
     * @return
     */
    public String mostrarHabitaciones(ArrayList<Habitacion> habitaciones) {
    	String str = "";
		LocalDate fecha1 = Principal.modelo.reserva.getFechaEntrada().toLocalDate();
		LocalDate fecha2 = Principal.modelo.reserva.getFechaSalida().toLocalDate(); 
		for (Habitacion s : habitaciones) {
			// carga el precio total de la estancia para la habitacion
			float precio = Principal.modelo.gestorDinero.getPrecioTotalHabitacion(s, fecha1, fecha2);
			// muestra el precio de la habitacion por noche y por estancia
			str += s.getNombre() + "\t\t" + precio + "€/Estancia (" + Principal.modelo.gestorDinero.getPrecioDiaHabitacion(s, fecha1) + "€/noche)\n";
		    if (s.getCtaCamasSimples() > 0 ) {
		    	str += "\t- " + s.getCtaCamasSimples() + "x  Cama Individual \n";
		    }
		    if (s.getCtaCamasMatrimonio() > 0 ) {
		    	str += "\t- " + s.getCtaCamasMatrimonio() + "x Cama Matrimonio \n";
		    }
		    if (s.getCtaCamasInfantil() > 0 ) {
		    	str += "\t- " + s.getCtaCamasInfantil() + "x Cama Infantil \n";
		    }
		    str += "\n";
		}
		return str;
    }
    
    /**
     * 
     * @param estancias
     * @param habitaciones
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