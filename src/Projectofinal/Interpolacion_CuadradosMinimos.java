package Projectofinal;

public class Interpolacion_CuadradosMinimos {

	private Double[] A;
	private Double[] B;
	private Double[][] C;
	private Double[] res;
	private Double[][] Table;
	private int Orden;

	public Interpolacion_CuadradosMinimos(Double[][] Table, int Orden) throws Exception {

		A = new Double[(Orden * 2) + 1];
		B = new Double[Orden + 1];
		C = new Double[Orden + 1][Orden + 2];
		res = new Double[Orden + 1];
		this.Table = Table;
		this.Orden = Orden;
		B[0] = (double) 0;
		A[0] = (double) Table.length;
		// Inicializar sumatorias
		for (int i = 1; i < A.length; i++) {

			A[i] = (double) 0;
		}
		for (int i = 1; i < B.length; i++) {

			B[i] = (double) 0;
		}
		// CALCULA SUMATORIAS DE LAS Y
		for (int i = 0; i <= Table.length - 1; i++) {
			B[0] = B[0] + Table[i][1];
			System.out.print("  " + B[0]);

			// Calcular Sumatorias de las X ala M potencia

			for (int j = 1; j < A.length; j++) {

				A[j] = A[j] + Math.pow(Table[i][0], (double) j);

			}
			// Calcular Sumatoria de Los Producto

			for (int j = 1; j <= Orden; j++) {
				B[j] = B[j] + (Table[i][1] * Math.pow(Table[i][0], j));

			}

		}
		// Calcular matriz De ecuaciones lineales
		for (int i = 1; i <= Orden + 1; i++) {
			for (int j = 1; j <= Orden + 1; j++) {

				C[i - 1][j - 1] = A[i + j - 2];

			}
			C[i - 1][Orden + 1] = B[i - 1];

		}

		// llamar gauss jordan para resolver el sistema de Ecuaciones lineales
		GaussJordan Gauss = new GaussJordan(C);
		Double[][] MatrixFinal = Gauss.solve(C);
		res = Gauss.solution(MatrixFinal);
	}

	public double Solve(double X) {
		double solucion = 0;
		for (int i = Orden; i >= 0; i--) {

			solucion = solucion + (res[i] * Math.pow(X, i));

		}
		return solucion;

	}

}
