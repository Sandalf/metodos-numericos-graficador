package Grafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.EcDiferencialesEnum;
import Projectofinal.Euler;
import Projectofinal.EulerMejorado;

public class EcDiferenciales extends JFrame {

	private JPanel contentPane;
	private JTextField FxyTextField;
	private JTextField XinicialTextField;
	private JTextField XaEncontarTextField;
	private JFormattedTextField solucionFormattedTextField;
	private JTable table;
	private JTextField YinicialtextField;
	private JTextField Iteraciones;
	EcDiferencialesEnum tipoMetodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcDiferenciales frame = new EcDiferenciales(null);
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
	public EcDiferenciales(EcDiferencialesEnum tipoMetodo) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// CONTROLAR SALIDA
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		       Menu_principal M = new Menu_principal();
		        M.main(null);
				setVisible(false);
				dispose();
		    }
		});
		
		setBounds(100, 100, 625, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.tipoMetodo=tipoMetodo;
		
		/*
		 * TITULO DE VENTANA
		 */
		setTitle("Metodo De Euler");

		/* 
		 * CABECEROS DE LA TABLA
		 */
		String[] cabecerosTabla = { "N","Xi","Yi"};
		
		JLabel lblX = new JLabel("F(x,y)");
		lblX.setBounds(18, 17, 48, 16);
		contentPane.add(lblX);
		
		JLabel lblError = new JLabel("X inicial");
		lblError.setBounds(10, 45, 69, 16);
		contentPane.add(lblError);
		
		JLabel lblFuncin = new JLabel("X a encontrar");
		lblFuncin.setBounds(10, 73, 97, 16);
		contentPane.add(lblFuncin);
		
		FxyTextField = new JTextField();
		FxyTextField.setBounds(76, 12, 48, 26);
		
		/* VALIDAR QUE SE INGRESEN SOLO NUMEROS */
		
		
		
		
		contentPane.add(FxyTextField);
		FxyTextField.setColumns(10);
		
		XinicialTextField = new JTextField();
		XinicialTextField.setBounds(95, 40, 29, 26);
		
		// VALIDAR QUE SE INGRESEN SOLO NUMEROS
		XinicialTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		
		contentPane.add(XinicialTextField);
		XinicialTextField.setColumns(10);
		
		XaEncontarTextField = new JTextField();
		XaEncontarTextField.setBounds(95, 68, 29, 26);
		contentPane.add(XaEncontarTextField);
		XaEncontarTextField.setColumns(10);
	    XaEncontarTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					
					if(Character.isLetter(c) && !e.isAltDown()) {
						e.consume();
					}
				}
			});
		
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
					String Exp=FxyTextField.getText();
					double X0=Double.parseDouble(XinicialTextField.getText());
					double Y0=Double.parseDouble(YinicialtextField.getText());
					int N=Integer.parseInt(Iteraciones.getText());
					double X=Double.parseDouble(XaEncontarTextField.getText());
					/* VALIDACIONES */
					if(FxyTextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar una funcion.");
					} else if(XinicialTextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar un valor X.");
					} else if(XaEncontarTextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar una X a encontrar.");
					} else if(YinicialtextField.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar un valor Y.");
					} else if(Iteraciones.getText() == "") {
						JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar una N como numero de iteraciones.");
					} else {
						switch(tipoMetodo){
						case Euler:
						
						Euler Ec = new Euler(Exp,X0,Y0,N,X);
						solucionFormattedTextField.setText(Ec.solve().toString());	
						table.setModel(new DefaultTableModel(Ec.getTabla(), cabecerosTabla));
						case EulerMejorado:
							EulerMejorado EcMejorado = new EulerMejorado(Exp,X0,Y0,N,X);
							solucionFormattedTextField.setText(EcMejorado.solve().toString());	
							table.setModel(new DefaultTableModel(EcMejorado.getTabla(), cabecerosTabla));
						
							}}
				}catch(Exception error) {
				JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al calcular la funcion.");
			}
				}
		
		});
		btnResolver.setBounds(250, 11, 117, 29);
		contentPane.add(btnResolver);
		
		/* LIMPIAR */
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FxyTextField.setText("0");
					XinicialTextField.setText("0");
					XaEncontarTextField.setText("");
					solucionFormattedTextField.setText("");
					table.setModel(new DefaultTableModel(new Object[][] {}, cabecerosTabla));
				} catch(Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar limpiar.");
				}
			}
		});
		btnLimpiar.setBounds(377, 11, 117, 29);
		contentPane.add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 110, 564, 297);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		/* ESTABLECER VALORES INICIALES EN LA VISTA */
		FxyTextField.setText("0");
		XinicialTextField.setText("0");
		XaEncontarTextField.setText("");
		solucionFormattedTextField.setText("");
		table.setModel(new DefaultTableModel(new Object[][] {}, cabecerosTabla));
		
		JLabel lblYInicial = new JLabel("Y inicial");
		lblYInicial.setBounds(144, 45, 69, 16);
		contentPane.add(lblYInicial);
		
		YinicialtextField = new JTextField();
		YinicialtextField.setText("0");
		YinicialtextField.setColumns(10);
		YinicialtextField.setBounds(210, 40, 19, 26);
		contentPane.add(YinicialtextField);
		YinicialtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		
		JLabel lblN = new JLabel("N");
		lblN.setBounds(152, 17, 19, 16);
		contentPane.add(lblN);
		
		Iteraciones = new JTextField();
		Iteraciones.setText("0");
		Iteraciones.setColumns(10);
		Iteraciones.setBounds(181, 12, 48, 26);
		contentPane.add(Iteraciones);
		Iteraciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
	}
}
