package Projectofinal;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Projectofinal.funcion;
import Projectofinal.graficadora;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;

public class grafica {

	private JFrame frame;
	private JTextField desde;
	private JTextField hasta;
	private JTextField intervalo;
	private JTextField Exp;

	
	graficadora grafica = new graficadora("mi grafica","x","y");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					grafica window = new grafica();
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
	public grafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);
		
		Button button = new Button("metodo de biseccion");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		popupMenu.add(button);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 36, 831, 33);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Exp = new JTextField();
		panel_1.add(Exp);
		Exp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Funcion");
		panel_1.add(lblNewLabel);
		
		JButton Graficar = new JButton("Graficar");
		Graficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					String def=Exp.getText();
					double x0=Double.parseDouble(desde.getText());
					double xn=Double.parseDouble(hasta.getText());
					double d= Double.parseDouble(intervalo.getText());
					funcion f= new funcion(def);
					double [] x = f.rango(x0, xn, d);
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
		panel_1.add(Graficar);
		
		desde = new JTextField();
		panel_1.add(desde);
		desde.setColumns(10);
		
		JLabel lblX = new JLabel("x0");
		panel_1.add(lblX);
		
		hasta = new JTextField();
		panel_1.add(hasta);
		hasta.setColumns(10);
		
		JLabel lblXn = new JLabel("xn");
		panel_1.add(lblXn);
		
		intervalo = new JTextField();
		panel_1.add(intervalo);
		intervalo.setColumns(10);
		
		JLabel inter = new JLabel("intervalo");
		panel_1.add(inter);
		
		
		
		
		
	
		
		
		
		JPanel panel_2 = grafica.obtieneGrafica();
		panel_2.setBounds(10, 80, 821, 455);
		frame.getContentPane().add(panel_2);
		
		
		
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
