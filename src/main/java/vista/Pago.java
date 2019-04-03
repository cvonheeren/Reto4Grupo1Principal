package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Pago extends JPanel {
	
	public JPanel panelTotales;
	public JButton btnContinuar, btnCancelar, btnCancelarPago, btnAtras, btnSeparador, btn200, btn100, btn50, btn20, btn10, btn5, btn2, btn1, btn050, btn020, btn010, btn005, btn002, btn001;
	public JLabel lblIcono, lblTotal, lblDineroTotal, lblIntroducido, lblDineroIntro, lblRestante, lblDineroRest;
	
	public Pago() {
		
		// configuracion del panel
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1024, 720);
		
		// logo
		lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/image2.png")));
		lblIcono.setOpaque(true);
		lblIcono.setBorder(null);
		lblIcono.setForeground(new Color(0, 0, 0));
		lblIcono.setBackground(new Color(255, 255, 255));
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(120, 0, 1024, 210);
		add(lblIcono);
		
		/*
		 * Botones monedas y billetes
		 */
		btn200 = new JButton("200 \u20AC");
		btn200.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn200.setForeground(new Color(255, 255, 255));
		btn200.setBackground(new Color(205, 48, 41));
		btn200.setBorderPainted(false);
		btn200.setBorder(null);
		btn200.setSelectedIcon(null);
		btn200.setBounds(450, 250, 85, 40);
		add(btn200);
		
		btn100 = new JButton("100 \u20AC");
		btn100.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn100.setForeground(new Color(255, 255, 255));
		btn100.setBackground(new Color(205, 48, 41));
		btn100.setBorderPainted(false);
		btn100.setBorder(null);
		btn100.setSelectedIcon(null);
		btn100.setBounds(450, 300, 85, 40);
		add(btn100);
		
		btn50 = new JButton("50 \u20AC");
		btn50.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn50.setForeground(new Color(255, 255, 255));
		btn50.setBackground(new Color(205, 48, 41));
		btn50.setBorderPainted(false);
		btn50.setBorder(null);
		btn50.setSelectedIcon(null);
		btn50.setBounds(450, 350, 83, 40);
		add(btn50);
		
		btn20 = new JButton("20 \u20AC");
		btn20.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn20.setForeground(new Color(255, 255, 255));
		btn20.setBackground(new Color(205, 48, 41));
		btn20.setBorderPainted(false);
		btn20.setBorder(null);
		btn20.setSelectedIcon(null);
		btn20.setBounds(545, 250, 85, 40);
		add(btn20);
		
		btn10 = new JButton("10 \u20AC");
		btn10.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn10.setForeground(new Color(255, 255, 255));
		btn10.setBackground(new Color(205, 48, 41));
		btn10.setBorderPainted(false);
		btn10.setBorder(null);
		btn10.setSelectedIcon(null);
		btn10.setBounds(545, 300, 85, 40);
		add(btn10);
		
		btn5 = new JButton("5 \u20AC");
		btn5.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn5.setForeground(new Color(255, 255, 255));
		btn5.setBackground(new Color(205, 48, 41));
		btn5.setBorderPainted(false);
		btn5.setBorder(null);
		btn5.setSelectedIcon(null);
		btn5.setBounds(545, 350, 85, 40);
		add(btn5);
		
		btn2 = new JButton("2 \u20AC");
		btn2.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setBackground(new Color(205, 48, 41));
		btn2.setBorderPainted(false);
		btn2.setBorder(null);
		btn2.setSelectedIcon(null);
		btn2.setBounds(658, 250, 85, 40);
		add(btn2);
		
		btn1 = new JButton("1 \u20AC");
		btn1.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setBackground(new Color(205, 48, 41));
		btn1.setBorderPainted(false);
		btn1.setBorder(null);
		btn1.setSelectedIcon(null);
		btn1.setBounds(658, 300, 85, 40);
		add(btn1);
		
		btn050 = new JButton("0,50 \u20AC");
		btn050.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn050.setForeground(new Color(255, 255, 255));
		btn050.setBackground(new Color(205, 48, 41));
		btn050.setBorderPainted(false);
		btn050.setBorder(null);
		btn050.setSelectedIcon(null);
		btn050.setBounds(658, 350, 85, 40);
		add(btn050);
		
		btn020 = new JButton("0,20 \u20AC");
		btn020.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn020.setForeground(new Color(255, 255, 255));
		btn020.setBackground(new Color(205, 48, 41));
		btn020.setBorderPainted(false);
		btn020.setBorder(null);
		btn020.setSelectedIcon(null);
		btn020.setBounds(753, 250, 85, 40);
		add(btn020);
		
		btn010 = new JButton("0,10 \u20AC");
		btn010.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn010.setForeground(new Color(255, 255, 255));
		btn010.setBackground(new Color(205, 48, 41));
		btn010.setBorderPainted(false);
		btn010.setBorder(null);
		btn010.setSelectedIcon(null);
		btn010.setBounds(753, 300, 85, 40);
		add(btn010);
		
		btn005 = new JButton("0,05 \u20AC");
		btn005.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn005.setForeground(new Color(255, 255, 255));
		btn005.setBackground(new Color(205, 48, 41));
		btn005.setBorderPainted(false);
		btn005.setBorder(null);
		btn005.setSelectedIcon(null);
		btn005.setBounds(753, 350, 85, 40);
		add(btn005);
		
		btn002 = new JButton("0,02 \u20AC");
		btn002.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn002.setForeground(new Color(255, 255, 255));
		btn002.setBackground(new Color(205, 48, 41));
		btn002.setBorderPainted(false);
		btn002.setBorder(null);
		btn002.setSelectedIcon(null);
		btn002.setBounds(848, 250, 85, 40);
		add(btn002);
		
		btn001 = new JButton("0,01 \u20AC");
		btn001.setFont(new Font("Agency FB", Font.BOLD, 18));
		btn001.setForeground(new Color(255, 255, 255));
		btn001.setBackground(new Color(205, 48, 41));
		btn001.setBorderPainted(false);
		btn001.setBorder(null);
		btn001.setSelectedIcon(null);
		btn001.setBounds(848, 300, 85, 40);
		add(btn001);
		
		// separador
		btnSeparador = new JButton("");
		btnSeparador.setEnabled(false);
		btnSeparador.setBounds(640, 250, 5, 140);
		add(btnSeparador);
		
		/*
		 * Botones
		 */
		
		btnCancelarPago = new JButton("Devolver");
		btnCancelarPago.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnCancelarPago.setForeground(new Color(255, 255, 255));
		btnCancelarPago.setBackground(new Color(205, 48, 41));
		btnCancelarPago.setBorderPainted(false);
		btnCancelarPago.setBorder(null);
		btnCancelarPago.setSelectedIcon(null);
		btnCancelarPago.setBounds(45, 470, 120, 30);
		add(btnCancelarPago);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setBackground(new Color(205, 48, 41));
		btnAtras.setBorderPainted(false);
		btnAtras.setBorder(null);
		btnAtras.setSelectedIcon(null);
		btnAtras.setBounds(45, 607, 100, 25);
		add(btnAtras);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(205, 48, 41));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBorder(null);
		btnCancelar.setSelectedIcon(null);
		btnCancelar.setBounds(45, 607, 100, 25);
		add(btnCancelar);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnContinuar.setForeground(new Color(255, 255, 255));
		btnContinuar.setBackground(new Color(205, 48, 41));
		btnContinuar.setBorderPainted(false);
		btnContinuar.setBorder(null);
		btnContinuar.setSelectedIcon(null);
		btnContinuar.setBounds(846, 642, 100, 25);
		// no esta habilitado hasta que insertas suficiente dinero
		btnContinuar.setEnabled(false);
		btnContinuar.setVisible(false);
		add(btnContinuar);
		
		// Panel donde se muestran los datos del pago al instante:
		// total a pagar, dinero introducido y dinero restante	
		panelTotales = new JPanel();
		panelTotales.setBackground(Color.GRAY);
		panelTotales.setBounds(45, 250, 290, 200);
		add(panelTotales);
		panelTotales.setLayout(null);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotal.setBounds(20, 20, 100, 32);
		panelTotales.add(lblTotal);
		
		lblDineroTotal = new JLabel("0 \u20AC");
		lblDineroTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDineroTotal.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDineroTotal.setBounds(130, 20, 120, 32);
		panelTotales.add(lblDineroTotal);
		
		lblIntroducido = new JLabel("Introducido");
		lblIntroducido.setFont(new Font("Arial", Font.PLAIN, 20));
		lblIntroducido.setBounds(20, 75, 100, 32);
		panelTotales.add(lblIntroducido);
		
		lblDineroIntro = new JLabel("0 \u20AC");
		lblDineroIntro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDineroIntro.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDineroIntro.setBounds(130, 75, 120, 32);
		panelTotales.add(lblDineroIntro);
		
		lblRestante = new JLabel("Restante");
		lblRestante.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRestante.setBounds(20, 135, 101, 32);
		panelTotales.add(lblRestante);
		
		lblDineroRest = new JLabel("0 \u20AC");
		lblDineroRest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDineroRest.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDineroRest.setBounds(130, 135, 121, 32);
		panelTotales.add(lblDineroRest);

	}

}
