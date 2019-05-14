package aplicacion;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modelo.Alojamiento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Estancia;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Modelo;

public class ControladorInformacionAloj implements Initializable {
	
    @FXML
    private AnchorPane paneInfoAloj;
	
	@FXML
	private ImageView img;
	
	@FXML
	private Text nombAloj, ubicacion, estancias;
	
	@FXML
	private Label descAloj, habitaciones, tituloHab, lblEstancias, lblTamano;
    
    @FXML
    private Hyperlink verMapa;
	
    @FXML
    private JFXButton atras, reservar;
    
    @FXML
    private Label lblSaludo;

    @FXML
    private Hyperlink lblSesion;
    
    @FXML
    private Label tamano;
    
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
			String str1 = mostrarEstancias(estanciasAloj);
			estancias.setText(str1);
			paneInfoAloj.getChildren().remove(this.lblTamano);
			paneInfoAloj.getChildren().remove(this.tamano);
			
		} else if(alojamiento instanceof Casa) {
			ArrayList<Estancia> estanciasAloj = ((Casa) alojamiento).getEstancias();
			String str1 = mostrarEstancias(estanciasAloj);
			estancias.setText(str1);
			tamano.setText(Float.toString(((Casa) alojamiento).getArea()));
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
			float precio = Principal.modelo.gestorDinero.getPrecioTotalHabitacion(s);
			Date fecha1 = Principal.modelo.reserva.getFechaEntrada();
			Date fecha2 = Principal.modelo.reserva.getFechaSalida();
			str += s.getNombre() + "\t\t" + Principal.modelo.gestorDinero.getPrecioDiaHabitacion(s) + "€/dia" + "\t\t" + precio + "€ para " + Principal.modelo.gestorFechas.setDiasSeleccionados(fecha1.toLocalDate(), fecha2.toLocalDate()).size() + " noches\n";
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
     * @param habitaciones
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