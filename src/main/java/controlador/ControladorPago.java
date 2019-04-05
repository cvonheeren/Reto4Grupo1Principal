package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.*;
import vista.*;

/**
 * Esta clase se encarga de controlar las funciones del panel de pago
 */

public class ControladorPago implements ActionListener{
	
	private Vista vista;
	private Modelo modelo;
	
	private JLabel introducido, restante;
	public float total = 0;
	public float dinero = 0;
	private float falta = 0;
	private float moneda = 0;
	public float sobra = 0;
	public float[] monedas;
	
	/**
	 * Constructor del controlador de pago
	 * 
	 * @param vista: Guarda el objeto vista para poder utilizar los distintos elementos de la interfaz
	 * @param modelo: Guarda el objeto modelo para poder acceder a los metodos del modelo
	 */
	public ControladorPago(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
		this.introducido = this.vista.pago.lblDineroIntro;
		this.restante = this.vista.pago.lblDineroRest;
		this.monedas = new float[0];
		this.total = 0;
		this.dinero = 0;
		this.falta = 0;
		this.moneda = 0;
		this.sobra = 0;
		
	}
	
	/**
	 * Generamos los listeners necesarios para el panel
	 */
	public void addListeners() {
		this.vista.pago.btn001.addActionListener(this);
		this.vista.pago.btn002.addActionListener(this);
		this.vista.pago.btn005.addActionListener(this);
		this.vista.pago.btn010.addActionListener(this);
		this.vista.pago.btn020.addActionListener(this);
		this.vista.pago.btn050.addActionListener(this);
		this.vista.pago.btn1.addActionListener(this);
		this.vista.pago.btn2.addActionListener(this);
		this.vista.pago.btn5.addActionListener(this);
		this.vista.pago.btn10.addActionListener(this);
		this.vista.pago.btn20.addActionListener(this);
		this.vista.pago.btn50.addActionListener(this);
		this.vista.pago.btn100.addActionListener(this);
		this.vista.pago.btn200.addActionListener(this);
		
		this.vista.pago.btnAtras.addActionListener(this);
		this.vista.pago.btnCancelar.addActionListener(this);
		this.vista.pago.btnCancelarPago.addActionListener(this);
		this.vista.pago.btnContinuar.addActionListener(this);
	}
	
	/**
	 * Metodo que contiene las acciones realizadas por cada uno de los listeners
	 */
	public void actionPerformed(ActionEvent e) {
		
		String botonPulsado = ((JButton) e.getSource()).getActionCommand();
		float importe;
		
		switch (botonPulsado) {
		
			case "0,01 €":
				importe = 0.01f;
				FuncionBotonDinero(importe);
				break;
				
			case "0,02 €":
				importe = 0.02f;
				FuncionBotonDinero(importe);
				break;
				
			case "0,05 €":
				importe = 0.05f;
				FuncionBotonDinero(importe);
				break;
				
			case "0,10 €":
				importe = 0.1f;
				FuncionBotonDinero(importe);
				break;
				
			case "0,20 €":
				importe = 0.2f;
				FuncionBotonDinero(importe);
				break;
				
			case "0,50 €":
				importe = 0.5f;
				FuncionBotonDinero(importe);
				break;
				
			case "1 €":
				importe = 1f;
				FuncionBotonDinero(importe);
				break;
				
			case "2 €":
				importe = 2f;
				FuncionBotonDinero(importe);
				break;
				
			case "5 €":
				importe = 5f;
				FuncionBotonDinero(importe);
				break;
				
			case "10 €":
				importe = 10f;
				FuncionBotonDinero(importe);
				break;
				
			case "20 €":
				importe = 20f;
				FuncionBotonDinero(importe);
				break;
				
			case "50 €":
				importe = 50f;
				FuncionBotonDinero(importe);
				break;
				
			case "100 €":
				importe = 100f;
				FuncionBotonDinero(importe);
				break;
				
			case "200 €":
				importe = 200f;
				FuncionBotonDinero(importe);
				break;
				
			case "Atrás":
				vista.selHotel.setVisible(true);
				vista.pago.setVisible(false);
				break;
				
			case "Cancelar":
				vista.bienvenida.setVisible(true);
				vista.pago.setVisible(false);
				reset();
				break;
				
			case "Devolver":
				FuncionDevolver();
				break;
				
			case "Continuar":
				FuncionContinuar();
				break;
				
		}
	}
	
	/**
	 * Realiza las operaciones con el importe de cada boton, tambien almacena las cantidades que se ban introduciendo, se calcula el dinero que fala por introducir o lo que sobra
	 * @param importe: Variable que guarda el valor monetario de cada uno de los botones de pago, para luego pasarlo como parametro en la funcion hace los calculos con el dinero
	 */
	public void FuncionBotonDinero(float importe) {
		int posicion;
		this.total = this.modelo.precioTotal;
		dinero = dinero + importe;
		dinero = Math.round(dinero*100);
		dinero = dinero/100;
		monedas = modelo.pago.redimensionarArrayMayor(monedas);
		posicion = monedas.length - 1;
		monedas[posicion] = importe;
		introducido.setText(Float.toString(dinero) + " €");
		if(dinero < total) {
			falta = modelo.pago.falta(total, dinero);
			restante.setText(Float.toString(falta) + " €");
		}
		else
			TodoIntroducido();
	}
	
