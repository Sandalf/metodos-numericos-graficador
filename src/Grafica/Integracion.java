package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.DerivacionEnum;
import Projectofinal.IntegracionEnum;
import Projectofinal.IntegracionTrapecio;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Integracion extends JFrame {

	private JPanel contentPane;
	private JTextField limSupTextField;
	private JTextField limInfTextField;
	private JTextField numPuntosTextField;
	private JTable table;
	private JLabel lblFuncin;
	private JTextField funcionTextField;
	private JLabel lblNewLabel;
	private JTextField solucionTextField;
	private JButton btnResolver;
	private JButton btnLimpiar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Integracion frame = new Integracion(null);
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
	public Integracion(IntegracionEnum tipoMetodo) {
		setResizable(false);
		
		// EVENTO PARA REGRESAR A VENTANA PRINCIPAL
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Menu_principal.main(null);
				setVisible(false);
				dispose();
			}
		});
		
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// TITULO DE VENTANA
		if(tipoMetodo == IntegracionEnum.Trapecio) {
			setTitle("Trapecio");
		}
		
		// CABECEROS
		String[] cabecero = {"X","Y","Fm","Producto"};
		
		JLabel lblLimiteSuperior = new JLabel("Limite Superior:");
		lblLimiteSuperior.setBounds(18, 54, 105, 16);
		contentPane.add(lblLimiteSuperior);
		
		limSupTextField = new JTextField();
		limSupTextField.setBounds(148, 49, 130, 26);
		// VALIDAR QUE SE INGRESEN SOLO NUMEROS
		limSupTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		contentPane.add(limSupTextField);
		limSupTextField.setColumns(10);
		
		JLabel lblLimiteInferior = new JLabel("Limite Inferior:");
		lblLimiteInferior.setBounds(18, 24, 105, 16);
		contentPane.add(lblLimiteInferior);
		
		limInfTextField = new JTextField();
		limInfTextField.setBounds(148, 19, 130, 26);
		// VALIDAR QUE SE INGRESEN SOLO NUMEROS
		limInfTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		contentPane.add(limInfTextField);
		limInfTextField.setColumns(10);
		
		JLabel lblNmeroDePuntos = new JLabel("Número de puntos:");
		lblNmeroDePuntos.setBounds(18, 82, 121, 16);
		contentPane.add(lblNmeroDePuntos);
		
		numPuntosTextField = new JTextField();
		numPuntosTextField.setBounds(148, 77, 130, 26);
		// VALIDAR QUE SE INGRESEN SOLO NUMEROS
		numPuntosTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});
		contentPane.add(numPuntosTextField);
		numPuntosTextField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 173, 415, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(new Object[][] {},cabecero));
		scrollPane.setColumnHeaderView(table);
		
		lblFuncin = new JLabel("Función:");
		lblFuncin.setBounds(18, 110, 61, 16);
		contentPane.add(lblFuncin);
		
		funcionTextField = new JTextField();
		funcionTextField.setBounds(148, 105, 130, 26);
		contentPane.add(funcionTextField);
		funcionTextField.setColumns(10);
		
		lblNewLabel = new JLabel("Solución:");
		lblNewLabel.setBounds(18, 138, 61, 16);
		contentPane.add(lblNewLabel);
		
		solucionTextField = new JTextField();
		solucionTextField.setEditable(false);
		solucionTextField.setBounds(148, 133, 130, 26);
		contentPane.add(solucionTextField);
		solucionTextField.setColumns(10);
		
		btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String funcion = funcionTextField.getText();
				Double limInf = Double.parseDouble(limInfTextField.getText());
			    Double limSup = Double.parseDouble(limSupTextField.getText());
			    Integer numPuntos = Integer.parseInt(numPuntosTextField.getText());
				IntegracionTrapecio trapecio = new IntegracionTrapecio(funcion,limInf,limSup,numPuntos);
				Double solucion = trapecio.solve();
				
				table.setModel(new DefaultTableModel(trapecio.getTabla(),cabecero));
				solucionTextField.setText(solucion.toString());
			}
		});
		btnResolver.setBounds(316, 19, 117, 29);
		contentPane.add(btnResolver);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(316, 49, 117, 29);
		contentPane.add(btnLimpiar);
		
		/* INICIALIZAR VALORES */
		limSupTextField.setText("0");
		limInfTextField.setText("0");
	}
}
