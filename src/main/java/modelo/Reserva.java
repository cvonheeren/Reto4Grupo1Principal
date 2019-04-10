package modelo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Reserva {

	private int codReserva;
	private int codHotel;
	private Date fecha;
	private float precio;
	
	/**
	 * Objeto para guardar la reserva que se está gestionando
	 * 
	 * @param codReserva Clave principal de la reserva
	 * @param fecha Fecha en la cual se realiza
	 * @param precio Precio
	 */
	public Reserva(int codReserva, int codHotel, Date fecha, float precio) {
		this.codReserva = codReserva;
		this.codHotel = codHotel;
		this.fecha = fecha;
		this.precio = precio;
	}
	
	public Reserva() {
		
	}

	public int getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}

	public int getCodHotel() {
		return codHotel;
	}

	public void setCodHotel(int codHotel) {
		this.codHotel = codHotel;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	/**
	 * Metodo que imprime la reserva
	 * @param path Ruta de impresion de la reserva
	 */
	public void imprimirReserva(String path) {
		
		FileWriter fichero = null;	
		PrintWriter writer = null;
		
		try {
			
			fichero = new FileWriter(path);
			writer = new PrintWriter(fichero);
			writer.println("=== DATOS DE LA RESERVA ===");
			writer.println();
			writer.println("Código Reserva: " + this.codReserva);
			writer.println("Código Alojamiento: " + this.codHotel);
//			writer.println("Fecha: " + this.fecha);
			writer.println("Precio: " + this.precio);
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
