package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Pago extends JPanel {
	
	public JPanel panelTotales;
	public JButton btnContinuar, btnCancelar, btnCancelarPago, btnAtras, btnSeparador;
	public JLabel lblIcono, lblTotal, lblDineroTotal, lblIntroducido, lblDineroIntro, lblRestante, lblDineroRest;
	public ButtonGroup botonesDinero = new ButtonGroup();
	public float[] monedasBilletes = {200, 100, 50, 20, 10, 5, 2, 1, 0.50f, 0.20f, 0.10f, 0.05f, 0.02f, 0.01f };
	public JButton[] botonesMonedasBilletes = new JButton[14];
	
	public Pago() {
		
		// configuracion del panel
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1024, 720);
		
		// creacion de componentes
		crearLogo();
		crearBotonesGenerales();
		crearPanelDinero();
		crearBotonesPago();

	}
	
	/**
	 * Crea el logo y lo añade al panel
	 */
	public void crearLogo() {
		lblIcono = new JLabel("");
//		lblIcono.setIcon(new ImageIcon(Bienvenida.class.getResource("/img/image2.png")));
		lblIcono.setOpaque(true);
		lblIcono.setBorder(null);
		lblIcono.setForeground(new Color(0, 0, 0));
		lblIcono.setBackground(new Color(255, 255, 255));
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(120, 0, 1024, 210);
		add(lblIcono);
	}
	
	/**
	 * Crea los botones generales de la interfaz y los añade al panel
	 */
	public void crearBotonesGenerales() {
		
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
	}
	
	/**
	 *  Crea el panel donde se muestran los datos al dinero:
	 *  total a pagar, dinero introducido y dinero restante	
	 */
	public void crearPanelDinero() {
	
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
	
	/**
	 * Crea los botones de monedas y billetes y los añade al panel
	 */
	public void crearBotonesPago() {
		
		for (int i = 0; i < monedasBilletes.length; i++ ) {
			if (monedasBilletes[i] > 0.5) {
				botonesMonedasBilletes[i] = new JButton((int)monedasBilletes[i] + " \u20AC");
			} else {
				botonesMonedasBilletes[i] = new JButton(monedasBilletes[i] + " \u20AC");
			}
			botonesMonedasBilletes[i].setName(Float.toString(monedasBilletes[i]));
			botonesMonedasBilletes[i].setFont(new Font("Agency FB", Font.BOLD, 18));
			botonesMonedasBilletes[i].setForeground(new Color(255, 255, 255));
			botonesMonedasBilletes[i].setBackground(new Color(205, 48, 41));
			botonesMonedasBilletes[i].setBorderPainted(false);
			botonesMonedasBilletes[i].setBorder(null);
			botonesMonedasBilletes[i].setSelectedIcon(null);
			botonesDinero.add(botonesMonedasBilletes[i]);
			add(botonesMonedasBilletes[i]);
		}
		
		botonesMonedasBilletes[13].setBounds(450, 250, 85, 40);
		botonesMonedasBilletes[12].setBounds(450, 300, 85, 40);
		botonesMonedasBilletes[11].setBounds(450, 350, 83, 40);
		botonesMonedasBilletes[10].setBounds(545, 250, 85, 40);
		botonesMonedasBilletes[9].setBounds(545, 300, 85, 40);
		botonesMonedasBilletes[8].setBounds(545, 350, 85, 40);
		botonesMonedasBilletes[7].setBounds(658, 250, 85, 40);
		botonesMonedasBilletes[6].setBounds(658, 300, 85, 40);
		botonesMonedasBilletes[5].setBounds(658, 350, 85, 40);
		botonesMonedasBilletes[4].setBounds(753, 250, 85, 40);
		botonesMonedasBilletes[3].setBounds(753, 300, 85, 40);
		botonesMonedasBilletes[2].setBounds(753, 350, 85, 40);
		botonesMonedasBilletes[1].setBounds(848, 250, 85, 40);
		botonesMonedasBilletes[0].setBounds(848, 300, 85, 40);
		
	}
	
	/**
	 * Actualiza el precio que se muestra
	 * 
	 * @param precio Nuevo valor del precio
	 */
	public void actualizarPrecio(float precio) {
		lblDineroTotal.setText(Float.toString(precio) + " €");
	}
	
	/**
	 * Actualiza el dinero introducido que se muestra
	 * 
	 * @param dineroIntroducido Nuevo valor del dinero introducido
	 */
	public void actualizarDineroIntroducido(float dineroIntroducido) {
		lblDineroIntro.setText(Float.toString(dineroIntroducido) + " €");
	}
	
	/**
	 * Actualiza el dinero restante que se muestra
	 * 
	 * @param dineroRestante Nuevo valor del dinero restante
	 */
	public void actualizarDineroRestante(float dineroRestante) {
		lblDineroRest.setText(Float.toString(dineroRestante) + " €");
	}
	
	/**
	 * Deshabilita/habilita el boton continuar
	 * 
	 * @param estado Nuevo estado para el boton
	 */
	public void estadoBotonContinuar(boolean estado) {
		btnContinuar.setVisible(estado);
		btnContinuar.setEnabled(estado);
	}
	
	/**
	 * Deshabilita/habilita los botones de las monedas y billetes
	 * 
	 * @param estado Nuevo estado para el boton
	 */
	public void estadoBotonesPago(boolean estado) {
		Enumeration<AbstractButton> enumeration = botonesDinero.getElements();
		while (enumeration.hasMoreElements()) {
		    enumeration.nextElement().setEnabled(estado);
		}
	}

}
