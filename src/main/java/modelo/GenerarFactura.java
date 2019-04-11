package modelo;

import java.io.FileWriter;
import java.io.PrintWriter;

public class GenerarFactura {
	
	/**
	 * Metodo que crea un archivo '.txt' con los datos de la reserva
	 * @param path Ruta donde crear el archivo
	 * @param reserva Objeto reserva del que se quiere imprimir los datos
	 */
	public void crearTxtReserva(String path, Reserva reserva) {
		
		FileWriter fichero = null;	
		PrintWriter writer = null;
		
		try {
			
			fichero = new FileWriter(path);
			writer = new PrintWriter(fichero);
			writer.println("=== DATOS DE LA RESERVA ===");
			writer.println();
			writer.println("Código Reserva: " + reserva.getCodReserva());
			writer.println("Código Alojamiento: " + reserva.getCodHotel());
//			writer.println("Fecha: " + this.fecha);
			writer.println("Precio: " + reserva.getPrecio());
			writer.println();
			writer.println();
			writer.println("Gracias por reservar con nosotros.");
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
		
	}
	
}
