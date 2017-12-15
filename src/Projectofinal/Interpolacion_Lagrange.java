package Projectofinal;

public class Interpolacion_Lagrange {
	private Double[][] puntos;

	public Interpolacion_Lagrange(Double[][] p) {

		puntos = p;

	}

	public double solve(Double x) {
		double Y = 0;
		for (int i = 0; i < puntos.length; i++) {
			double num = 1;
			double den = 1;
			for (int j = 0; j < puntos.length; j++) {
				if (i != j) {
					num = num * (x - puntos[j][0]);
					den = den * (puntos[i][0] - puntos[j][0]);
				}

			}

			Y = Y + num / den * puntos[i][1];
		}
		return Y;

	}

}
