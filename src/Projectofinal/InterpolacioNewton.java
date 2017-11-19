package Projectofinal;

import org.apache.commons.math.util.MathUtils;

public class InterpolacioNewton {
	
	private static Double[][] puntos;
	private static Double[][] tablaDiferencias;

	public static void main(String[] args) {
		double[][] p = {{0,2},{2,8},{4,62},{6,212},{8,506},{10,992}};
		puntos = new Double[p.length][p[0].length];
		tablaDiferencias = new Double[p.length][p.length];
		
		/* CREARA TABLE DE PUNTOS */
		/* LLENAR COLUMNA 'Y' DE TABLA DE DIFERENCIAS */
		for(int i = 0; i < p.length; i++) {
			for(int j = 0; j < p[0].length; j++) {
				puntos[i][j] = new Double(p[i][j]);
				if(j > 0) {
					tablaDiferencias[i][j-1] = new Double(p[i][j]);
				}
			}
		}	
		
		/* LLENAR TABLA DE DIFERENCIAS ON CEROS */
		for(int i = 0; i < puntos.length; i++) {
			for(int j = 0; j < puntos.length; j++) {
				if(tablaDiferencias[i][j] == null) {
					tablaDiferencias[i][j] = new Double(0);
				}
			}
		}
		
		displayMatrix(tablaDiferencias);
		
		displayMatrix(diferencias());
		
		System.out.println("y="+solve(3.2));
		
	}
	
	/* TABLA DE DIFERENCIAS FINITAS */
	public static Double[][] diferencias() {
		
		int row = 1;
		for(int j = 0; j < tablaDiferencias.length-1; j++) {
			for(int i = 0; i < tablaDiferencias.length-row; i++) {
				tablaDiferencias[i][j+1] = new Double(tablaDiferencias[i+1][j] - tablaDiferencias[i][j]);
			}
			row++;
		}
		
		return tablaDiferencias;
	}
	
	/* RESOLVER FUNCION */
	public static Double solve(Double x) {
		Double k = (x - puntos[0][0])/(puntos[1][0]-puntos[0][0]);
		Double y = new Double(0);
		
		for(int i = 0; i < tablaDiferencias.length;i++) {
			Double num = new Double(1);
			int j = 0;
			while(j <= (i - 1)) {
				num = num * (k-j);
				j++;
			}
			y = y + ((num/MathUtils.factorial((i))) * tablaDiferencias[0][i]);
		}
		
		return y;
	}
	
	/* PINTAR TABLA */
	public static void displayMatrix(Double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}