package Grafica;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.DerivacionDiferenciasFinitas;
import Projectofinal.DerivacionEnum;
import Projectofinal.InterpolacioNewton;
import Projectofinal.InterpolacionEnum;
import Projectofinal.Interpolacion_Lagrange;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Derivacion extends JFrame {

	private JPanel contentPane;
	private JTextField x_TextField;
	private JTextField y_TextField;
	private JButton btnResolver;
	private JTable puntosTable;
	private JTable diferenciasTable;
	private ArrayList<Double[]> puntos;
	private Double[][] tableModel;
	private JTextField xValue_textField;
	private JTextField answer_TextField;
	private Double diferenciaEntrePuntos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Derivacion frame = new Derivacion(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Derivacion(DerivacionEnum tipoMetodo) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Menu_principal.main(null);
				setVisible(false);
				dispose();
			}
		});
		setBounds(100, 100, 450, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// TITULO DE VENTANA
		if(tipoMetodo == DerivacionEnum.DiferenciasFinitas) {
			setTitle("Diferencias Finitas");
		}

		// INICIALIZAR ARREGLO DE PUNTOS
		puntos = new ArrayList<Double[]>();

		// CABECEROS
		String[] cabeceroPuntosTable = { "X", "Y" };
		String[] cabeceroDiferenciasTable = { "Diferencias" };

		x_TextField = new JTextField();
		// VALIDAR QUE SE INGRESEN SOLO NUMEROS
		x_TextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		x_TextField.setBounds(101, 6, 43, 26);
		contentPane.add(x_TextField);
		x_TextField.setColumns(10);

		// AGREGAR PUNTOS
		JButton btnAgregarPunto = new JButton("Agregar Punto");
		btnAgregarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// VALIDAR QUE LAS COORDENADAS NO ESTEN VACIAS
				if (x_TextField.getText().isEmpty() || y_TextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getContentPane(), "No debe haber coordenadas vacias.");
				} else {

					Double[] punto = new Double[2];
					punto[0] = Double.parseDouble(x_TextField.getText());
					punto[1] = Double.parseDouble(y_TextField.getText());

					/*
					 * CALCULAR DIFERENCIA ENTRE PUNTOS
					 */
					if (puntos.size() == 2) {
						diferenciaEntrePuntos = puntos.get(0)[0] - puntos.get(1)[0];
					}

					/*
					 * VALIDAR QUE PUNTO INGRESADO COINCIDA CON LA DIFERENCIA ENTRE LOS PUNTOS
					 * ANTERIORES
					 */
					if (puntos.size() >= 2 && diferenciaEntrePuntos != puntos.get(puntos.size() - 1)[0] - punto[0]) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Las coordenadas X deben estar a la misma distancia entre si.");
					} else {
						puntos.add(punto);

						/*
						 * EL ARRAYLIST SE DEBE CONVETIR A ARREGLO PARA SER ACEPTADO COMO MODELO DE LA
						 * TABLA
						 */
						tableModel = new Double[puntos.size()][2];

						for (int i = 0; i < puntos.size(); i++) {
							tableModel[i] = puntos.get(i);
						}

						puntosTable.setModel(new DefaultTableModel(tableModel, cabeceroPuntosTable));
					}
				}
			}
		});
		btnAgregarPunto.setBounds(317, 6, 117, 29);
		contentPane.add(btnAgregarPunto);

		y_TextField = new JTextField();
		// VALIDAR QUE SE INGRESEN SOLO NUMEROS
		y_TextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		y_TextField.setBounds(188, 6, 43, 26);
		contentPane.add(y_TextField);
		y_TextField.setColumns(10);

		JLabel lblX = new JLabel("X:");
		lblX.setBounds(76, 11, 21, 16);
		contentPane.add(lblX);

		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(164, 11, 21, 16);
		contentPane.add(lblY);

		/* RESOLVER */
		btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntos.size() < 2) {
					JOptionPane.showMessageDialog(getContentPane(), "No ha agregado suficientes puntos.");
				} else {
					System.out.println("Resolviendo");
					if (tipoMetodo == DerivacionEnum.DiferenciasFinitas) {
						DerivacionDiferenciasFinitas derivacionDiferenciasFinitas = new DerivacionDiferenciasFinitas(tableModel);
						Double soulucion = derivacionDiferenciasFinitas.solve(Double.parseDouble(xValue_textField.getText()));
						Double[][] tablaDiferencias = derivacionDiferenciasFinitas.getTablaDiferencias();
						String[] cabeceros = new String[tablaDiferencias[0].length];

						for (int i = 0; i < tablaDiferencias[0].length; i++) {
							if (i == 0) {
								cabeceros[i] = "Δy";
							} else {
								cabeceros[i] = "Δy" + (i + 1);
							}
						}

						answer_TextField.setText(soulucion.toString());
						diferenciasTable.setModel(new DefaultTableModel(tablaDiferencias, cabeceros));
					}
				}
			}
		});
		btnResolver.setBounds(317, 40, 117, 29);
		contentPane.add(btnResolver);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 135, 100, 200);
		contentPane.add(scrollPane);

		puntosTable = new JTable();
		puntosTable.setEnabled(false);
		puntosTable.setBackground(Color.LIGHT_GRAY);
		puntosTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		puntosTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroPuntosTable));
		scrollPane.setViewportView(puntosTable);

		JScrollPane diferenciasScrollPane = new JScrollPane();
		diferenciasScrollPane.setBounds(129, 135, 305, 200);
		contentPane.add(diferenciasScrollPane);

		diferenciasTable = new JTable();
		diferenciasTable.setEnabled(false);
		diferenciasTable.setBackground(Color.LIGHT_GRAY);
		diferenciasTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		diferenciasTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroDiferenciasTable));
		diferenciasScrollPane.setViewportView(diferenciasTable);

		JLabel lblValorDeX = new JLabel("Valor de X:");
		lblValorDeX.setBounds(19, 45, 79, 16);
		contentPane.add(lblValorDeX);

		xValue_textField = new JTextField();
		xValue_textField.setBounds(101, 40, 130, 26);
		contentPane.add(xValue_textField);
		xValue_textField.setColumns(10);

		JLabel lblValorDeY = new JLabel("Solucion:");
		lblValorDeY.setBounds(19, 82, 79, 16);
		contentPane.add(lblValorDeY);

		answer_TextField = new JTextField();
		answer_TextField.setEditable(false);
		answer_TextField.setColumns(10);
		answer_TextField.setBounds(101, 77, 130, 26);
		contentPane.add(answer_TextField);

		/* LIMPIAR */
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x_TextField.setText("0");
				y_TextField.setText("0");
				xValue_textField.setText("0");
				puntosTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroPuntosTable));
				diferenciasTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroDiferenciasTable));
				answer_TextField.setText("");
				puntos = new ArrayList<Double[]>();
			}
		});
		btnLimpiar.setBounds(317, 77, 117, 29);
		contentPane.add(btnLimpiar);

		// INICIALIZAR VALORES
		x_TextField.setText("0");
		y_TextField.setText("0");
		xValue_textField.setText("0");
	}
}
