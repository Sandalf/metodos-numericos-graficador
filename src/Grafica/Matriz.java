package Grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class Matriz {

	private JFrame frame;
	private JComboBox renglonesComboBox;
	private JComboBox columnasComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Matriz window = new Matriz();
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
	public Matriz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnCrearMatriz = new JButton("Crear matriz");
		btnCrearMatriz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarMatriz editarMatriz = new EditarMatriz();
				editarMatriz.setVisible(true);
			}
		});
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		frame.getContentPane().add(lblDimensiones);
		
		renglonesComboBox = new JComboBox();
		columnasComboBox = new JComboBox();
		
		// AGREGAR ELEMENTOS DE COMBOBOX
		for(int i = 1; i <= 100; i++) {
			renglonesComboBox.addItem(new Integer(i));
			columnasComboBox.addItem(new Integer(i));
		}
		
		frame.getContentPane().add(renglonesComboBox);
		
		JLabel lblX = new JLabel("X");
		frame.getContentPane().add(lblX);
		
		frame.getContentPane().add(columnasComboBox);
		frame.getContentPane().add(btnCrearMatriz);
	}

}
