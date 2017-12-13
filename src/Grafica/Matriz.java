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

import Projectofinal.Gauss;
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
		
		System.out.println("Tipo de metodo " + tipoMetodo);
		
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
				
			}
		});
		btnMatrixAux .setBounds(165, 83, 155, 27);
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
		
			// DESPLEGAR MATRICES
			if(matrices != null && solucion != null) {
				int labelHorizontalPos = 57;
				int labelVerticalPos = 70;
				JLabel solucionLabel = new JLabel("Solucion:");
				solucionLabel.setBounds(57,50,80,30);
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
				
				// DESPLEGAR MATRICES
				int matrizIndex = 1;
				int yPosition = 130;		
				
				System.out.println("Matrices original");
				Gauss.displayMatrix(matrices.get(0));
				System.out.println("Matrices no original");
				Gauss.displayMatrix(matrices.get(1));
				
				for(Double[][] matriz: matrices) {
					System.out.println("Matriz: " + matrizIndex);
					Gauss.displayMatrix(matriz);
					JLabel matrizLabel = new JLabel("Matriz " + Integer.toString(matrizIndex));
					matrizLabel.setBounds(57,yPosition-20,60,30);
					getContentPane().add(matrizLabel);
					for(Double[] fila: matriz) {
						int xPosition = 57;
						for(Double elemento: fila) {
							System.out.println(elemento);
							JLabel el = new JLabel(Double.toString(elemento));
							el.setBounds(xPosition,yPosition,60,30);
							getContentPane().add(el);
							xPosition += 60;
						}
						yPosition += 30;
					}
					yPosition += 30;
					matrizIndex++;
				}
				
				
				
			}
		}
	}

	
}
