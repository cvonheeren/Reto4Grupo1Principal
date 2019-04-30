package aplicacion;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import core.Principal;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

public class ControladorFactura {

    @FXML
    private Label cod;

    @FXML
    private Label tipo;

    @FXML
    private Label nombre;

    @FXML
    private Label fechaEntrada;

    @FXML
    private Label fechaSalida;

    @FXML
    private Label adultos;

    @FXML
    private Label ninos;

    @FXML
    private Label habitaciones;

    @FXML
    private Label precio;

    @FXML
    private JFXButton inicio;

    @FXML
    private WebView mapa;
    
    @FXML
    private JFXButton btnimprimir;

    @FXML
    void Inicio(MouseEvent event) {

    }
    
    @FXML
    void Inicio(MouseEvent event) {
    }
    
    @FXML
    void Imprimir(MouseEvent event) {
    	String pathReserva = preguntarGuadar();
		
		// creamos el archivo de texto
		Principal.modelo.generarFactura.crearTxtReserva(pathReserva, Principal.modelo.alojamiento.getCodAlojamiento(), Principal.modelo.pago.getPrecioTotal());
		
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
			filename = "Reserva-"+Principal.modelo.reserva.getCodReserva() + ".txt";
		}
		FileDialog fDialog = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
		fDialog.setFile(filename);
		fDialog.setVisible(true);
		String pathReserva = fDialog.getDirectory() + fDialog.getFile();
		return pathReserva;
	}

}
