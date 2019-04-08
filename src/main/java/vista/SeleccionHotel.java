package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import modelo.Alojamiento;

public class SeleccionHotel extends JPanel {

	public JLabel lblIcono,lblHotel;
	public JButton btnLogin,btnRegistro,btnContinuar,btnCancelar, btnAtras;
	public JComboBox<Alojamiento> selHotel;
	
	public SeleccionHotel() {
		
		// configuracion del panel
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1024, 720);
		
		// logo
		lblIcono = new JLabel("");
//		lblIcono.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/image2.png")));
		lblIcono.setOpaque(true);
		lblIcono.setBorder(null);
		lblIcono.setForeground(new Color(0, 0, 0));
		lblIcono.setBackground(new Color(255, 255, 255));
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(120, 0, 1024, 210);
		add(lblIcono);
		
		// label seleccion hotel
		lblHotel = new JLabel("Selecccione Hotel:");
		lblHotel.setFont(new Font("Agency FB", Font.BOLD, 22));
		lblHotel.setHorizontalAlignment(SwingConstants.LEFT);
		lblHotel.setBounds(45, 200, 300, 40);
		add(lblHotel);
		
		// seleccion de hotel
		selHotel = new JComboBox<Alojamiento>();
		selHotel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		selHotel.setFont(new Font("Agency FB", Font.PLAIN, 20));
		selHotel.setForeground(new Color(0, 0, 0));
		selHotel.setBackground(new Color(204, 150, 150));
		selHotel.setBounds(45, 245, 327, 30);
		add(selHotel);
		
		/*
		 * Botones
		 */
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(205, 48, 41));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBorder(null);
		btnCancelar.setSelectedIcon(null);
		btnCancelar.setBounds(45, 607, 100, 25);
		add(btnCancelar);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setBackground(new Color(205, 48, 41));
		btnAtras.setBorderPainted(false);
		btnAtras.setBorder(null);
		btnAtras.setSelectedIcon(null);
		add(btnAtras);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnContinuar.setForeground(new Color(255, 255, 255));
		btnContinuar.setBackground(new Color(205, 48, 41));
		btnContinuar.setBorderPainted(false);
		btnContinuar.setBorder(null);
		btnContinuar.setSelectedIcon(null);
		btnContinuar.setBounds(846, 642, 100, 25);
		add(btnContinuar);

	}

}
