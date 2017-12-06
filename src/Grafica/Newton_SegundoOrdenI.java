package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.Funcion;
import Projectofinal.Newton_SegundoOrden;
import Projectofinal.newton_raphson;

public class Newton_SegundoOrdenI {

	private JFrame frame;
	private JTable table;
	private JTextField exp;
	private JTextField x1;
	private JTextField Iteraciones;
	private JTextField Error;
	private JTextField Raiz;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Newton_SegundoOrdenI window = new Newton_SegundoOrdenI();
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
	public Newton_SegundoOrdenI() {
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
		
		frame.setTitle("Newton De Segundo Orden");
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
					"I", "F(Xi)","F'(Xi)","F''(Xi)","Xi+1","F(Xi+1)","E"
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
		
		
		
		Iteraciones = new JTextField();
		Iteraciones.setBounds(282, 31, 95, 20);
		panel.add(Iteraciones);
		Iteraciones.setColumns(10);
		
		Error = new JTextField();
		Error.setBounds(459, 31, 95, 20);
		panel.add(Error);
		Error.setColumns(10);
		
		JLabel lblFuncion = new JLabel("Funcion");
		lblFuncion.setBounds(37, 11, 46, 14);
		panel.add(lblFuncion);
		
		JLabel lblX = new JLabel("X1");
		lblX.setBounds(159, 11, 31, 14);
		panel.add(lblX);
		
		JLabel lblIteraciones = new JLabel("Iteraciones");
		lblIteraciones.setBounds(306, 11, 81, 14);
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
			    int Lim = Integer.parseInt(Iteraciones.getText());
				double E = Double.parseDouble(Error.getText());
				Funcion f= new Funcion(F);
                try {
					
					
					
					Newton_SegundoOrden B = new Newton_SegundoOrden(F,X1,E,Lim);
					
						try {
							B.inicia();
							 if(B.getBandera()!=false) 
		                     {
		                    	 JOptionPane.showMessageDialog(panel, "No se encotro la raiz en el numero de iteraciones dadas");
		                     }
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					

					Object[][] Resultados = B.getResultados();
					
					
					table.setModel(new DefaultTableModel(
							Resultados
								
							,
							new String[] {
							"I", "F(Xi)","F'(Xi)","F''(Xi)","Xi+1","F(Xi+1)","E"
							}
            
						));
   

                     Raiz.setText(Double.toString(B.getRaiz()));
                    
                
                }


				 catch (Exception e) {
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
				Menu_principal m= new Menu_principal();
				
				m.main(null);
				frame.dispose();
				
				
			}
		});
		btnNewButton.setBounds(448, 134, 125, 39);
		panel.add(btnNewButton);
		
		Raiz = new JTextField();
		Raiz.setBackground(Color.GRAY);
		Raiz.setBounds(10, 149, 142, 20);
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
