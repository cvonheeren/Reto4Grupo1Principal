package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class FinPago extends JPanel {

	public JLabel lblIcono,lblTitulo,lblTotal,lblPagado,lblADevolver,lblImprimir;
	public JButton btnImprimir,btnSeparador;
	public JTextField txtTotal,txtPagado;
	public JTextPane txtDevolver;
	
	public FinPago() {
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1024, 720);
		
		lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/image2.png"))); 
		lblIcono.setOpaque(true);
		lblIcono.setBorder(null);
		lblIcono.setForeground(new Color(0, 0, 0));
		lblIcono.setBackground(new Color(255, 255, 255));
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(120, 0, 1024, 210);
		add(lblIcono);
		
		lblTitulo = new JLabel("¡Gracias por su compra!");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Agency FB", Font.BOLD, 44));
		lblTitulo.setBounds(10, 210, 1004, 80);
		add(lblTitulo);
		
		lblTotal = new JLabel("IMPORTE TOTAL");
		lblTotal.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblTotal.setBounds(387, 300, 120, 25);
		add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Agency FB", Font.PLAIN, 18));
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(new Color(0, 0, 0));
		txtTotal.setBackground(new Color(204, 150, 150));
		txtTotal.setBorder(null);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setBounds(506, 300, 180, 25);
		txtTotal.setFocusable(false);
		add(txtTotal);
		
		lblPagado = new JLabel("PAGADO");
		lblPagado.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblPagado.setBounds(387, 350, 120, 25);
		add(lblPagado);
		
		txtPagado = new JTextField();
		txtPagado.setFont(new Font("Agency FB", Font.PLAIN, 18));
		txtPagado.setHorizontalAlignment(SwingConstants.CENTER);
		txtPagado.setForeground(new Color(0, 0, 0));
		txtPagado.setBackground(new Color(204, 150, 150));
		txtPagado.setBorder(null);
		txtPagado.setHorizontalAlignment(SwingConstants.CENTER);
		txtPagado.setBounds(506, 350, 180, 25);
		txtPagado.setFocusable(false);
		add(txtPagado);
		
		lblADevolver = new JLabel("A DEVOLVER");
		lblADevolver.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblADevolver.setBounds(387, 400, 120, 25);
		add(lblADevolver);
		
		txtDevolver = new JTextPane();
		txtDevolver.setFont(new Font("Agency FB", Font.PLAIN, 20));
		txtDevolver.setAlignmentX(SwingConstants.CENTER);
		txtDevolver.setForeground(new Color(0, 0, 0));
		txtDevolver.setBackground(new Color(204, 150, 150));
		txtDevolver.setRequestFocusEnabled(false);
		
		StyledDocument doc = txtDevolver.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		txtDevolver.setBounds(506, 400,180, 150);
		txtDevolver.setFocusable(false);
		add(txtDevolver);
		
		btnSeparador = new JButton("");
		btnSeparador.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnSeparador.setForeground(new Color(255, 255, 255));
		btnSeparador.setBackground(new Color(205, 48, 41));
		btnSeparador.setBorderPainted(false);
		btnSeparador.setBorder(null);
		btnSeparador.setSelectedIcon(null);
		btnSeparador.setEnabled(false);
		btnSeparador.setBounds(397, 570, 230, 9);
		add(btnSeparador);
		
		lblImprimir = new JLabel("Pulse 'Imprimir' para adquirir su billete");
		lblImprimir.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblImprimir.setBounds(387, 590, 250, 25);
		add(lblImprimir);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnImprimir.setForeground(new Color(255, 255, 255));
		btnImprimir.setBackground(new Color(205, 48, 41));
		btnImprimir.setBorderPainted(false);
		btnImprimir.setBorder(null);
		btnImprimir.setSelectedIcon(null);
		btnImprimir.setBounds(452, 620, 120, 40);
		add(btnImprimir);
		
	}

}
