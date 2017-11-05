package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class EditarMatriz extends JFrame {

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane.add(btnResolver, gbc_btnResolver);
	}

}
