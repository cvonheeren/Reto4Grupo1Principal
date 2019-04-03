package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Vista extends JFrame {

	public Bienvenida bienvenida;
	public SeleccionHotel selHotel;
	public Pago pago;
	public FinPago finPago;
	
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(new Color(255, 192, 203));	
//		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/Logo_Bilbao.png"));
//	    setIconImage(icon);
	    setTitle("Bidai-On");
	    
	    InstanciarPaneles();
		AddPaneles();
		activarVisibilidad();
	}
	
	private void InstanciarPaneles() {
		bienvenida = new Bienvenida();
		selHotel = new SeleccionHotel();
		pago = new Pago();
		finPago = new FinPago();
	}
	
	private void AddPaneles() {
		getContentPane().add(bienvenida);
		getContentPane().add(selHotel);
		getContentPane().add(pago);
		getContentPane().add(finPago);	
	}
	
	private void activarVisibilidad() {
		bienvenida.setVisible(true);
		selHotel.setVisible(false); 
		pago.setVisible(false);
		finPago.setVisible(false);	
		setVisible(true);
	}

}
