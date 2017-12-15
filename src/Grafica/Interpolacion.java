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

import Projectofinal.Graficadora;
import Projectofinal.InterpolacioNewton;
import Projectofinal.InterpolacionEnum;
import Projectofinal.Interpolacion_CuadradosMinimos;
import Projectofinal.Interpolacion_Lagrange;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Interpolacion extends JFrame {

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
	private Graficadora GraficaPuntos;
	private JComboBox<Integer> Orden;
    private JPanel Grafica;
    private JTable table;
    private JTable SumatoriasTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interpolacion frame = new Interpolacion(null);
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
	public Interpolacion(InterpolacionEnum tipoMetodo) {
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
		setBounds(100, 100, 625, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// combo box para el orden de la funcion en el metodo de minimos cuadrados
		Orden = new JComboBox<Integer>();
		contentPane.add(Orden);
		Orden.setBounds(300, 6, 40, 27);
		JLabel OrdenF = new JLabel("Orden");
		contentPane.add(OrdenF);
		OrdenF.setBounds(260, 6, 40, 27);
		for (int i = 1; i <= 20; i++) {
			Orden.addItem(i);
		}
		if (tipoMetodo != InterpolacionEnum.Interpolacion_CuadradrosMinimos) {
			Orden.setVisible(false);
			OrdenF.setVisible(false);
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
				try {
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

						// DEFINIR METODO POR EL QUE SE VA A RESOLVER

						switch (tipoMetodo) {

						case InterpolacionNewton:
							// VALIDAR QUE PUNTO INGRESADO COINCIDA CON LA DIFERENCIa ENTRE LOS PUNTOS
							// ANTERIORES
							if (puntos.size() >= 2
									&& diferenciaEntrePuntos != puntos.get(puntos.size() - 1)[0] - punto[0]) {

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
							break;
						case Interpolacion_Lagrange:
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
							break;
						case Interpolacion_CuadradrosMinimos:
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
                           	break;

						}

					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar agregar punto.");
					System.out.println(error.getMessage());
				}
			}
		}

		);

		btnAgregarPunto.setBounds(400, 6, 150, 29);
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
				try {
					if (puntos.size() < 2) {
						JOptionPane.showMessageDialog(getContentPane(), "No ha agregado suficientes puntos.");
					} else {
						if (tipoMetodo == InterpolacionEnum.InterpolacionNewton) {
							InterpolacioNewton interpolacionNewton = new InterpolacioNewton(tableModel);
							Double soulucion = interpolacionNewton
									.solve(Double.parseDouble(xValue_textField.getText()));
							Double[][] tablaDiferencias = interpolacionNewton.getTablaDiferencias();
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
						if (tipoMetodo == InterpolacionEnum.Interpolacion_Lagrange) {
							Interpolacion_Lagrange InterpolacionLagrange = new Interpolacion_Lagrange(tableModel);
							Double soulucion = InterpolacionLagrange
									.solve(Double.parseDouble(xValue_textField.getText()));
							answer_TextField.setText(soulucion.toString());
						} else if (tipoMetodo == InterpolacionEnum.Interpolacion_CuadradrosMinimos) {
							Integer OrdenFuncion = (Integer) Orden.getSelectedItem();
							Interpolacion_CuadradosMinimos Cuadrados = new Interpolacion_CuadradosMinimos(tableModel,
									OrdenFuncion);
							Double soulucion = Cuadrados.Solve(Double.parseDouble(xValue_textField.getText()));
							answer_TextField.setText(soulucion.toString());
							
						}
					}

				} catch (Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar resolver.");
					System.out.println(error.getMessage());


					
				
				}if(tipoMetodo == InterpolacionEnum.Interpolacion_Lagrange)
				{ 
					Interpolacion_Lagrange InterpolacionLagrange= new Interpolacion_Lagrange(tableModel);
					Double soulucion =InterpolacionLagrange.solve(Double.parseDouble(xValue_textField.getText()));
					answer_TextField.setText(soulucion.toString());
				}
				else if(tipoMetodo == InterpolacionEnum.Interpolacion_CuadradrosMinimos)
				{
				Integer OrdenFuncion = (Integer)Orden.getSelectedItem();
				Interpolacion_CuadradosMinimos Cuadrados;
			
					try {
						Cuadrados = new Interpolacion_CuadradosMinimos(tableModel,OrdenFuncion);

           Double soulucion =Cuadrados.Solve(Double.parseDouble(xValue_textField.getText()));
           answer_TextField.setText(soulucion.toString());
           Double[][] Resultados=Cuadrados.Resultados();
           String [] cabeseros = {"x","y","x^2","x^3","x^4","xy","x^2y"};
           Cuadrados.Resultados();
           diferenciasTable.setModel(new DefaultTableModel(Resultados, cabeseros));
        

   		   JScrollPane ScrollPane = new JScrollPane();
   		   ScrollPane.setBounds(129, 135, 399, 208);
   		   contentPane.add(ScrollPane);
   		   ScrollPane.setViewportView(diferenciasTable);
           ScrollPane.setVisible(true);
           Grafica.setVisible(false);
        
          
           
           
           
           
           
           
           
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}}
		});
		btnResolver.setBounds(400, 40, 150, 29);
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
		diferenciasScrollPane.setBounds(129, 135, 399, 208);
		contentPane.add(diferenciasScrollPane);

		diferenciasTable = new JTable();
		diferenciasTable.setEnabled(false);
		diferenciasTable.setBackground(Color.LIGHT_GRAY);
		diferenciasTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		diferenciasTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroDiferenciasTable));
		diferenciasScrollPane.setViewportView(diferenciasTable);
		if (tipoMetodo != InterpolacionEnum.InterpolacionNewton) {
			diferenciasScrollPane.setVisible(false);
		}

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
				try {
					x_TextField.setText("0");
					y_TextField.setText("0");
					xValue_textField.setText("0");
					puntosTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroPuntosTable));
					diferenciasTable.setModel(new DefaultTableModel(new Object[][] {}, cabeceroDiferenciasTable));
					answer_TextField.setText("");
					puntos = new ArrayList<Double[]>();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar limpiar.");
					System.out.println(error.getMessage());
				}
			}
		});
		btnLimpiar.setBounds(400, 77, 150, 29);
		contentPane.add(btnLimpiar);

		// INICIALIZAR VALORES
		x_TextField.setText("0");
		y_TextField.setText("0");
		xValue_textField.setText("0");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(559, 135, -424, 232);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.setBounds(510, 209, -301, 40);
		contentPane.add(table);
	}
}
