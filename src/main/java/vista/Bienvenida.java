package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bienvenida extends JPanel {

	public JButton btnContinuar;
	public JLabel lblIcono,lblTocaPantalla;
	
	public Bienvenida() {
		
		// configuracion del panel
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1024, 720);
		
		// logo
		lblIcono = new JLabel("");
//		lblIcono.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/image.png")));
		lblIcono.setBounds(120, 130, 1024, 300);
		add(lblIcono);
		
		// boton para comenzar
		btnContinuar = new JButton("");
		btnContinuar.setSelectedIcon(null);
		btnContinuar.setContentAreaFilled(false);
		btnContinuar.setBorder(null);
		btnContinuar.setForeground(new Color(255, 51, 51));
		btnContinuar.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnContinuar.setBackground(Color.WHITE);
		btnContinuar.setBounds(0, 0, 1024, 720);
		btnContinuar.setOpaque(false);
		btnContinuar.setBorderPainted(false);
		add(btnContinuar);
		
		lblTocaPantalla = new JLabel("Toca la pantalla para comenzar");
		lblTocaPantalla.setHorizontalAlignment(SwingConstants.CENTER);
		lblTocaPantalla.setFont(new Font("Agency FB", Font.BOLD, 24));
		lblTocaPantalla.setForeground(new Color(205, 48, 41));
		lblTocaPantalla.setBounds(362, 440, 300, 30);
		add(lblTocaPantalla);
				
	}

}
