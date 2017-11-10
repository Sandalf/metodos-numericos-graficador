package Grafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Projectofinal.Gauss;
import Projectofinal.MetodoMatrizEnum;

public class Matriz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> renglonesComboBox;
	private JComboBox<Integer> columnasComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Matriz frame = new Matriz(null);
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
	public Matriz(MetodoMatrizEnum tipoMetodo) {
		initialize(null,null,tipoMetodo);
	}
	
	public Matriz(ArrayList<double[][]> matrices, double[] solucion, MetodoMatrizEnum tipoMetodo) {
		initialize(matrices,solucion,tipoMetodo);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<double[][]> matrices,double[] solucion, MetodoMatrizEnum tipoMetodo) {
		setBounds(120, 100, 450, 700);
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
				Integer rows = (Integer)renglonesComboBox.getSelectedItem();
				Integer columns = (Integer)columnasComboBox.getSelectedItem();
				EditarMatriz editarMatriz = new EditarMatriz(rows,columns,tipoMetodo);
				editarMatriz.setVisible(true);
				dispose();
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
			if(i >= 3) {
				columnasComboBox.addItem(new Integer(i));
			}
		}
		
		getContentPane().add(renglonesComboBox);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(225, 11, 8, 16);
		getContentPane().add(lblX);
		
		getContentPane().add(columnasComboBox);
		getContentPane().add(btnCrearMatriz);
		
		// DESPLEGAR MATRICES
		if(matrices != null && solucion != null) {
			int labelHorizontalPos = 57;
			int labelVerticalPos = 70;
			JLabel solucionLabel = new JLabel("Solución:");
			solucionLabel.setBounds(57,40,80,30);
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
			
			for(double[][] matriz: matrices) {
				System.out.println("Matriz: " + matrizIndex);
				Gauss.displayMatrix(matriz);
				JLabel matrizLabel = new JLabel("Matriz " + Integer.toString(matrizIndex));
				matrizLabel.setBounds(57,yPosition-20,60,30);
				getContentPane().add(matrizLabel);
				for(double[] fila: matriz) {
					int xPosition = 57;
					for(double elemento: fila) {
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
