package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorBienvenida implements ActionListener {
	
	private Controlador cont;
	
	public ControladorBienvenida(Controlador cont)
	{
		this.cont=cont;
		anadirListeners();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		cont.miVista.bienvenida.setVisible(false);
		cont.miVista.selHotel.setVisible(true);
		new ControladorSeleccionAlojamiento(cont);
	}
	
	
	
	private void anadirListeners()
	{
		cont.miVista.bienvenida.btnContinuar.addActionListener(this);
	}
	
}
