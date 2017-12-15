package Grafica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.MetodoMatrizEnum;

public class Matriz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> renglonesComboBox;
	private JComboBox<Integer> columnasComboBox;
	private JTable table;
	private JTextField errorTextField;
	private Double [][] MatrixAux;
	private int matrixIndex;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JLabel matrizLabel;
	private ArrayList<JLabel> labelsMatrizPaginacion = new ArrayList<JLabel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Matriz frame = new Matriz(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Matriz(MetodoMatrizEnum tipoMetodo,Double [][] MatrixAux) {
		initialize(null,null,tipoMetodo,null);
		this.MatrixAux=MatrixAux;
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public Matriz(ArrayList<Double[][]> matrices, Double[] solucion, MetodoMatrizEnum tipoMetodo, Double errorPermisible) {
		initialize(matrices,solucion,tipoMetodo,errorPermisible);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Double[][]> matrices,Double[] solucion, MetodoMatrizEnum tipoMetodo, Double errorPermisible) {
		setBounds(100, 130, 580, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// TITULO DE VENTANA
		switch(tipoMetodo) {
			case GAUSS:
				setTitle("Gauss");
				break;
			case GAUSS_JORDAN:
				setTitle("Gauss Jordan");
				break;
			case MONTANTE:
				setTitle("Montante");
				break;
			case CRAMER:
				setTitle("Cramer");
				break;
			case JACOBI:
				setTitle("Jacobi");
				break;
			case GAUSS_SEIDEL:
				setTitle("Gauss Seidel");
				break;
			default:
				break;
		}
		
		// CONTROLAR SALIDA
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Menu_principal.main(null);
				setVisible(false);
				dispose();
		    }
		});
		
		JButton btnCrearMatriz = new JButton("Crear matriz");
		btnCrearMatriz.setBounds(323, 6, 121, 29);
		btnCrearMatriz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double errorPermisible = new Double(0);
					
					if((tipoMetodo == MetodoMatrizEnum.JACOBI || tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL)  && errorTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "Deber ingresar el error permisible.");
					} else if((tipoMetodo == MetodoMatrizEnum.JACOBI || tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL) && !errorTextField.getText().isEmpty()) {
						errorPermisible = Double.parseDouble(errorTextField.getText());
						Integer rows = (Integer)renglonesComboBox.getSelectedItem();
						Integer columns = (Integer)columnasComboBox.getSelectedItem();
						EditarMatriz editarMatriz = new EditarMatriz(rows,columns,tipoMetodo,errorPermisible,null);
						editarMatriz.setVisible(true);
						
						dispose();
					} else {
						Integer rows = (Integer)renglonesComboBox.getSelectedItem();
						Integer columns = (Integer)columnasComboBox.getSelectedItem();
						EditarMatriz editarMatriz = new EditarMatriz(rows,columns,tipoMetodo,errorPermisible,null);
						editarMatriz.setVisible(true);
						
						dispose();
					}
				} catch(Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar crear la matriz.");
					System.out.println(error.getMessage());
				}
			}
		});
		getContentPane().setLayout(null);
		
		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setBounds(57, 11, 82, 16);
		getContentPane().add(lblDimensiones);
		
		renglonesComboBox = new JComboBox<Integer>();
		renglonesComboBox.setBounds(151, 7, 72, 27);
		columnasComboBox = new JComboBox<Integer>();
		columnasComboBox.setBounds(245, 6, 72, 27);
		
		// AGREGAR ELEMENTOS DE COMBOBOX
		for(int i = 2; i <= 100; i++) {
			renglonesComboBox.addItem(new Integer(i));
			columnasComboBox.addItem(new Integer(i));
		}
		
		getContentPane().add(renglonesComboBox);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(225, 11, 8, 16);
		getContentPane().add(lblX);
		
		getContentPane().add(columnasComboBox);
		getContentPane().add(btnCrearMatriz);
		
		JButton btnMatrixAux = new JButton("Matriz Aux");
		btnMatrixAux .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	                 Double errorPermisible = new Double(0);
					
					if((tipoMetodo == MetodoMatrizEnum.JACOBI || tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL)  && errorTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "Deber ingresar el error permisible.");
					} else if((tipoMetodo == MetodoMatrizEnum.JACOBI || tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL) && !errorTextField.getText().isEmpty()) {
						errorPermisible = Double.parseDouble(errorTextField.getText());
						int rows = MatrixAux.length;
						int columns = MatrixAux[0].length-1;
						EditarMatriz editarMatriz = new EditarMatriz(rows,columns,tipoMetodo,errorPermisible,MatrixAux);
						editarMatriz.setVisible(true);
						
						dispose();
					} else {
						Integer rows = MatrixAux.length;
						Integer columns = MatrixAux[0].length-1;
						EditarMatriz editarMatriz = new EditarMatriz(rows,columns,tipoMetodo,errorPermisible,MatrixAux);
						editarMatriz.setVisible(true);
						dispose();
					}
				} catch(Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "No tiene niguna matriz guardada en la matriz auxiliar.");
					System.out.println(error.getMessage());
				}
				
			}
		});
		btnMatrixAux .setBounds(444, 7, 121, 27);
		getContentPane().add(btnMatrixAux );
		
		if(matrices != null) 
			btnMatrixAux.setVisible(false);
		
		if(tipoMetodo == MetodoMatrizEnum.JACOBI || tipoMetodo == MetodoMatrizEnum.GAUSS_SEIDEL) {
			
			// AJUSTAR TAMAÑO DE VENTANA
			setBounds(120, 100, 600, 350);
			
			JLabel errorLabel = new JLabel("Error permisible:");
			errorLabel.setBounds(57,40,160,30);
			getContentPane().add(errorLabel);
			
			errorTextField = new JTextField("0.1");
			errorTextField.setBounds(180,40,60,30);
			
			// SE INGRESA ERROR PERMISBLE PUESTO ANTES DE CREAR MATRIZ
			if(errorPermisible != null) {
				errorTextField.setText(errorPermisible.toString());
			}
			
			// VALIDAR QUE SE INGRESEN SOLO NUMEROS
			errorTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					
					if(Character.isLetter(c) && !e.isAltDown()) {
						e.consume();
					}
				}
			});
			getContentPane().add(errorTextField); 
			
			if(matrices != null) {
				
			
				// DEPLEGAR TABLA
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 80, 578, 203);
				getContentPane().add(scrollPane);
				
				String[] cabeceros = new String[matrices.get(0)[0].length];
				System.out.println("Cabeceros length: " + cabeceros.length);
				int xVarIndex = 0;
				
				// INSERTAR CABECEROS DINAMICAMENTE
				for(int i = 0; i < cabeceros.length; i++) {
					if(xVarIndex == ((cabeceros.length-1)/2)) {
						xVarIndex = 1;
					} else {
						xVarIndex++;
					}
					
					if(i == cabeceros.length - 1) {
						cabeceros[i] = "Error";
					} else {
						cabeceros[i] = "X" + xVarIndex;				
					}
				}
				
				table = new JTable();
				table.setBackground(Color.LIGHT_GRAY);
				table.setBorder(new LineBorder(new Color(0, 0, 0)));
				table.setModel(new DefaultTableModel(new Object[][] {},cabeceros));
				scrollPane.setViewportView(table);
				
				Object[][] resultados = matrices.get(0);
				table.setModel(new DefaultTableModel(resultados,cabeceros));
			}
			
		} else {
			
			// INICIALIZAR PAGINACION DE MATRICES
			matrixIndex = 0;
		
			// DESPLEGAR MATRICES
			if(matrices != null && solucion != null) {
				int labelHorizontalPos = 57;
				int labelVerticalPos = 120;
				JLabel solucionLabel = new JLabel("Solucion:");
				solucionLabel.setBounds(57,90,80,30);
				getContentPane().add(solucionLabel);
				
				// DESPLEGAR SOLUCION
				for(int i = 0; i < solucion.length; i++) {
					JLabel xLabel = new JLabel("X"+(i+1));
					JTextField xTextField = new JTextField();
					xTextField.setText(Double.toString(solucion[i]));
					xLabel.setBounds(labelHorizontalPos,labelVerticalPos,30,30);
					xTextField.setBounds(labelHorizontalPos+15,labelVerticalPos,80,30);
					xTextField.setEditable(false);
					labelHorizontalPos += 100;
					getContentPane().add(xLabel);
					getContentPane().add(xTextField);
				}
				
				JLabel lblVerMatrices = new JLabel("Ver Matrices: " + matrices.size() + " matrices");
				lblVerMatrices.setBounds(57, 39, 210, 16);
				getContentPane().add(lblVerMatrices);
				
				/* VER SIGUIENTE MATRIZ */
				btnSiguiente = new JButton("Siguiente");
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						matrixIndex += 1;
						paginarMatriz(matrices,matrixIndex);
					}
				});
				btnSiguiente.setBounds(176, 63, 117, 29);
				getContentPane().add(btnSiguiente);
				
				/* VER MATRIZ ANTERIOR */
				btnAnterior = new JButton("Anterior");
				btnAnterior.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						matrixIndex -= 1;
						paginarMatriz(matrices,matrixIndex);
					}
				});
				btnAnterior.setBounds(47, 63, 117, 29);
				getContentPane().add(btnAnterior);
				
				paginarMatriz(matrices,matrixIndex);			
			}
		}
	}
	
	public void paginarMatriz(ArrayList<Double[][]> matrices, int index) {
		
		/* HABILITAR O DESHABILITAR BOTONES DE PAGINACION */
		if (matrixIndex == 0) {
			btnAnterior.setEnabled(false);
		} else {
			btnAnterior.setEnabled(true);
		}
		
		if(matrixIndex == matrices.size()-1) {
			btnSiguiente.setEnabled(false);
		} else {
			btnSiguiente.setEnabled(true);
		}
		
		/* ELIMINAR COMPONENTES PREVIOS */
		if(matrizLabel != null) { 
			getContentPane().remove(matrizLabel);
		}
		
		if(!labelsMatrizPaginacion.isEmpty()) {
			for(JLabel label : labelsMatrizPaginacion) {
				getContentPane().remove(label);
			}
			labelsMatrizPaginacion.clear();
		}
		
		matrizLabel = new JLabel("Matriz " + Integer.toString(matrixIndex+1));
		matrizLabel.setBounds(57,160,60,30);
		getContentPane().add(matrizLabel);
	
		Double[][] matriz = matrices.get(matrixIndex);
		int yPosition = 180;	
		
		/* PINTAR MATRIZ */
		for(Double[] fila: matriz) {
			int xPosition = 57;
			for(Double elemento: fila) {
				System.out.println(elemento);
				JLabel el = new JLabel(Double.toString(elemento));
				el.setBounds(xPosition,yPosition,60,30);
				getContentPane().add(el);
				labelsMatrizPaginacion.add(el);			
				xPosition += 60;
			}
			yPosition += 30;
		}
		
		/* ACTUALIZAR PANEL */
		getContentPane().revalidate();
		getContentPane().repaint();
	}
}
