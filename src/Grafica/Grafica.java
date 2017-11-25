package Grafica;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
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
	private String Funcion;

	Graficadora grafica = new Graficadora(Funcion, "x", "y");

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

	




	public Grafica() {
		initialize();
		
	}





	public Grafica(String text) throws Exception {
		double x0 ;
		double xn ;
		Funcion=text;
		initialize();
		String def = Funcion;
		switch(Funcion) 
		{
		case "sin(x)":
		 x0 = (double)-10;
		 xn = (double)10;
		break;
		case "cos(x)":
			 x0 = (double)-10;
			 xn = (double)10;
			break;
		default:
			 x0 = (double)-100;
			 xn = (double)100;
			 break;
		}
		double d = 0.1;
		Funcion f = new Funcion(def);
		Funcion X = new Funcion("0");
		Funcion Y = new Funcion("0");
		double[] x = f.rango(x0, xn, d);
		double[] y;
		
		
	
		
		
		try {
			y = f.eval(x);
			grafica.crearGrafica(def, x, y);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setVisible(true);
		
		
	}



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
		Exp.setVisible(false);

		

		JButton Graficar = new JButton("Graficar");
		Graficar.setVisible(false);
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
		desde.setVisible(false);

		
		hasta = new JTextField();
		Grafica.add(hasta);
		hasta.setColumns(10);
		hasta.setVisible(false);

		

		JPanel panel_2 = grafica.obtieneGrafica();
		panel_2.setBounds(10, 80, 821, 455);
		frame.getContentPane().add(panel_2);

		
		

	}
}