	/**
	 * Realiza las operaciones necesarias cuando el usuario pide que se le devuelva la ultima moneda introducida
	 */
	public void FuncionDevolver() {
		int posicion;
		if(dinero > 0) {
			posicion = monedas.length - 1;
			moneda = monedas[posicion];
			dinero = dinero - moneda;
			dinero = Math.round(dinero*100); 
			dinero = dinero/100;
			introducido.setText(Float.toString(dinero) + " €");
			monedas = modelo.pago.redimensionarArrayMenor(monedas);
			if(dinero < total) {
				falta = modelo.pago.falta(total, dinero);
				restante.setText(Float.toString(falta) + " €");
				this.vista.pago.btn001.setEnabled(true);
				this.vista.pago.btn002.setEnabled(true);
				this.vista.pago.btn005.setEnabled(true);
				this.vista.pago.btn010.setEnabled(true);
				this.vista.pago.btn020.setEnabled(true);
				this.vista.pago.btn050.setEnabled(true);
				this.vista.pago.btn1.setEnabled(true);
				this.vista.pago.btn2.setEnabled(true);
				this.vista.pago.btn5.setEnabled(true);
				this.vista.pago.btn10.setEnabled(true);
				this.vista.pago.btn20.setEnabled(true);
				this.vista.pago.btn50.setEnabled(true);
				this.vista.pago.btn100.setEnabled(true);
				this.vista.pago.btn200.setEnabled(true);
				
				this.vista.pago.btnContinuar.setVisible(false);
				this.vista.pago.btnContinuar.setEnabled(false);
			}
			else
				TodoIntroducido();
		}
	}
	
	/**
	 * Funcion que se utiliza una vez que el dinero ha llegado o sobrepasado el total
	 */
	public void TodoIntroducido() {
		
		introducido.setText(Float.toString(dinero) + " €");
		restante.setText("0 €");
		
		this.vista.pago.btn001.setEnabled(false);
		this.vista.pago.btn002.setEnabled(false);
		this.vista.pago.btn005.setEnabled(false);
		this.vista.pago.btn010.setEnabled(false);
		this.vista.pago.btn020.setEnabled(false);
		this.vista.pago.btn050.setEnabled(false);
		this.vista.pago.btn1.setEnabled(false);
		this.vista.pago.btn2.setEnabled(false);
		this.vista.pago.btn5.setEnabled(false);
		this.vista.pago.btn10.setEnabled(false);
		this.vista.pago.btn20.setEnabled(false);
		this.vista.pago.btn50.setEnabled(false);
		this.vista.pago.btn100.setEnabled(false);
		this.vista.pago.btn200.setEnabled(false);
		
		this.vista.pago.btnContinuar.setVisible(true);
		this.vista.pago.btnContinuar.setEnabled(true);
		
	}
	
	/**
	 * Funcion del boton continuar
	 */
	public void FuncionContinuar() {
		sobra = modelo.pago.sobra(total, dinero);
		vista.finPago.setVisible(true);
		vista.pago.setVisible(false);
		
		vista.finPago.txtTotal.setText(Float.toString(total) + " €");
		vista.finPago.txtPagado.setText(Float.toString(dinero) + " €");
		vista.finPago.txtDevolver.setText(Float.toString(sobra) + " €");
		
		// rellenar datos del cliente en el billete	
		modelo.reserva.setPrecio(modelo.precioTotal);
		
		// insertar billete en BBDD
//		int codBilleteIda = modelo.consultas.insertarBillete(modelo.billeteIda);
//		modelo.reserva.setCodBillete(codBilleteIda);
//		
//		// comprobar si existe billete de vuelta
//		if (modelo.billeteVuelta != null) {
//			
//			// rellenar datos del cliente en el billete	
//			modelo.billeteVuelta.setDni(modelo.cliente.getDni());
//			
//			// insertar billete en BBDD
//			int codBilleteVuelta = modelo.consultas.insertarBillete(modelo.billeteVuelta);
//			modelo.billeteVuelta.setCodBillete(codBilleteVuelta);
//			
//		}
		
	}
	
	/**
	 * Funcion del boton de cancelar
	 */
	public void reset() {
		modelo.alojamiento = null;
		modelo.hotel = null;
		modelo.reserva = null;
		modelo.habitacion = null;
		modelo.pago = null;
		
		this.vista.pago.btn001.setEnabled(true);
		this.vista.pago.btn002.setEnabled(true);
		this.vista.pago.btn005.setEnabled(true);
		this.vista.pago.btn010.setEnabled(true);
		this.vista.pago.btn020.setEnabled(true);
		this.vista.pago.btn050.setEnabled(true);
		this.vista.pago.btn1.setEnabled(true);
		this.vista.pago.btn2.setEnabled(true);
		this.vista.pago.btn5.setEnabled(true);
		this.vista.pago.btn10.setEnabled(true);
		this.vista.pago.btn20.setEnabled(true);
		this.vista.pago.btn50.setEnabled(true);
		this.vista.pago.btn100.setEnabled(true);
		this.vista.pago.btn200.setEnabled(true);
		
		this.vista.pago.btnContinuar.setVisible(false);
		this.vista.pago.btnContinuar.setEnabled(false);
	}
}
