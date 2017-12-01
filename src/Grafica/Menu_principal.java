package Grafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Projectofinal.Biseccion;
import Projectofinal.DerivacionEnum;
import Projectofinal.Funcion;
import Projectofinal.IntegracionEnum;
import Projectofinal.InterpolacionEnum;
import Projectofinal.MetodoMatrizEnum;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import java.awt.List;
import java.awt.Button;
import javax.swing.SwingConstants;

import Grafica.Falsa_posicionI;
import Grafica.SecanteI;
import Grafica.biseccion;

public class Menu_principal {

	public JFrame Menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal window = new Menu_principal();

					window.Menu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu_principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Menu = new JFrame();
		Menu.setBounds(100, 100, 566, 461);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		Menu.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 560, 21);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Metodos de Raices");
		menuBar.add(mnNewMenu);
		
		JMenu mnMatrices = new JMenu("Matrices");
		menuBar.add(mnMatrices);
		
		JButton btnGauss = new JButton("Gauss");
		
		
		btnGauss.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(btnGauss);
		
		JButton btnGaussJordan = new JButton("Gauss Jordan");
		
		btnGaussJordan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS_JORDAN);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(btnGaussJordan);
		
		JButton btnMontante = new JButton("Montante");
		btnMontante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS.MONTANTE);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(btnMontante);
		
		JButton btnCramer = new JButton("Cramer");
		btnCramer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.CRAMER);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(btnCramer);
		
		JButton btnJacobi = new JButton("Jacobi");
		btnJacobi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.JACOBI);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(btnJacobi);

		JButton btnBiseccion = new JButton("Biseccion                          ");
		btnBiseccion.setAlignmentX(1);
		btnBiseccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				biseccion B = new biseccion();
				B.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(btnBiseccion);

		JButton btnNewButton = new JButton("Falsa Posicion                 ");
		btnNewButton.setToolTipText("");
		btnNewButton.setAlignmentX(1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Falsa_posicionI Falsa = new Falsa_posicionI();
				Falsa.main(null);
				Menu.setVisible(false);

			}
		});
		mnNewMenu.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Secante                             ");
		btnNewButton_1.setAlignmentX(1);
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecanteI S = new SecanteI();
				S.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(btnNewButton_1);
		
		JButton btnAproximaciones = new JButton("Aproximaciones              ");
		btnAproximaciones.setAlignmentX(1);
		btnAproximaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Aproximaciones_Sucesivas S= new Aproximaciones_Sucesivas();
				S.main(null);
				Menu.setVisible(false);
				
				
			}
		});
		mnNewMenu.add(btnAproximaciones);
		

		JButton btnNewtonraphson = new JButton("Newton_Raphson            ");
		btnNewtonraphson.setAlignmentX(1);
		btnNewtonraphson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Newton_Raphson N= new Newton_Raphson();
				
				N.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(btnNewtonraphson);
		
		JButton btnNewtonsegundoorden = new JButton("Newton_SegundoOrden");
		btnNewtonsegundoorden.setAlignmentX(1);
		mnNewMenu.add(btnNewtonsegundoorden);
		
		btnNewtonsegundoorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Newton_SegundoOrdenI N =  new Newton_SegundoOrdenI();
			      N.main(null);	
				Menu.setVisible(false);
			}
		});
		
		
		
		
		
		
		JMenu mnInterpolacin = new JMenu("Interpolación");
		menuBar.add(mnInterpolacin);
		
		JButton btnNewton = new JButton("Newton");
		btnNewton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interpolacion interpolacion = new Interpolacion(InterpolacionEnum.InterpolacionNewton);
				interpolacion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnInterpolacin.add(btnNewton);
		
		JButton btnLagrange = new JButton("Lagrange");
		btnLagrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		Interpolacion Lagrange = new Interpolacion(InterpolacionEnum.Interpolacion_Lagrange);
		Lagrange.setVisible(true);
		Menu.setVisible(false);
		
			}
		});
		mnInterpolacin.add(btnLagrange);
		
		JButton btnMinimoscuadrados = new JButton("MinimosCuadrados");
		btnMinimoscuadrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	       Interpolacion Cuadrados= new Interpolacion(InterpolacionEnum.Interpolacion_CuadradrosMinimos);
	       Cuadrados.setVisible(true);
	        Menu.setVisible(false);
	
			}
		});
		mnInterpolacin.add(btnMinimoscuadrados);
		
		JMenu mnDerivacin = new JMenu("Derivación");
		menuBar.add(mnDerivacin);
		
		JButton btnDiferenciasFinitas = new JButton("Diferencias Finitas");
		btnDiferenciasFinitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Derivacion derivacion = new Derivacion(DerivacionEnum.DiferenciasFinitas);
				derivacion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnDerivacin.add(btnDiferenciasFinitas);
		
		JMenu mnIntegracin = new JMenu("Integración");
		menuBar.add(mnIntegracin);
		
		JButton btnTrapecio = new JButton("Trapecio");
		btnTrapecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integracion integracion = new Integracion(IntegracionEnum.Trapecio);
				integracion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnIntegracin.add(btnTrapecio);

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