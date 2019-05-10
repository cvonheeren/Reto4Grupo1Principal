package aplicacion;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import core.Principal;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Habitacion;

public class ControladorFactura implements Initializable {

   @FXML
    private Label nombre;

    @FXML
    private Label habitaciones;

    @FXML
    private Label fechaEntrada;

    @FXML
    private Label fechaSalida;

    @FXML
    private Label precio;

    @FXML
    private Label pension;

    @FXML
    private Label servicios;

    @FXML
    private Label ubicacion;

    @FXML
    private JFXButton btnimprimir;

    @FXML
    void imprimir(ActionEvent event) {
    	String pathReserva = preguntarGuadar();
		
		// creamos el archivo de texto
		Principal.modelo.generarFactura.crearTxtReserva(pathReserva, Principal.modelo.reserva);
		
		// abrimos el archivos en el programa predeterminado
		abrirArchivo(pathReserva);
    }
    
    /**
	 * Abre el archivo indicado en la ruta con el editor predeterminado
	 * 
	 * @param pathReserva ruta del archivo
	 */
	public void abrirArchivo(String pathReserva) {
		File file = new File(pathReserva);
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Genera una ventana de dialogo que premite elegir donde guardar el archivo
	 * 
	 * @return ruta que el usuario a elegido
	 */
	public String preguntarGuadar() {
		String filename = "";
		if(Principal.modelo.reserva != null) {
			filename = "Reserva-" + Principal.modelo.reserva.getCodReserva() + ".txt";
		}
		FileDialog fDialog = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
		fDialog.setFile(filename);
		fDialog.setVisible(true);
		String pathReserva = fDialog.getDirectory() + fDialog.getFile();
		return pathReserva;
	}
	
	public void ActualizarDatos() {
		nombre.setText(Principal.modelo.reserva.getAlojamiento().getNombre());
		ubicacion.setText(Principal.modelo.reserva.getAlojamiento().getUbicacion());
		fechaEntrada.setText(Principal.modelo.reserva.getFechaEntrada().toLocalDate().toString());
		fechaSalida.setText(Principal.modelo.reserva.getFechaSalida().toLocalDate().toString());
		precio.setText(Principal.modelo.reserva.getPrecio() + "€");
		String str = "";
		for (Habitacion h: Principal.modelo.reserva.getHabitacionesReservadas()) {
		    str += h.getNombre() + " x " + h.getCantidad() + "\n";
		}
		habitaciones.setText(str);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Principal.aplicacion.controladorFactura=this;
		
	}


}
