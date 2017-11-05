package Grafica;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Projectofinal.Gauss;

public class EditarMatriz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLimpiar;

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
	public EditarMatriz() {}
	
	public EditarMatriz(int rowsParam, int columnsParam) {
		// SE INCREMENTA PARA INSERTAR CABECEROS Y ESPACIADO
		final Integer rows = rowsParam + 2;
		final Integer columns = columnsParam + 3;
		final ArrayList<JFormattedTextField> listOfTextFields = new ArrayList<JFormattedTextField>();
		final ArrayList<double[][]> matricesCalculadas = new ArrayList<double[][]>();
		final double[] solucion = new double[rowsParam];
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// CONTROLAR EXIT
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Matriz matriz = new Matriz(null,null);
		        matriz.setVisible(true);
				setVisible(false);
				dispose();
		    }
		});
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		
		int[] rowHeights = new int[rows];
		int[] columnWidths = new int[columns];
		double[] rowWeights = new double[rows];
		double[] columnWeights = new double[columns];
		
		for(int i = 0; i < rows; i++) {
			rowHeights[i] = 0;
			rowWeights[i] = 1.0;
		}
		
		for(int j = 0; j < columns; j++) {
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
		for(int i = 0; i < rows - 1; i++) {
			for(int j = 0; j < columns - 2; j++) {
				if(i == 0 && j != 0) {
					// AGREGAR CABECEROS
					JLabel descLabel = new JLabel("X" + j);
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
				} else if (j != 0 && i != 0){
					// AGREGAR TEXTFIELD
					NumberFormat format = NumberFormat.getInstance();
				    NumberFormatter formatter = new NumberFormatter(format);
				    formatter.setValueClass(Integer.class);
				    formatter.setMaximum(Integer.MAX_VALUE);
				    formatter.setAllowsInvalid(false);
				    formatter.setCommitsOnValidEdit(true);
				    JFormattedTextField field = new JFormattedTextField(formatter);
				    field.setValue(new Integer(0));
					gbc_matrix.gridx = j;
					gbc_matrix.gridy = i;
					listOfTextFields.add(field);
					contentPane.add(field, gbc_matrix);
				}
			}
		}
		
		btnLimpiar = new JButton("Limpiar");
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.weightx = 1;
		gbc_btnLimpiar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpiar.gridx = columns -1;
		gbc_btnLimpiar.gridy = 1;
		
		// LIMPIAR CAMPOS
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listOfTextFields.forEach((field)->field.setValue(new Integer(0)));
			}
		});
		
		contentPane.add(btnLimpiar, gbc_btnLimpiar);
		
		JButton btnResolver = new JButton("Resolver");
		GridBagConstraints gbc_btnResolver = new GridBagConstraints();
		gbc_btnResolver.weightx = 1;
		gbc_btnResolver.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnResolver.gridx = columns -1;
		gbc_btnResolver.gridy = 2;
		
		// RESOLVER MATRIZ
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<double[][]> matrices = new ArrayList<double[][]>();
				double[][] matrizOrignial = new double[rowsParam][columnsParam];
				double[][] matrizTriangulada = new double[rowsParam][columnsParam];
				double[] solucion = new double[rowsParam];
				int indexTextField = 0;
			
				for(int i = 0; i < rowsParam; i++) {
					for(int j = 0; j < columnsParam; j++) {
						matrizOrignial[i][j] = (double)((Integer)listOfTextFields.get(indexTextField).getValue());
						indexTextField++;
					}
				}			
				
				Gauss gauss = new Gauss(matrizOrignial);

				matricesCalculadas.add(matrizOrignial);

				matrizTriangulada = gauss.triangulateMatrix(gauss.getOriginalMatrix());
				solucion = gauss.calculateSolution(matrizTriangulada);
				
				matricesCalculadas.add(matrizTriangulada);
				
				Matriz matriz = new Matriz(matricesCalculadas,solucion);
				matriz.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		contentPane.add(btnResolver, gbc_btnResolver);
	}

}
