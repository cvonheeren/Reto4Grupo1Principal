package modelo;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {

	
//	public Habitacion habitacion = null;
	public Cliente cliente = null;
	public Reserva reserva = null;
	public Pago pago = null;
	public GestorBBDD gestorBBDD = null;
	public GenerarFactura generarFactura = null;
	public GestorValidaciones gestorValidaciones = null;
	
	/**
	 * Constructor
	 */
	public Modelo() {
		this.pago = new Pago();
		this.gestorBBDD = new GestorBBDD();
		this.generarFactura = new GenerarFactura();
		this.reserva = new Reserva();
		this.gestorValidaciones = new GestorValidaciones();
	}

	public String LeerTexto(String ruta) {
			String[] datos = new String[4];
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

