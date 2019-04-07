package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import modelo.*;
import vista.*;

/**
 * Esta clase se encarga de controlar las funciones del panel de pago
 */
public class ControladorPago {
	
	private Controlador cont;
	private Vista vista;
	private Modelo modelo;
	
	/**
	 * Constructor del controlador de pago
	 * 
	 * @param cont Objeto controlador principal
	 */
	public ControladorPago(Controlador cont) {
		this.cont = cont;
		this.vista = cont.miVista;
		this.modelo = cont.miModelo;
		modelo.pago.setPrecioTotal(300);
		vista.pago.actualizarPrecio(modelo.pago.getPrecioTotal());
		vista.pago.actualizarDineroRestante(modelo.pago.getPrecioTotal());
		addListeners();
	}
	
	/**
	 * Añade los listeners a los componentes del panel
	 */
	public void addListeners() {
		Enumeration<AbstractButton> enumeration = this.vista.pago.botonesDinero.getElements();
		while (enumeration.hasMoreElements()) {
		    enumeration.nextElement().addActionListener(new ListenerBotonesDinero());
		}
		vista.pago.btnAtras.addActionListener(new ListenerBotonesGenerales());
		vista.pago.btnCancelar.addActionListener(new ListenerBotonesGenerales());
		vista.pago.btnCancelarPago.addActionListener(new ListenerBotonesGenerales());
		vista.pago.btnContinuar.addActionListener(new ListenerBotonesGenerales());
	}
	
	/**
	 * Suma el dinero introducido. Se utiliza en los botones de monedas y billetes
	 * @param importe Valor a sumar
	 */
	public void sumarDinero(float importe) {
		float dineroIntroducido = modelo.pago.sumarDinero(importe);
		float dineroRestante = modelo.pago.calcularDineroRestante();
		vista.pago.actualizarDineroIntroducido(dineroIntroducido);
		vista.pago.actualizarDineroRestante(dineroRestante);
		comprobarTodoIntroducido();
	}
	
	/**
	 * Resta la ultima moneda o billete introducido
	 */
	public void devolverDinero() {
		float dineroIntroducido = modelo.pago.devolverDinero();
		float dineroRestante = modelo.pago.calcularDineroRestante();
		vista.pago.actualizarDineroIntroducido(dineroIntroducido);
		vista.pago.actualizarDineroRestante(dineroRestante);
		comprobarTodoIntroducido();
	}
	
	/**
	 * Comprueba si se ha introducido todo el dinero necesario para pagar
	 * y habilita y deshabilita los botones del panel segun es necesario
	 */
	public void comprobarTodoIntroducido() {
		if(modelo.pago.comprobarFaltaDinero()) {
			vista.pago.estadoBotonContinuar(false);
			vista.pago.estadoBotonesPago(true);
		} else {
			vista.pago.estadoBotonContinuar(true);
			vista.pago.estadoBotonesPago(false);
		}
	}
	
	/**
	 * Funcion del boton continuar
	 */
	public void continuar() {
		
		float precio = modelo.pago.getPrecioTotal();
		float dineroIntroducido = modelo.pago.getDineroIntroducido();
		float dineroSobrante = modelo.pago.calcularDineroSobrante();
		
		vista.finPago.txtTotal.setText(Float.toString(precio) + " €");
		vista.finPago.txtPagado.setText(Float.toString(dineroIntroducido) + " €");
		vista.finPago.txtDevolver.setText(Float.toString(dineroSobrante) + " €");
		
		vista.finPago.setVisible(true);
		vista.pago.setVisible(false);
		
		// rellenar datos del cliente en el billete	
		modelo.reserva.setPrecio(modelo.pago.getPrecioTotal());
		
		// insertar reserva en BBDD
		modelo.reserva.insertarReserva(cont);
		
		new ControladorFinPago(cont);
	}
	
	/**
	 * Devuelve el panel pago a sus valores iniciales
	 * Se utiliza como accion del boton cancelar
	 */
	public void reset() {
		modelo.alojamiento = null;
		modelo.hotel = null;
		modelo.reserva = null;
		modelo.habitacion = null;
		modelo.pago = null;
		vista.pago.estadoBotonContinuar(false);
		vista.pago.estadoBotonesPago(true);
	}
	
	/**
	 * Listener para los botones de las monedas y los billetes
	 */
	private class ListenerBotonesDinero implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String botonPulsado = ((JButton) e.getSource()).getName();
			float importe = Float.parseFloat(botonPulsado);
			sumarDinero(importe);
		}
	}
	
	/**
	 * Listener para los botones generales de la interfaz
	 */
	private class ListenerBotonesGenerales implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String botonPulsado = ((JButton) e.getSource()).getActionCommand();
			switch (botonPulsado) {
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
					devolverDinero();
					break;
				case "Continuar":
					continuar();
					break;
			}				
		}
	}
}
