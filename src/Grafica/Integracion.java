package Grafica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Projectofinal.IntegracionEnum;
import Projectofinal.IntegracionSimpsonTresOctavos;
import Projectofinal.IntegracionSimpsonUnTercio;
import Projectofinal.IntegracionTrapecio;
import Projectofinal.IntegracionTrapecioTablaValores;

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
	private JLabel lblNmeroDePuntos;
	private JButton btnAgregarPunto;
	private JLabel xLabel;
	private JTextField xTextField;
	private JLabel yLabel;
	private JTextField yTextField;
	private ArrayList<Double[]> trapecioTablaValores = new ArrayList<Double[]>();
	private Double[][] trapecioTablaValoresTableModel;

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
		
		lblNmeroDePuntos = new JLabel("Número de puntos:");
		lblNmeroDePuntos.setBounds(18, 83, 121, 16);
		contentPane.add(lblNmeroDePuntos);
		
		numPuntosTextField = new JTextField();
		numPuntosTextField.setBounds(148, 78, 130, 26);
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
		scrollPane.setViewportView(table);
		
		lblFuncin = new JLabel("Función:");
		lblFuncin.setBounds(18, 110, 61, 16);
		contentPane.add(lblFuncin);
		
		funcionTextField = new JTextField();
		funcionTextField.setBounds(148, 105, 130, 26);
		contentPane.add(funcionTextField);
		funcionTextField.setColumns(10);
		
		lblNewLabel = new JLabel("Solucion:");
		lblNewLabel.setBounds(18, 138, 61, 16);
		contentPane.add(lblNewLabel);
		
		solucionTextField = new JTextField();
		solucionTextField.setEditable(false);
		solucionTextField.setBounds(148, 133, 130, 26);
		contentPane.add(solucionTextField);
		solucionTextField.setColumns(10);
		
		/* RESOLVER */
		btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/* VALIDAR */
					if(validar(tipoMetodo)) {
						String funcion = funcionTextField.getText().trim();
						Double limInf = Double.parseDouble(limInfTextField.getText());
					    Double limSup = Double.parseDouble(limSupTextField.getText());
					    Integer numPuntos = Integer.parseInt(numPuntosTextField.getText());
					    
					    /* RESOVLVER POR EL METODO DEL TRAPECIO */
					    if(tipoMetodo == IntegracionEnum.Trapecio) {
							IntegracionTrapecio trapecio = new IntegracionTrapecio(funcion,limInf,limSup,numPuntos);
							Double solucion = trapecio.solve();
							
							table.setModel(new DefaultTableModel(trapecio.getTabla(),cabecero));
							solucionTextField.setText(solucion.toString());
					    } else if (tipoMetodo == IntegracionEnum.TrapecioTablaValores) { 
					    		/* AGREGAR LIMITE SUPERIOR */
					    		limSupTextField.setText(trapecioTablaValores.get(trapecioTablaValores.size()-1)[0].toString());
					    	
					    		Double[][] puntos = new Double[trapecioTablaValores.size()][4];
					    		
					    		for(int i = 0; i < puntos.length; i++) {
					    			puntos[i] = trapecioTablaValores.get(i).clone();
					    		}
					    		
					    		IntegracionTrapecioTablaValores trapecio = new IntegracionTrapecioTablaValores(puntos);
							Double solucion = trapecio.solve();
							
							table.setModel(new DefaultTableModel(trapecio.getTabla(),cabecero));
							solucionTextField.setText(solucion.toString());
					    } else if (tipoMetodo == IntegracionEnum.SimpsonUnTercio) {
		    	
					    		/* RESOVLVER POR EL METODO DEL SIMPSON 1/3 */
					    		IntegracionSimpsonUnTercio simpsonUnTercio = new IntegracionSimpsonUnTercio(funcion,limInf,limSup,numPuntos);
					    		Double solucion = simpsonUnTercio.solve();
					    								
							table.setModel(new DefaultTableModel(simpsonUnTercio.getTabla(),cabecero));
							solucionTextField.setText(String.format(java.util.Locale.US,"%.3f", solucion));
					    		
					    } else if (tipoMetodo == IntegracionEnum.SimpsonTresOctavos) {	
					    	
					    		/* RESOVLVER POR EL METODO DEL SIMPSON 3/8 */
					    		IntegracionSimpsonTresOctavos simpsonTresOctavos = new IntegracionSimpsonTresOctavos(funcion,limInf,limSup,numPuntos);
					    		Double solucion = simpsonTresOctavos.solve();
					    								
							table.setModel(new DefaultTableModel(simpsonTresOctavos.getTabla(),cabecero));
							solucionTextField.setText(String.format(java.util.Locale.US,"%.3f", solucion));
							
					    }	
					}
				} catch(Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar resolver la funcion.");
					System.out.println(error.getMessage());
				}
			}
		});
		btnResolver.setBounds(316, 19, 117, 29);
		contentPane.add(btnResolver);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					limSupTextField.setText("0");
					limInfTextField.setText("0");
					numPuntosTextField.setText("0");
					xTextField.setText("0");
					yTextField.setText("0");
					funcionTextField.setText("");
					solucionTextField.setText("");
					table.setModel(new DefaultTableModel(new Object[][] {},cabecero));
				} catch(Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar limpiar.");
					System.out.println(error.getMessage());
				}
			}
		});
		btnLimpiar.setBounds(316, 49, 117, 29);
		contentPane.add(btnLimpiar);
		
		/* AGREGAR PUNTO */
		btnAgregarPunto = new JButton("Agregar Punto");
		btnAgregarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// VALIDAR QUE LAS COORDENADAS NO ESTEN VACIAS
					if (xTextField.getText().isEmpty() || yTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "No debe haber coordenadas vacias.");
					} else {
	
						Double[] punto = new Double[2];
						punto[0] = Double.parseDouble(xTextField.getText());
						punto[1] = Double.parseDouble(yTextField.getText());
						Double[] row = { punto[0], punto[1], 0.0, 0.0 };
						
						trapecioTablaValores.add(row);
						
						/*
						 * EL ARRAYLIST SE DEBE CONVETIR A ARREGLO PARA SER ACEPTADO COMO MODELO DE LA
						 * TABLA
						 */
						trapecioTablaValoresTableModel = new Double[trapecioTablaValores.size()][4];
	
						for (int i = 0; i < trapecioTablaValores.size(); i++) {
							trapecioTablaValoresTableModel[i] = trapecioTablaValores.get(i);
						}
	
						table.setModel(new DefaultTableModel(trapecioTablaValoresTableModel, cabecero));
						
						/*
						 * SE AGREGA LIMITE SUPERIOR
						 */
						if(trapecioTablaValores.size() == 1) {
							limInfTextField.setText(trapecioTablaValores.get(0)[0].toString());
						}
					
					}
				} catch(Exception error) {
					JOptionPane.showMessageDialog(getContentPane(), "Ocurrio un error al intentar agregar el punto.");
					System.out.println(error.getMessage());
				}
			}
		});
		btnAgregarPunto.setBounds(316, 79, 117, 29);
		btnAgregarPunto.setVisible(false);
		contentPane.add(btnAgregarPunto);
		
		xLabel = new JLabel("X:");
		xLabel.setBounds(115, 83, 21, 16);
		xLabel.setVisible(false);
		contentPane.add(xLabel);
		
		xTextField = new JTextField();
		xTextField.setText("0");
		xTextField.setColumns(10);
		xTextField.setBounds(148, 78, 43, 26);
		xTextField.setVisible(false);
		contentPane.add(xTextField);
		
		yLabel = new JLabel("Y:");
		yLabel.setBounds(202, 83, 21, 16);
		yLabel.setVisible(false);
		contentPane.add(yLabel);
		
		yTextField = new JTextField();
		yTextField.setText("0");
		yTextField.setColumns(10);
		yTextField.setBounds(235, 78, 43, 26);
		yTextField.setVisible(false);
		contentPane.add(yTextField);
		
		/* TITULO DE VENTANA */
		switch (tipoMetodo) {
			case Trapecio:
				setTitle("Trapecio");
			break;
			case TrapecioTablaValores:
				setTitle("Trapecio (Tabla de valores)");
				lblNmeroDePuntos.setVisible(false);
				numPuntosTextField.setVisible(false);
				xLabel.setVisible(true);
				xTextField.setVisible(true);
				yLabel.setVisible(true);
				yTextField.setVisible(true);
				funcionTextField.setVisible(false);
				lblFuncin.setVisible(false);
				limSupTextField.setEnabled(false);
				limInfTextField.setEnabled(false);
				btnAgregarPunto.setVisible(true);
			break;
			case SimpsonUnTercio:
				setTitle("Simpson 1/3");
			break;
			case SimpsonTresOctavos:
				setTitle("Simpson 3/8");
			break;
			default:
			break;
		}
		
		/* INICIALIZAR VALORES */
		limSupTextField.setText("0");
		limInfTextField.setText("0");
		funcionTextField.setText("");
		numPuntosTextField.setText("0");
	}
	
	private boolean validar(IntegracionEnum tipoMetodo) {
		String funcion = funcionTextField.getText().trim();
		Double limInf = Double.parseDouble(limInfTextField.getText());
	    Double limSup = Double.parseDouble(limSupTextField.getText());
	    Integer numPuntos = Integer.parseInt(numPuntosTextField.getText());
		if(limInf - limSup == 0) {
			JOptionPane.showMessageDialog(getContentPane(), "La diferencia entre los limites de la integral debe ser mayor a 0.");
			return false;
		} else if(funcion.isEmpty() && tipoMetodo != IntegracionEnum.TrapecioTablaValores) {
			JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar un función antes de continuar.");
			return false;
		} else if(numPuntos <= 0 && tipoMetodo != IntegracionEnum.TrapecioTablaValores) {
			JOptionPane.showMessageDialog(getContentPane(), "Debe ingresar una cantidad de puntos mayor a 0.");
			return false;
		} else if(numPuntos % 2 == 0 && tipoMetodo == IntegracionEnum.SimpsonUnTercio) {
    			JOptionPane.showMessageDialog(getContentPane(), "El numero de puntos debe ser impar.");
    			return false;
		} else if(numPuntos % 3 != 0 && tipoMetodo == IntegracionEnum.SimpsonTresOctavos) {
			JOptionPane.showMessageDialog(getContentPane(), "El numero de puntos debe ser multiplo de 3.");
			return false;
		}
		return true;
	}
}
