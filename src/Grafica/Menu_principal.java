package Grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Projectofinal.DerivacionEnum;
import Projectofinal.EcDiferencialesEnum;
import Projectofinal.IntegracionEnum;
import Projectofinal.InterpolacionEnum;
import Projectofinal.MetodoMatrizEnum;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;

public class Menu_principal {

	public JFrame Menu;
	public static Double [][] MatrizAux;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal window = new Menu_principal();

					window.Menu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu_principal() {
		initialize();
	
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Menu = new JFrame();
		Menu.setBounds(100, 130, 580, 500);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		Menu.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 560, 21);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Metodos de Raices");
		menuBar.add(mnNewMenu);

		JMenu mnMatrices = new JMenu("Matrices");
		menuBar.add(mnMatrices);

		JMenuItem mntmGauss = new JMenuItem("Gauss");
		mntmGauss.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS,MatrizAux);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(mntmGauss);

		JMenuItem mntmGaussJordan = new JMenuItem("Gauss Jordan");
		mntmGaussJordan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS_JORDAN,MatrizAux);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(mntmGaussJordan);

		JMenuItem mntmMontante = new JMenuItem("Montante");
		mntmMontante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS.MONTANTE,MatrizAux);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(mntmMontante);

		JMenuItem mntmCramer = new JMenuItem("Cramer");
		mntmCramer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.CRAMER,MatrizAux);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(mntmCramer);

		JMenuItem mntmJacobi = new JMenuItem("Jacobi");
		mntmJacobi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.JACOBI,MatrizAux);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(mntmJacobi);
		
		JMenuItem mntmGaussSeidel = new JMenuItem("Gauss Seidel");
		mntmGaussSeidel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matriz matriz = new Matriz(MetodoMatrizEnum.GAUSS_SEIDEL,MatrizAux);
				matriz.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnMatrices.add(mntmGaussSeidel);

		JMenuItem mntmBisseccion = new JMenuItem("Bisseccion");
		mntmBisseccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				biseccion B = new biseccion();
				B.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(mntmBisseccion);

		JMenuItem mntmFalsaPosicion = new JMenuItem("Falsa Posicion");
		mntmFalsaPosicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Falsa_posicionI Falsa = new Falsa_posicionI();
				Falsa.main(null);
				Menu.setVisible(false);

			}
		});
		mnNewMenu.add(mntmFalsaPosicion);

		JMenuItem mntmSecante = new JMenuItem("Secante");
		mntmSecante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecanteI S = new SecanteI();
				S.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(mntmSecante);

		JButton btnAproximaciones = new JButton("Aproximaciones");
		btnAproximaciones.setAlignmentX(1);
		btnAproximaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Aproximaciones_Sucesivas S = new Aproximaciones_Sucesivas();
				S.main(null);
				Menu.setVisible(false);

			}
		});

		JMenuItem mntmAproximacionesSucesivas = new JMenuItem("Aproximaciones Sucesivas");
		mntmAproximacionesSucesivas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Aproximaciones_Sucesivas S = new Aproximaciones_Sucesivas();
				S.main(null);
				Menu.setVisible(false);

			}
		});
		mnNewMenu.add(mntmAproximacionesSucesivas);

		JMenuItem mntmNewtonraphson = new JMenuItem("Newton Raphson");
		mntmNewtonraphson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Newton_Raphson N = new Newton_Raphson();

				N.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewtonraphson);

		JMenuItem mntmNewtonsegundoorden = new JMenuItem("Newton Segundo Orden");
		mntmNewtonsegundoorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Newton_SegundoOrdenI N = new Newton_SegundoOrdenI();
				N.main(null);
				Menu.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewtonsegundoorden);

		JMenu mnInterpolacin = new JMenu("Interpolacion");
		menuBar.add(mnInterpolacin);

		JMenuItem mntmnewton = new JMenuItem("Newton");
		mntmnewton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interpolacion interpolacion = new Interpolacion(InterpolacionEnum.InterpolacionNewton);
				interpolacion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnInterpolacin.add(mntmnewton);

		JMenuItem mntmLagrange = new JMenuItem("Lagrange");
		mntmLagrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Interpolacion Lagrange = new Interpolacion(InterpolacionEnum.Interpolacion_Lagrange);
				Lagrange.setVisible(true);
				Menu.setVisible(false);

			}
		});
		mnInterpolacin.add(mntmLagrange);

		JMenuItem mntmMinimosCuadrados = new JMenuItem("Minimos Cuadrados");
		mntmMinimosCuadrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Interpolacion Cuadrados = new Interpolacion(InterpolacionEnum.Interpolacion_CuadradrosMinimos);
				Cuadrados.setVisible(true);
				Menu.setVisible(false);

			}
		});
		mnInterpolacin.add(mntmMinimosCuadrados);

		JMenu mnDerivacin = new JMenu("Derivacion");
		menuBar.add(mnDerivacin);

		JMenuItem mntmDiferenciasFinitas = new JMenuItem("Diferencias finitas");
		mntmDiferenciasFinitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Derivacion derivacion = new Derivacion(DerivacionEnum.DiferenciasFinitas);
				derivacion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnDerivacin.add(mntmDiferenciasFinitas);

		JMenuItem mntmPorLimites = new JMenuItem("Por Limites");
		mntmPorLimites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DerivacionPorLimites derivacion = new DerivacionPorLimites();
				derivacion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnDerivacin.add(mntmPorLimites);

		JMenu mnIntegracin = new JMenu("Integracion");
		menuBar.add(mnIntegracin);

		JMenuItem mntmTrapecio = new JMenuItem("Trapecio");
		mntmTrapecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integracion integracion = new Integracion(IntegracionEnum.Trapecio);
				integracion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnIntegracin.add(mntmTrapecio);

		JMenuItem mntmTrapecioTablaValores = new JMenuItem("Trapecio (Tabla de Valores)");
		mntmTrapecioTablaValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integracion integracion = new Integracion(IntegracionEnum.TrapecioTablaValores);
				integracion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnIntegracin.add(mntmTrapecioTablaValores);

		JMenuItem mntmSimpsion = new JMenuItem("Simpson 1/3");
		mntmSimpsion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integracion integracion = new Integracion(IntegracionEnum.SimpsonUnTercio);
				integracion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnIntegracin.add(mntmSimpsion);

		JMenuItem mntmSimpsonTresOctavos = new JMenuItem("Simpson 3/8");
		mntmSimpsonTresOctavos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integracion integracion = new Integracion(IntegracionEnum.SimpsonTresOctavos);
				integracion.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnIntegracin.add(mntmSimpsonTresOctavos);

		JMenu mnEcuacionesDiferenciales = new JMenu("Ecuaciones Diferenciales");
		menuBar.add(mnEcuacionesDiferenciales);

		JMenuItem mntmEuler = new JMenuItem("Euler");
		mntmEuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EcDiferenciales Ec = new EcDiferenciales(EcDiferencialesEnum.Euler);
				Ec.setVisible(true);
				Menu.setVisible(false);

			}
		});
		mnEcuacionesDiferenciales.add(mntmEuler);

		JMenuItem mntmEulermejorado = new JMenuItem("Euler Mejorado");
		mntmEulermejorado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				EcDiferenciales Ec = new EcDiferenciales(EcDiferencialesEnum.EulerMejorado);
				Ec.setVisible(true);
				Menu.setVisible(false);
			}
		});
		mnEcuacionesDiferenciales.add(mntmEulermejorado);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Metodos Numericos");
		lblNewJgoodiesTitle.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		lblNewJgoodiesTitle.setBounds(161, 52, 259, 39);
		panel.add(lblNewJgoodiesTitle);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Integrantes");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewJgoodiesLabel.setBounds(225, 244, 137, 21);
		panel.add(lblNewJgoodiesLabel);
		
		JLabel lblProfesor = DefaultComponentFactory.getInstance().createTitle("Profesor :  Jesus Astolgo Rodriguez Valenzuela");
		lblProfesor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProfesor.setBounds(100, 102, 381, 28);
		panel.add(lblProfesor);
		
		JLabel lblCarreraIngEn = DefaultComponentFactory.getInstance().createTitle("Carrera: Ing. en Sistemas Computacionales");
		lblCarreraIngEn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCarreraIngEn.setBounds(123, 152, 321, 21);
		panel.add(lblCarreraIngEn);
		
		JLabel lblSemestre = DefaultComponentFactory.getInstance().createLabel("Semestre 99");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSemestre.setBounds(218, 184, 160, 36);
		panel.add(lblSemestre);
		
		JLabel lblNewJgoodiesTitle_1 = DefaultComponentFactory.getInstance().createTitle("Dinorah Estephania Guzman Alvarado");
		lblNewJgoodiesTitle_1.setBounds(168, 276, 223, 14);
		panel.add(lblNewJgoodiesTitle_1);
		
		JLabel lblLuisAlfonsoSandoval = DefaultComponentFactory.getInstance().createTitle("Luis Alfonso Sandoval Huerta");
		lblLuisAlfonsoSandoval.setBounds(187, 301, 204, 14);
		panel.add(lblLuisAlfonsoSandoval);
		
		JLabel lblCesarLudovicoAngulo = DefaultComponentFactory.getInstance().createTitle("Cesar Ludovico Angulo Castro");
		lblCesarLudovicoAngulo.setBounds(188, 326, 245, 14);
		panel.add(lblCesarLudovicoAngulo);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public static void setMatrixOriginal(Double[][] matrixAux) {
		MatrizAux=matrixAux;
		
	}
}