package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import modelo.Alojamiento;
import modelo.AlojamientoLista;
import modelo.Modelo;
import vista.Vista;

public class ControladorSeleccionAlojamiento {
	
	private Controlador cont;
	private Vista vista;
	private Modelo modelo;

	public ControladorSeleccionAlojamiento(Controlador cont)
	{
		this.cont=cont;
		this.vista = cont.miVista;
		this.modelo = cont.miModelo;
		MostrarAlojamientosEnComboBox();
		anadirListeners();	
	}
	
	public void MostrarAlojamientosEnComboBox()
	{
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		AlojamientoLista alojLista = new AlojamientoLista(cont);
		alojLista.setAlojamientoLista();
		alojamientos=alojLista.getAlojamientoLista();
		vista.selHotel.selHotel.removeAllItems();
		for(int i=0;i<alojamientos.size();i++)
		{
			cont.miVista.selHotel.selHotel.addItem(alojamientos.get(i));
		}
		
	}
	
	private void anadirListeners()
	{
		cont.miVista.selHotel.btnCancelar.addActionListener(new ListenerBotonesGenerales());
		cont.miVista.selHotel.btnContinuar.addActionListener(new ListenerBotonesGenerales());
	}
	
	/**
	 * Funcion del boton continuar
	 */
	public void continuar() {
		cont.miVista.selHotel.setVisible(false);
		cont.miVista.pago.setVisible(true);
		cont.miModelo.alojamiento = (Alojamiento) cont.miVista.selHotel.selHotel.getSelectedItem();
		cont.miModelo.reserva.setCodHotel(cont.miModelo.alojamiento.getCodAlojamiento());
		new ControladorPago(cont);
	}
	
	
	/**
	 * Devuelve el panel pago a sus valores iniciales
	 * Se utiliza como accion del boton cancelar
	 */
	public void reset() {
		
	}
	
	/**
	 * Listener para los botones generales de la interfaz
	 */
	private class ListenerBotonesGenerales implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String botonPulsado = ((JButton) e.getSource()).getActionCommand();
			switch (botonPulsado) {
				case "Cancelar":
					vista.bienvenida.setVisible(true);
					vista.selHotel.setVisible(false);
					reset();
					break;
				case "Continuar":
					continuar();
					break;
			}				
		}
	}
}
