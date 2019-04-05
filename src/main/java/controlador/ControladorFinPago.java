package controlador;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import modelo.Modelo;
import vista.Vista;

/**
 * Esta clase se encarga de controlar el panel de fin de pago
 */

public class ControladorFinPago implements ActionListener {
	
	public Vista vista;
	public Modelo modelo;
	
	/**
	 * Constructor del controlador de fin de pago
	 * @param vista Instancia del main frame para poder utilizarlo
	 * @param modelo instancia del modelo para poder utilizarlo
	 */
	public ControladorFinPago(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}
	
	/**
	 * Creamos los listeners del panel de fin de pago
	 */
	public void addListeners() {
		vista.finPago.btnImprimir.addActionListener(this);
	}
	
	/**
	 * Funcion que realiza la accion del listener
	 */
	public void actionPerformed(ActionEvent e) {
		
		// guardamos el nombre del boton pulsado
		String botonPulsado = ((JButton) e.getSource()).getActionCommand();
		
		// comprobamos que boton se ha pulsado y ejecutamos sus acciones
		switch (botonPulsado) {
		
			case "Imprimir":
				
				String filename = "";
				
				if(modelo.reserva != null) {
					filename = "Reserva-"+modelo.reserva.getCodReserva() + ".txt";
				}
				
				// le pedimos al usuario que eliga donde guardarlo
				FileDialog fDialog = new FileDialog(vista, "Save", FileDialog.SAVE);
				fDialog.setFile(filename);
				fDialog.setVisible(true);
				String pathReserva = fDialog.getDirectory() + fDialog.getFile();
				
				// creamos los archivos de texto
				imprimirReserva(pathReserva);
				
				// abrimos los archivos en el programa predeterminado
				File file = new File(pathReserva);
				
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// mostrar siguiente panel 'agur'
				vista.despedida.setVisible(true);
				vista.finPago.setVisible(false);
				
			    Timer timer = new Timer();

			    TimerTask task = new TimerTask() {
			        @Override
			        public void run(){
			        	vista.bienvenida.setVisible(true);
			        }
			     };
			     
			    // Empezamos dentro de 10ms y luego lanzamos la tarea cada 1000ms
			    timer.schedule(task, 10000);
				
				break;
		
		}
		
	}
	
	/**
	 * Metodo que imprime la reserva
	 * @param path Ruta de impresion del billete
	 * @return Retorna la ruta del billete
	 */
	public String imprimirReserva(String path) {
		
		FileWriter fichero = null;	
		PrintWriter writer = null;
		
		try {
			
			fichero = new FileWriter(path);
			writer = new PrintWriter(fichero);
			writer.println("=== DATOS DE LA RESERVA ===");
			writer.println();
			writer.println("Código Billete: " + modelo.reserva.getCodReserva());
			writer.println();
			writer.println();
			writer.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fichero != null) {
					fichero.close();
				}
				if (writer != null) {
					writer.close();
				}
				} catch (Exception e) {
					e.getStackTrace();
			}
		}
		
		return path;
		
	}
	
}