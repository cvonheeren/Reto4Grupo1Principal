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
		
		String habitaciones = "";
		for (Habitacion h: reserva.getHabitacionesReservadas()) {
			habitaciones += h.getNombre() + " x " + h.getCantidad() + "\n";
		}
		
		try {
			
			fichero = new FileWriter(path);
			writer = new PrintWriter(fichero);
			writer.println("=== DATOS DE LA RESERVA ===");
			writer.println();
			writer.println("Fecha de compra: " + reserva.getFechaEntrada());
			writer.println();
			writer.println("Código reserva: " + reserva.getCodReserva());
			writer.println("Nombre alojamiento: " + reserva.getAlojamiento().getNombre());
			writer.println("Fecha de entrada: " + reserva.getFechaEntrada());
			writer.println("Fecha de salida: " + reserva.getFechaSalida());
			writer.println("Precio: " + reserva.getPrecio() + " €");
			writer.println();
			writer.println();
			writer.println("=== DATOS DEL ALOJAMIENTO ===");
			writer.println();
			writer.println("Nombre alojamiento: " + reserva.getAlojamiento().getNombre());
			writer.println("Ubicacion: " + reserva.getAlojamiento().getUbicacion());
			writer.println("Habitaciones reservadas: ");
			writer.println(habitaciones);
			writer.println();
			writer.println();
			writer.println("=== DATOS DEL CLIENTE ===");
			writer.println();
			writer.println("Nombre: " + reserva.getCliente().getNombre());
			writer.println("Apellidos: " + reserva.getCliente().getApellidos());
			writer.println("DNI: " + reserva.getCliente().getDni());
			writer.println("Fecha de nacimiento: " + reserva.getCliente().getFechaNac());
			writer.println("Email: " + reserva.getCliente().getEmail());
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
