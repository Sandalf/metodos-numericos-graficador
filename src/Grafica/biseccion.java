package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Biseccion {

	private JFrame frame;
	private JTable table;
	private JTextField exp;
	private JTextField x1;
	private JTextField x2;
	private JTextField Iteraciones;
	private JTextField Error;
	private JTextField Raiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Biseccion window = new Biseccion();
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
	public Biseccion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 578, 203);
		panel.add(scrollPane);

		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, },
				new String[] { "I", "x1", "x2", "x2-x1", "xm", "F(x1)", "F(xm)" }));
		scrollPane.setViewportView(table);

		exp = new JTextField();
		exp.setBounds(10, 31, 112, 20);
		panel.add(exp);
		exp.setColumns(10);

		x1 = new JTextField();
		x1.setBounds(147, 31, 45, 20);
		panel.add(x1);
		x1.setColumns(10);

		x2 = new JTextField();
		x2.setBounds(230, 31, 45, 20);
		panel.add(x2);
		x2.setColumns(10);

		Iteraciones = new JTextField();
		Iteraciones.setBounds(321, 31, 95, 20);
		panel.add(Iteraciones);
		Iteraciones.setColumns(10);

		Error = new JTextField();
		Error.setBounds(459, 31, 95, 20);
		panel.add(Error);
		Error.setColumns(10);

		JLabel lblFuncion = new JLabel("Funcion");
		lblFuncion.setBounds(37, 11, 46, 14);
		panel.add(lblFuncion);

		JLabel lblX = new JLabel("X1");
		lblX.setBounds(159, 11, 31, 14);
		panel.add(lblX);

		JLabel lblX_1 = new JLabel("X2");
		lblX_1.setBounds(244, 11, 31, 14);
		panel.add(lblX_1);

		JLabel lblIteraciones = new JLabel("Iteraciones");
		lblIteraciones.setBounds(335, 11, 81, 14);
		panel.add(lblIteraciones);

		JLabel lblError = new JLabel("Error");
		lblError.setBounds(481, 11, 46, 14);
		panel.add(lblError);

		JLabel lblRaiz = new JLabel("Raiz");
		lblRaiz.setBounds(37, 124, 61, 14);
		panel.add(lblRaiz);

		JButton EncontrarSolucion = new JButton("Encontrar Solucion");
		EncontrarSolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String F = exp.getText();
				double X1 = Double.parseDouble(x1.getText());
				double X2 = Double.parseDouble(x2.getText());
				int Lim = Integer.parseInt(Iteraciones.getText());
				double E = Double.parseDouble(Error.getText());

				Projectofinal.Biseccion B = new Projectofinal.Biseccion(F, X1, X2, E, Lim);
				try {
					B.Inciar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Object[][] Resultados = B.getResultados();

				table.setModel(new DefaultTableModel(Resultados

						, new String[] { "I", "x1", "x2", "x2-x1", "Xm", "F(x1)", "F(xm)" }));

				Raiz.setText(Double.toString(B.getRaiz()));

			}
		});

		EncontrarSolucion.setBounds(10, 62, 125, 28);
		panel.add(EncontrarSolucion);

		JButton btnNewButton = new JButton("Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu m = new Menu();

				m.main(null);
				frame.dispose();

			}
		});
		btnNewButton.setBounds(448, 134, 125, 39);
		panel.add(btnNewButton);

		Raiz = new JTextField();
		Raiz.setBackground(Color.GRAY);
		Raiz.setBounds(10, 149, 125, 20);
		panel.add(Raiz);
		Raiz.setColumns(10);

	}
}
