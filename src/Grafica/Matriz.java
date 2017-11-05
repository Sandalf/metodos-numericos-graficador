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
					Matriz frame = new Matriz();
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
	public Matriz() {
		initialize(null,null);
	}
	
	public Matriz(ArrayList<double[][]> matrices, double[] solucion) {
		initialize(matrices,solucion);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<double[][]> matrices,double[] solucion) {
		setBounds(120, 100, 450, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnCrearMatriz = new JButton("Crear matriz");
		btnCrearMatriz.setBounds(323, 6, 121, 29);
		btnCrearMatriz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer rows = (Integer)renglonesComboBox.getSelectedItem();
				Integer columns = (Integer)columnasComboBox.getSelectedItem();
				EditarMatriz editarMatriz = new EditarMatriz(rows,columns);
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
		for(int i = 1; i <= 100; i++) {
			renglonesComboBox.addItem(new Integer(i));
			columnasComboBox.addItem(new Integer(i));
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
			System.out.println("Matrices size: " + matrices.size());
			int matrizIndex = 1;
			int yPosition = 130;
			for(double[][] matriz: matrices) {
				System.out.println("Matriz: " + matrizIndex);
				Gauss g = new Gauss(matriz);
				g.displayMatrix(matriz);
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
