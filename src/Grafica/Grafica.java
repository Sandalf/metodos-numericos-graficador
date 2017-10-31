package Grafica;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Projectofinal.Funcion;
import Projectofinal.Graficadora;

public class Grafica {

	private JFrame frame;
	private JTextField desde;
	private JTextField hasta;

	private JTextField Exp;

	Graficadora grafica = new Graficadora("mi grafica", "x", "y");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafica window = new Grafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Grafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 585);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel Grafica = new JPanel();
		Grafica.setBounds(0, 36, 831, 33);
		frame.getContentPane().add(Grafica);
		Grafica.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Exp = new JTextField();
		Grafica.add(Exp);
		Exp.setColumns(10);

		JLabel lblNewLabel = new JLabel("Funcion");
		Grafica.add(lblNewLabel);

		JButton Graficar = new JButton("Graficar");
		Graficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String def = Exp.getText();
				double x0 = Double.parseDouble(desde.getText());
				double xn = Double.parseDouble(hasta.getText());
				double d = 0.1;
				Funcion f = new Funcion(def);
				double[] x = f.rango(x0, xn, d);
				double[] y;
				try {
					y = f.eval(x);
					grafica.crearGrafica(def, x, y);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		Grafica.add(Graficar);

		desde = new JTextField();
		Grafica.add(desde);
		desde.setColumns(10);

		JLabel lblX = new JLabel("x1");
		Grafica.add(lblX);

		hasta = new JTextField();
		Grafica.add(hasta);
		hasta.setColumns(10);

		JLabel lblXn = new JLabel("x2");
		Grafica.add(lblXn);

		JPanel panel_2 = grafica.obtieneGrafica();
		panel_2.setBounds(10, 80, 821, 455);
		frame.getContentPane().add(panel_2);

		JButton MenuPrincipal = new JButton("Menu Principal");
		MenuPrincipal.setBounds(10, 0, 147, 23);
		frame.getContentPane().add(MenuPrincipal);
		MenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Menu m = new Menu();
				//m.main(null);
				frame.setVisible(false);

			}
		});

	}
}
