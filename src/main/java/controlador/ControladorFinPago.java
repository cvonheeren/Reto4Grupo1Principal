package controlador;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import modelo.Modelo;
import vista.Vista;

/**
 * Esta clase se encarga de controlar el panel de fin de pago
 */

public class ControladorFinPago  {
	
	private Controlador cont;
	public Vista vista;
	public Modelo modelo;
	
	/**
	 * Constructor del controlador de fin de pago
	 * 
	 * @param cont Objeto controlador principal
	 */
	public ControladorFinPago(Controlador cont) {
		this.cont = cont;
		this.vista = cont.miVista;
		this.modelo = cont.miModelo;
		addListeners();
	}
	
	/**
	 * Creamos los listeners del panel de fin de pago
	 */
	public void addListeners() {
		vista.finPago.btnImprimir.addActionListener(new ListenerBotonesGenerales());
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
		if(modelo.reserva != null) {
			filename = "Reserva-"+modelo.reserva.getCodReserva() + ".txt";
		}
		FileDialog fDialog = new FileDialog(vista, "Save", FileDialog.SAVE);
		fDialog.setFile(filename);
		fDialog.setVisible(true);
		String pathReserva = fDialog.getDirectory() + fDialog.getFile();
		return pathReserva;
	}
	
	/**
	 * Crea un timer con el intervalo de tiempo especificado
	 * 
	 * @param ms intervalo de tiempo en milisegundos
	 */
	public void crearTimer(int ms) {
		Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	        @Override
	        public void run(){
	        	vista.bienvenida.setVisible(true);
	        }
	     };
	    timer.schedule(task, ms);
	}
	
	/**
	 * Listener para los botones generales de la interfaz
	 */
	private class ListenerBotonesGenerales implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String botonPulsado = ((JButton) e.getSource()).getActionCommand();
			
			// comprobamos que boton se ha pulsado y ejecutamos sus acciones
			switch (botonPulsado) {
			
				case "Imprimir":
					
					String pathReserva = preguntarGuadar();
					
					// creamos el archivo de texto
					modelo.reserva.imprimirReserva(pathReserva);
					
					// abrimos el archivos en el programa predeterminado
					abrirArchivo(pathReserva);
					
					// mostrar siguiente panel 'agur'
					vista.despedida.setVisible(true);
					vista.finPago.setVisible(false);
					
					crearTimer(10000);
					
					break;
			}
		}
	}
	
}