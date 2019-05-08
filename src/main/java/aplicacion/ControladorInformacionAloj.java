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
import modelo.Reserva;

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
    
    @FXML
    void IniciarCerrar(ActionEvent event) {
    	Principal.aplicacion.controladorInformacionAloj=this;
    	if(Principal.modelo.cliente==null)
		{
			Principal.aplicacion.CargarSceneLogin();
		}
		else
		{
			Principal.iniciarPrograma();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Reserva reserva = Principal.modelo.reserva;
		Alojamiento alojamiento = reserva.getAlojamiento();
		nombAloj.setText(alojamiento.getNombre());
		descAloj.setText(alojamiento.getDescripcion());
		img.setImage(new Image (alojamiento.getImgurl()));
		ubicacion.setText(alojamiento.getUbicacion());
		
		int tamanoUbicacion = (int) ((int) ubicacion.getBoundsInLocal().getMaxX()+600);
		verMapa.setLayoutX(tamanoUbicacion);
		
		// precio
		precio.setText(reserva.getPrecioTotal() + "€");
		precio.setStyle("-fx-font: 20 arial;");
//		precio.setFill(Paint.valueOf("#0ab21b"));
		
		if(alojamiento instanceof Hotel) {
    		
    		// Muestra las habitaciones disponibles
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
     * @param habitaciones
     * @return
     */
    public String mostrarHabitaciones(ArrayList<Habitacion> habitaciones) {
    	String str = "";
		for (Habitacion s : habitaciones) {
			str += s.getNombre() + "\n";
		    if (s.getCtaCamasSimples() > 0 ) {
		    	str += "- Cama Individual x " + s.getCtaCamasSimples() + "\n";
		    }
		    if (s.getCtaCamasMatrimonio() > 0 ) {
		    	str += "- Cama Matrimonio x " + s.getCtaCamasMatrimonio() + "\n";
		    }
		    if (s.getCtaCamasInfantil() > 0 ) {
		    	str += "- Cama Infantil x " + s.getCtaCamasInfantil() + "\n";
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
    
	public void SesionIniciada() {
		lblSaludo.setText("Hola, " + Principal.modelo.cliente.getDni());
		lblSesion.setText("Cerrar Sesion");
	}
	
}