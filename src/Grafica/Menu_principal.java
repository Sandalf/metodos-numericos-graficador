package Grafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Projectofinal.Biseccion;
import Projectofinal.Funcion;
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

		JButton btnBiseccion = new JButton("Biseccion            ");
		btnBiseccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				biseccion B = new biseccion();
				B.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(btnBiseccion);

		JButton btnNewButton = new JButton("Falsa Posicion   ");
		btnNewButton.setToolTipText("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Falsa_posicionI Falsa = new Falsa_posicionI();
				Falsa.main(null);
				Menu.setVisible(false);

			}
		});
		mnNewMenu.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Secante               ");
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecanteI S = new SecanteI();
				S.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(btnNewButton_1);
		
		JButton btnAproximaciones = new JButton("Aproximaciones");
		btnAproximaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Aproximaciones_Sucesivas S= new Aproximaciones_Sucesivas();
				S.main(null);
				Menu.setVisible(false);
				
				
			}
		});
		mnNewMenu.add(btnAproximaciones);
		
		JMenu mnInterpolacin = new JMenu("Interpolación");
		menuBar.add(mnInterpolacin);
		
		JButton btnNewton = new JButton("Newton");
		btnNewton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interpolacion interpolacion = new Interpolacion();
				interpolacion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnInterpolacin.add(btnNewton);
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