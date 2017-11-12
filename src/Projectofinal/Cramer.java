package Projectofinal;

import java.util.ArrayList;
import java.util.Arrays;

public class Cramer {
	
	public Cramer() {
		
	}
	
	public static void displayMatrix(double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public double[][] solve(double[][] matrix, double[] soluciones, ArrayList<double[][]> matrices) {
		double[][] coeficientes = new double[matrix.length][matrix[0].length-1];
		double[] constantes = new double[matrix.length];
		double[][] matrixCopy = new double[coeficientes.length][coeficientes[0].length];
		double[] detx = new double[coeficientes.length];
		double det = 0;
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				/* EXTRAER COEFICIENTES DE MATRIZ Y COPIAR MATRIZ */
				if(j < coeficientes[0].length) {
					matrixCopy[i][j] = coeficientes[i][j] = matrix[i][j];	
				}
				
				/* EXTRAER CONSTANTES DE MATRIZ */
				if(j == matrix[0].length - 1) {
					constantes[i] =  matrix[i][matrix[0].length-1];
				}
			}
		}
		
		det = matrixDeterminant(coeficientes);
	
		System.out.println("Determinante: " + det + "\n");
		
		System.out.println("Matriz de coeficientes:");
		displayMatrix(coeficientes);
		
		System.out.println("Constantes:" + Arrays.toString(constantes) + "\n");
		
		if (det == 0) {
			return null;
		} else {
			for(int j = 0; j < matrixCopy.length; j++) {
				for(int i = 0; i < matrixCopy.length; i++) {
					matrixCopy[i][j] = constantes[i];
				}
				double[][] m = new double[matrixCopy.length][matrixCopy[0].length]; 
				duplicate(matrixCopy,m);
				displayMatrix(m);
				matrices.add(m);
				detx[j] = matrixDeterminant(matrixCopy);
				System.out.println("X" + (j+1) + ": " + detx[j] + "\n");
				duplicate(coeficientes,matrixCopy);
				soluciones[j] =  detx[j] / det;
				
			}
		}
		
		System.out.println("Solución:" + Arrays.toString(soluciones));
		
		return matrixCopy;
	}
	
	public void duplicate(double[][] org, double[][] cop) {
		for(int i = 0; i < org.length; i++) {
			cop[i] = org[i].clone();
		}
	}
	
	public double matrixDeterminant (double[][] matrix) {
		double temporary[][];
		double result = 0;

		if (matrix.length == 1) {
			result = matrix[0][0];
			return (result);
		}

		if (matrix.length == 2) {
			result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
			return (result);
		}

		for (int i = 0; i < matrix[0].length; i++) {
			temporary = new double[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					if (k < i) {
						temporary[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temporary[j - 1][k - 1] = matrix[j][k];
					}
				}
			}

			result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temporary);
		}
		return (result);
	}
	

}
