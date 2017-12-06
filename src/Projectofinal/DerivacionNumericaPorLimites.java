package Projectofinal;

import java.util.ArrayList;

public class DerivacionNumericaPorLimites {

	private Double[][] tabla;
	private Funcion funcion;
	private double ep;

	public DerivacionNumericaPorLimites(Funcion funcion, Double error) {
		this.funcion = funcion;
		this.ep = error;
	}

	public Double solve(Double x) throws Exception {
		ArrayList<Double[]> datos = new ArrayList<Double[]>();
		int n = 1;
		Double da = 1 * Math.pow(10, 10);
		Double error = new Double(0);
		Double k;
		Double d;
		Double h;

		do {
			Double[] row = new Double[9];
			h = (Math.pow(0.1, n));
			k = (1 / h);
			d = (funcion.eval(x + h) - funcion.eval(x)) * k;
			error = Math.abs(da - d);
			da = d;

			// Agregar datos de iteracion
			row[0] = (double) n;
			row[1] = h;
			row[2] = k;
			row[3] = (x + h);
			row[4] = funcion.eval(x + h);
			row[5] = funcion.eval(x);
			row[6] = funcion.eval(x + h) - funcion.eval(x);
			row[7] = d;
			row[8] = error;

			datos.add(row);

			n++;
		} while (error > ep);

		setTabla(datos);

		return d;
	}

	public Double[][] getTabla() {
		return tabla;
	}

	public void setTabla(ArrayList<Double[]> datos) {
		this.tabla = new Double[datos.size()][datos.get(0).length];
		int i = 0;

		for (Double[] row : datos) {
			tabla[i] = row.clone();
			i++;
		}
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	/* PINTAR TABLA */
	public static void displayMatrix(Double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}
