package Grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Menu {

	public JFrame Menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();

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
	public Menu() {
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

		JButton btnGraficadora = new JButton("graficadora");
		btnGraficadora.setBounds(10, 67, 126, 44);

		panel.add(btnGraficadora);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 560, 21);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Metodos de Raices");
		menuBar.add(mnNewMenu);

		JButton btnBiseccion = new JButton("Biseccion");
		btnBiseccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Biseccion B = new Biseccion();
				B.main(null);
				Menu.setVisible(false);

			}
		});
		mnNewMenu.add(btnBiseccion);

		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);

		JMenu mnNewMenu_3 = new JMenu("New menu");
		menuBar.add(mnNewMenu_3);

		btnGraficadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Grafica G = new Grafica();
				G.main(null);

				Menu.setVisible(false);

			}
		});
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
