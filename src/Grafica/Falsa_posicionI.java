package Grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Projectofinal.Biseccion;
import Projectofinal.Falsa_posicion;
import Projectofinal.Funcion;
import Grafica.Grafica;
import Grafica.Menu_principal;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Falsa_posicionI {

	private JFrame frame;
	private JTable table;
	private JTextField exp;
	private JTextField x1;
	private JTextField xI;
	private JTextField Lim;
	private JTextField Error;
	private JTextField Raiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Falsa_posicionI window = new Falsa_posicionI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Falsa_posicionI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 457);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      Menu_principal M = new Menu_principal();
		        M.main(null);
				frame.setVisible(false);
				frame.dispose();
		    }
		});
		frame.setTitle("Falsa Posicion");
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 578, 203);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"I", "x1", "x2", "x2-x1","F(xI)-F(x)", "xI+1", "E", "F(x+1)"
			}
		));
		scrollPane.setViewportView(table);
		
		exp = new JTextField();
		exp.setBounds(10, 31, 112, 20);
		panel.add(exp);
		exp.setColumns(10);
		
		x1 = new JTextField();
		x1.setBounds(147, 31, 45, 20);
		panel.add(x1);
		x1.setColumns(10);
		
		xI = new JTextField();
		xI.setBounds(230, 31, 45, 20);
		panel.add(xI);
		xI.setColumns(10);
		
		Lim = new JTextField();
		Lim.setBounds(321, 31, 95, 20);
		panel.add(Lim);
		Lim.setColumns(10);
		
		Error = new JTextField();
		Error.setBounds(459, 31, 95, 20);
		panel.add(Error);
		Error.setColumns(10);
		
		JLabel lblFuncion = new JLabel("Funcion");
		lblFuncion.setBounds(37, 11, 46, 14);
		panel.add(lblFuncion);
		
		JLabel lblX = new JLabel("x1");
		lblX.setBounds(159, 11, 31, 14);
		panel.add(lblX);
		
		JLabel lblX_1 = new JLabel("xI");
		lblX_1.setBounds(244, 11, 31, 14);
		panel.add(lblX_1);
		
		JLabel lblIteraciones = new JLabel("Iteraciones");
		lblIteraciones.setBounds(335, 11, 81, 14);
		panel.add(lblIteraciones);
		
		JLabel lblError = new JLabel("Error");
		lblError.setBounds(481, 11, 46, 14);
		panel.add(lblError);
		
		JLabel lblRaiz = new JLabel("Raiz");
		lblRaiz.setBounds(37, 124, 61, 14);
		panel.add(lblRaiz);
		
		JButton EncontrarSolucion = new JButton("Encontrar Solucion");
		EncontrarSolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				String F = exp.getText();
				double X1 =Double.parseDouble(x1.getText());
				double XI =Double.parseDouble(xI.getText());
				int Ite = Integer.parseInt(Lim.getText());
				double E = Double.parseDouble(Error.getText());
				Funcion f= new Funcion(F);
                try {
					if(f.eval(X1)*f.eval(XI)<0) {
					
					
					Falsa_posicion Falsa = new Falsa_posicion(F,X1,XI,E,Ite);
					
						
							Falsa.Iniciar();
							if(Falsa.getBandera()!=false) 
		                     {
		                    	 JOptionPane.showMessageDialog(panel, "No se encotro la raiz en el numero de iteraciones dadas");
		                     }
					

					Object[][] Resultados = Falsa.getResultados();
					
					
					table.setModel(new DefaultTableModel(
							Resultados
								
							,
							new String[] {
									"I", "x1", "x2", "x2-x1","F(xI)-F(x)", "xI+1", "E", "F(x+1)"
							}
            
						));
   

                     Raiz.setText(Double.toString(Falsa.getRaiz()));}
                     else { JOptionPane.showMessageDialog(panel, "No Existe Raiz en ese intervalo ingrese otros valores "
                     		+ "o una funcion diferente");}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
		}
	});
				
		
		EncontrarSolucion.setBounds(10, 62, 125, 28);
		panel.add(EncontrarSolucion);
		
		JButton btnNewButton = new JButton("Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu_principal m = new Menu_principal();
				
				m.main(null);
				frame.dispose();
				
				
			}
		});
		btnNewButton.setBounds(448, 134, 125, 39);
		panel.add(btnNewButton);
		
		Raiz = new JTextField();
		Raiz.setBackground(Color.GRAY);
		Raiz.setBounds(10, 149, 125, 20);
		panel.add(Raiz);
		Raiz.setColumns(10);
		JButton btnNewButton_1 = new JButton("Graficar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Grafica G = new Grafica(exp.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton_1.setBounds(448, 78, 125, 28);
		panel.add(btnNewButton_1);
	
	}
}