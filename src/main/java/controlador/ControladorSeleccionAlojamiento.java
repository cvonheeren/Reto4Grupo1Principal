package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import modelo.Alojamiento;
import modelo.Hotel;
import vista.Vista;

public class ControladorSeleccionAlojamiento implements ActionListener {
	
	Controlador cont;

	public ControladorSeleccionAlojamiento(Controlador cont)
	{
		this.cont=cont;
		MostrarAlojamientosEnComboBox();
		anadirListeners();	
	}
	
	public void MostrarAlojamientosEnComboBox()
	{
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		alojamientos=cont.miModelo.alojamiento.CargarListaAlojamientos(cont);
		for(int i=0;i<alojamientos.size();i++)
		{
			cont.miVista.selHotel.selHotel.addItem(alojamientos.get(i));
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cont.miVista.selHotel.setVisible(false);
		cont.miVista.pago.setVisible(true);
		cont.miModelo.alojamiento=(Alojamiento) cont.miVista.selHotel.selHotel.getSelectedItem();
		new ControladorPago(cont);
	}
	
	private void anadirListeners()
	{
		cont.miVista.selHotel.btnContinuar.addActionListener(this);
	}
	
	
	
}
