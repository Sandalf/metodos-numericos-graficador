package Grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.DerivacionEnum;
import Projectofinal.DerivacionNumericaPorLimites;
import Projectofinal.Funcion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class DerivacionPorLimites extends JFrame {

	private JPanel contentPane;
	private JTextField xTextField;
	private JTextField errorTextField;
	private JTextField funcionTextField;
	private JFormattedTextField solucionFormattedTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DerivacionPorLimites frame = new DerivacionPorLimites();
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
	public DerivacionPorLimites() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Menu_principal.main(null);
				setVisible(false);
				dispose();
			}
		});
		setBounds(100, 100, 475, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * TITULO DE VENTANA
		 */
		setTitle("Derivacion numerica por limites");

		/* 
		 * CABECEROS DE LA TABLA
		 */
		String[] cabecerosTabla = { "n","h","k","x+h","f(x+h)","f(x)"+"f(x+h) - f(x)","f(x)","e"};
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(18, 17, 22, 16);
		contentPane.add(lblX);
		
		JLabel lblError = new JLabel("Error:");
		lblError.setBounds(18, 45, 61, 16);
		contentPane.add(lblError);
		
		JLabel lblFuncin = new JLabel("Funcion:");
		lblFuncin.setBounds(18, 73, 61, 16);
		contentPane.add(lblFuncin);
		
		xTextField = new JTextField();
		xTextField.setBounds(76, 12, 130, 26);
		
		/* VALIDAR QUE SE INGRESEN SOLO NUMEROS */
		xTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		
		contentPane.add(xTextField);
		xTextField.setColumns(10);
		
		errorTextField = new JTextField();
		errorTextField.setBounds(76, 40, 130, 26);
		
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
		
		contentPane.add(errorTextField);
		errorTextField.setColumns(10);
		
		funcionTextField = new JTextField();
		funcionTextField.setBounds(76, 68, 130, 26);
		contentPane.add(funcionTextField);
		funcionTextField.setColumns(10);
		
		JLabel lblSolucin = new JLabel("Solucion:");
		lblSolucin.setBounds(218, 73, 61, 16);
		contentPane.add(lblSolucin);
		
		solucionFormattedTextField = new JFormattedTextField();
		solucionFormattedTextField.setBounds(291, 68, 165, 26);
		contentPane.add(solucionFormattedTextField);
		
		/* RESOLVER */
		JButton btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double x;
					Double error;
					Funcion funcion;
					DerivacionNumericaPorLimites derivacion;
					
					/* VALIDACIONES */
					if(xTextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar un valor x.");
					} else if(errorTextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar un error.");
					} else if(funcionTextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar una funcion valida.");
					} else {
						x = Double.parseDouble(xTextField.getText().trim());
						error = Double.parseDouble(errorTextField.getText().trim());
						funcion = new Funcion(funcionTextField.getText().trim());
						derivacion = new DerivacionNumericaPorLimites(funcion,error);
						solucionFormattedTextField.setText(derivacion.solve(x).toString());						
						table.setModel(new DefaultTableModel(derivacion.getTabla(), cabecerosTabla));
					}
			} catch(Exception error) {
				JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al calcular la funcion.");
			}
		}
		});
		btnResolver.setBounds(218, 12, 117, 29);
		contentPane.add(btnResolver);
		
		/* LIMPIAR */
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xTextField.setText("0");
				errorTextField.setText("0");
				funcionTextField.setText("");
				solucionFormattedTextField.setText("");
				table.setModel(new DefaultTableModel(new Object[][] {}, cabecerosTabla));
			}
		});
		btnLimpiar.setBounds(339, 12, 117, 29);
		contentPane.add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 110, 435, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		/* ESTABLECER VALORES INICIALES EN LA VISTA */
		xTextField.setText("0");
		errorTextField.setText("0");
		funcionTextField.setText("");
		solucionFormattedTextField.setText("");
		table.setModel(new DefaultTableModel(new Object[][] {}, cabecerosTabla));
	}
}
