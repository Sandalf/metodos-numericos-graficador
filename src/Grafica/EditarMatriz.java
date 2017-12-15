package Grafica;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Projectofinal.Cramer;
import Projectofinal.Gauss;
import Projectofinal.GaussJordan;
import Projectofinal.GaussSeidel;
import Projectofinal.Jacobi;
import Projectofinal.MetodoMatrizEnum;
import Projectofinal.Montante;

public class EditarMatriz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLimpiar;
	private Double[][] matrizOriginal;
	private Double[][] matrizAux;
	private String bandera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarMatriz frame = new EditarMatriz();
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
	public EditarMatriz() {
	}

	public EditarMatriz(int rowsParam, int columnsParam, MetodoMatrizEnum tipoMetodo, Double errorPermisible,
			Double[][] MatrizAux) {
		// SE INCREMENTA PARA INSERTAR CABECEROS Y ESPACIADO
		// DETERMINAR SI LA MATRIZ AUXILIAR ES IGUAL O DIFERENTE DE NULL
		this.matrizAux = MatrizAux;
		if (matrizAux != null) {
			bandera = "mA";

		} else
			bandera = "mO";

		final Integer rows = rowsParam + 2;
		final Integer columns = columnsParam + 3;
		final ArrayList<JTextField> listOfTextFields = new ArrayList<JTextField>();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// CONTROLAR SALIDA
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					Matriz matriz = new Matriz(null, null, tipoMetodo, null);
					matriz.setVisible(true);
					setVisible(false);
					dispose();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar cerrar la ventana.");
					System.out.println(error.getMessage());
				}
			}
		});

		setBounds(100, 130, 580, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();

		int[] rowHeights = new int[rows];
		int[] columnWidths = new int[columns];
		double[] rowWeights = new double[rows];
		double[] columnWeights = new double[columns];

		for (int i = 0; i < rows; i++) {
			rowHeights[i] = 0;
			rowWeights[i] = 1.0;
		}

		for (int j = 0; j < columns; j++) {
			columnWidths[j] = 0;
			columnWeights[j] = 1.0;
		}

		gbl_contentPane.columnWidths = columnWidths;
		gbl_contentPane.rowHeights = rowHeights;
		gbl_contentPane.columnWeights = columnWeights;
		gbl_contentPane.rowWeights = rowWeights;

		contentPane.setLayout(gbl_contentPane);

		// INSERTAR INPUTFIELDS
		GridBagConstraints gbc_matrix = new GridBagConstraints();
		gbc_matrix.weightx = 1;
		gbc_matrix.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < rows - 1; i++) {
			int c = 0, k = 0;
			for (int j = 0; j < columns - 1; j++) {
				if (i == 0 && j != 0) {
					// AGREGAR CABECEROS
					JLabel descLabel = null;

					// DETERMINAR SI ES UNA COLUMNA DE INCOGNITAS O DE CONSTANTES
					if (j == columns - 2) {
						descLabel = new JLabel("b");
					} else {
						descLabel = new JLabel("X" + j);
					}

					descLabel.setHorizontalAlignment(SwingConstants.CENTER);
					descLabel.setVerticalAlignment(SwingConstants.CENTER);
					gbc_matrix.gridx = j;
					gbc_matrix.gridy = i;
					contentPane.add(descLabel, gbc_matrix);
				} else if (j == 0 && i != 0) {
					// AGREGAR NUMERO DE FILA
					JLabel descLabel = new JLabel(Integer.toString(i));
					descLabel.setHorizontalAlignment(SwingConstants.CENTER);
					descLabel.setVerticalAlignment(SwingConstants.CENTER);
					gbc_matrix.gridx = j;
					gbc_matrix.gridy = i;
					contentPane.add(descLabel, gbc_matrix);
				} else if (j != 0 && i != 0) {
					// AGREGAR TEXTFIELD
					JTextField field = new JTextField();
					if (matrizAux == null)
						field.setText(Integer.toString(0));
					else {

					}

					// VOLVER NULL LOS TEXT FIELDS CON UN CLICK
					field.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							field.setText(null);
						}
					});
					// VALIDAR QUE SE INGRESEN SOLO NUMEROS
					field.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {
							char c = e.getKeyChar();

							if (Character.isLetter(c) && !e.isAltDown()) {
								e.consume();
							}
						}

					});

					gbc_matrix.gridx = j;
					gbc_matrix.gridy = i;
					listOfTextFields.add(field);
					contentPane.add(field, gbc_matrix);
				}

			}

		}
		if (matrizAux != null) {
			int C = 0;
			for (int i = 0; i < matrizAux.length; i++) {
				int j = 0;
				while (j < matrizAux[0].length) {

					listOfTextFields.get(C).setText(matrizAux[i][j].toString());

					j++;
					C++;
				}

			}
		}
		btnLimpiar = new JButton("Limpiar");
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.weightx = 1;
		gbc_btnLimpiar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpiar.gridx = columns - 1;
		gbc_btnLimpiar.gridy = 1;

		// LIMPIAR CAMPOS
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listOfTextFields.forEach((field) -> field.setText(Integer.toString(0)));
			}
		});

		contentPane.add(btnLimpiar, gbc_btnLimpiar);

		JButton btnResolver = new JButton("Resolver");
		GridBagConstraints gbc_btnResolver = new GridBagConstraints();
		gbc_btnResolver.weightx = 1;
		gbc_btnResolver.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnResolver.gridx = columns - 1;
		gbc_btnResolver.gridy = 2;

		// RESOLVER MATRIZ
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Double[][]> matrices = new ArrayList<Double[][]>();
					Double[][] matrizOrignial = new Double[rowsParam][columnsParam + 1];
					Double[][] matrizFinal = new Double[rowsParam][columnsParam + 1];
					Double[] solucion = new Double[rowsParam];
					int indexTextField = 0;
					if (matrizAux == null) {
						for (int i = 0; i < rowsParam; i++) {
							for (int j = 0; j < columnsParam + 1; j++) {
								matrizOrignial[i][j] = Double
										.parseDouble(listOfTextFields.get(indexTextField).getText());
								indexTextField++;
							}
						}
					}

					switch (bandera) {
						case "mO": {
							try {
								if (tipoMetodo == MetodoMatrizEnum.GAUSS) {
									Gauss gauss = new Gauss(matrizOrignial.clone());
	
									matrizFinal = gauss.triangulateMatrix(matrizOrignial.clone());
									solucion = gauss.calculateSolution(matrizFinal.clone());
	
									matrices.add(matrizOrignial.clone());
									matrices.add(matrizFinal.clone());
								} else if (tipoMetodo == MetodoMatrizEnum.GAUSS_JORDAN) {
									GaussJordan gaussJordan = new GaussJordan(matrizOrignial.clone());
	
									matrizFinal = gaussJordan.solve(matrizOrignial.clone());
									solucion = gaussJordan.solution(matrizFinal.clone());
	
									matrices.add(matrizOrignial.clone());
									matrices.add(matrizFinal.clone());
								} else if (tipoMetodo == MetodoMatrizEnum.MONTANTE) {
									Montante montante = new Montante(matrizOrignial.clone());
	
									matrizFinal = montante.solve(montante.getOriginalMatrix());
									solucion = montante.solution(matrizFinal.clone());
	
									matrices.add(matrizOrignial.clone());
									matrices.add(matrizFinal.clone());
								} else if (tipoMetodo == MetodoMatrizEnum.CRAMER) {
									Cramer cramer = new Cramer();
	
									matrices.add(matrizOrignial.clone());
									matrizFinal = cramer.solve(matrizOrignial.clone(), solucion, matrices);
								} else if (tipoMetodo == MetodoMatrizEnum.JACOBI) {
									Jacobi jacobi = new Jacobi();
									System.out.println("Error permisible: " + errorPermisible);
									matrices.add(jacobi.solve(matrizOrignial.clone(), matrizOrignial.clone().length,
											errorPermisible, 100));
								} else if (tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL) {
									GaussSeidel gaussSeidel = new GaussSeidel();
									System.out.println("Error permisible: " + errorPermisible);
									matrices.add(gaussSeidel.solve(matrizOrignial.clone(), errorPermisible));
								}
	
								Matriz matriz = new Matriz(matrices, solucion, tipoMetodo, errorPermisible);
								matriz.setVisible(true);
								setVisible(false);
								dispose();
							} catch (Exception error) {
								JOptionPane.showMessageDialog(getContentPane(),
										"Ocurrio un error al intentar resolver la matriz.");
								System.out.println(error.getMessage());
								System.out.println(error.getLocalizedMessage());
								error.printStackTrace();
							}
							break;
						}
	
						case "mA": {
							try {
								if (tipoMetodo == MetodoMatrizEnum.GAUSS) {
									Gauss gauss = new Gauss(matrizAux.clone());
	
									matrizFinal = gauss.triangulateMatrix(matrizAux.clone());
									solucion = gauss.calculateSolution(matrizFinal.clone());
	
									matrices.add(matrizAux.clone());
									matrices.add(matrizFinal.clone());
								} else if (tipoMetodo == MetodoMatrizEnum.GAUSS_JORDAN) {
									GaussJordan gaussJordan = new GaussJordan(matrizAux.clone());
	
									matrizFinal = gaussJordan.solve(matrizAux.clone());
									solucion = gaussJordan.solution(matrizFinal.clone());
	
									matrices.add(matrizAux.clone());
									matrices.add(matrizFinal.clone());
								} else if (tipoMetodo == MetodoMatrizEnum.MONTANTE) {
									Montante montante = new Montante(matrizAux.clone());
	
									matrizFinal = montante.solve(matrizAux.clone());
									solucion = montante.solution(matrizFinal.clone());
	
									matrices.add(matrizAux.clone());
									matrices.add(matrizFinal.clone());
								} else if (tipoMetodo == MetodoMatrizEnum.CRAMER) {
									Cramer cramer = new Cramer();
	
									matrices.add(matrizAux.clone());
									matrizFinal = cramer.solve(matrizAux.clone(), solucion, matrices);
								} else if (tipoMetodo == MetodoMatrizEnum.JACOBI) {
									Jacobi jacobi = new Jacobi();
									System.out.println("Error permisible: " + errorPermisible);
									matrices.add(jacobi.solve(matrizAux.clone(), matrizAux.clone().length, errorPermisible,
											100));
								} else if (tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL) {
									GaussSeidel gaussSeidel = new GaussSeidel();
									System.out.println("Error permisible: " + errorPermisible);
									matrices.add(gaussSeidel.solve(matrizAux.clone(), errorPermisible));
								}
	
								Matriz matrizA = new Matriz(matrices, solucion, tipoMetodo, errorPermisible);
								matrizA.setVisible(true);
								setVisible(false);
								dispose();
							} catch (Exception error) {
								JOptionPane.showMessageDialog(getContentPane(),
										"Ocurrio un error al intentar resolver la matriz.");
								System.out.println(error.getMessage());
							}
							break;
						}
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar resolver la matriz.");
					System.out.println(error.getMessage());
				}
			}
		});

		contentPane.add(btnResolver, gbc_btnResolver);

		JButton btnGuardar = new JButton("Guardar Matriz");
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.weightx = 1;
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.gridx = columns - 1;
		gbc_btnGuardar.gridy = 4;

		/* GUARDAR MATRIZ */
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					matrizOriginal = new Double[rowsParam][columnsParam + 1];
					int indexTextField = 0;
					for (int i = 0; i < rowsParam; i++) {
						for (int j = 0; j < columnsParam + 1; j++) {
							matrizOriginal[i][j] = Double.parseDouble(listOfTextFields.get(indexTextField).getText());
							indexTextField++;
						}
					}
					Menu_principal.setMatrixOriginal(matrizOriginal);
				} catch (Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar guardar la matriz.");
					System.out.println(error.getMessage());
				}
			}
		});

		contentPane.add(btnGuardar, gbc_btnGuardar);

	}

	public Double[][] getMatrizOrignial() {
		return matrizOriginal;
	}

	public void setMatrizOrignial(Double[][] matrizOrignial) {
		this.matrizOriginal = matrizOrignial;
	}

}
