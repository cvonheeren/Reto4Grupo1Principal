package modelo;

import java.io.FileWriter;
import java.io.PrintWriter;

public class GenerarFactura {
	
	/**
	 * Metodo que crea un archivo '.txt' con los datos de la reserva
	 * @param path Ruta donde crear el archivo
	 * @param reserva Objeto reserva del que se quiere imprimir los datos
	 */
	public void crearTxtReserva(String path, int codAlojamiento, float precio ) {
		
		FileWriter fichero = null;	
		PrintWriter writer = null;
		
		try {
			
			fichero = new FileWriter(path);
			writer = new PrintWriter(fichero);
			writer.println("=== DATOS DE LA RESERVA ===");
			writer.println();
			writer.println("Código Reserva: " + 16);
			writer.println("Código Alojamiento: " + codAlojamiento);
//			writer.println("Fecha: " + this.fecha);
			writer.println("Precio: " + precio);
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
