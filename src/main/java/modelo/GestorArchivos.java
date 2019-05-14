package modelo;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.Principal;

public class GestorArchivos {
	
	/**
	 * Metodo que crea un archivo '.txt' con los datos de la reserva
	 * @param path Ruta donde crear el archivo
	 * @param reserva Objeto reserva del que se quiere imprimir los datos
	 */
	public void crearTxtReserva(String path, Reserva reserva) {
		
		Modelo modelo = Principal.modelo;
		
		FileWriter fichero = null;	
		PrintWriter writer = null;
		
		String habitaciones = "";
		for (Habitacion h: reserva.getHabitacionesReservadas()) {
			habitaciones += "	- " + h.getNombre() + " x " + h.getCantidad() + "\n";
		}
		
		try {
			
			fichero = new FileWriter(path);
			writer = new PrintWriter(fichero);
			
			// texto a imprimir
			writer.println();
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
			writer.println("Nombre: " + modelo.cliente.getNombre());
			writer.println("Apellidos: " + modelo.cliente.getApellidos());
			writer.println("DNI: " + modelo.cliente.getDni());
			writer.println("Fecha de nacimiento: " + modelo.cliente.getFechaNac());
			writer.println("Email: " + modelo.cliente.getEmail());
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
	
	/**
	 * Genera una ventana de dialogo que premite elegir donde guardar el archivo
	 * @return ruta que el usuario a elegido
	 */
	public String preguntarGuadar() {
		String filename = null;
		String pathReserva = null;
		FileDialog fDialog = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
		if(Principal.modelo.reserva != null) {
			filename = "Reserva-" + Principal.modelo.reserva.getCodReserva() + ".txt";
		}
		if (filename != null) {
			fDialog.setFile(filename);
			fDialog.setVisible(true);
			if (fDialog.getDirectory() != null & fDialog.getFile() != null) {
				pathReserva = fDialog.getDirectory() + fDialog.getFile();
			}
		}
		return pathReserva;
	}
	
	public String leerBases(String ruta) {
		FileReader fileReader = null;
		BufferedReader buffer = null;
		String resultado = "";
		try {
			fileReader = new FileReader(ruta);
			buffer = new BufferedReader(fileReader);
			String linea = "";
			
			while ((linea = buffer.readLine()) != null) {
				resultado=resultado+linea +"\n";					
			}
		} catch (Exception e) {
			//Implementar logger?
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		return resultado;
	}
	
}
