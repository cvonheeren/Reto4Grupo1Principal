package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Despedida extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JLabel lblIcono,lblElorrieta,lblBbilbao,lblUml;
	public JLabel lblDespedida;
	
	public Despedida() {
		
		// configuracion del panel
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1024, 720);
		
		// logo
		lblIcono = new JLabel("");
//		lblIcono.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/image.png")));
		lblIcono.setBounds(120, 130, 1024, 300);
		add(lblIcono);
		
		// mensaje de despedida
		lblDespedida = new JLabel("¡Que tenga un buen viaje!");
		lblDespedida.setFont(new Font("Agency FB", Font.BOLD, 36));
		lblDespedida.setBounds(380, 420, 300, 50);
		add(lblDespedida);
		
//		lblElorrieta = new JLabel("");
//		lblElorrieta.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/logo_elorrieta2.png")));
//		lblElorrieta.setBounds(349, 481, 326, 188);
//		add(lblElorrieta);
//		
//		lblBbilbao = new JLabel("");
//		lblBbilbao.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/Bbilbao2.jpg")));
//		lblBbilbao.setBounds(10, 512, 309, 157);
//		add(lblBbilbao);
//		
//		lblUml = new JLabel("");
//		lblUml.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/uml.png")));
//		lblUml.setBounds(698, 472, 309, 199);
//		add(lblUml);

	}

}