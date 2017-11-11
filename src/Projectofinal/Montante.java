package Projectofinal;

import java.util.Arrays;

public class Montante {
	
	private double[][] originalMatrix;
	private double[][] solutionMatrix;
	
	public Montante(double[][] om) {
		this.originalMatrix = new double[om.length][];
		for(int i = 0; i < om.length; i++) {
			this.originalMatrix[i] = om[i].clone();
		}
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
	
	public double[][] solve(double[][] matrix) {
		double pivoteAnt = 1;
		
		this.solutionMatrix = new double[matrix.length][];
		for(int i = 0; i < matrix.length; i++) {
			this.solutionMatrix[i] = matrix[i].clone();
		}
		
		for(int k = 0; k < solutionMatrix.length; k++) {
			for(int i = 0; i < solutionMatrix.length; i++) {
				if(i != k) {
					for(int j = solutionMatrix[0].length - 1; j >= 0; j--) {
						solutionMatrix[i][j] = ((solutionMatrix[k][k] * solutionMatrix[i][j])-(solutionMatrix[i][k] * solutionMatrix[k][j]))/pivoteAnt;
					}
				}
			}
			pivoteAnt = solutionMatrix[k][k];
		}
		
		System.out.println("-- SOLUTION MATRIX --");
		displayMatrix(solutionMatrix);
		
		return solutionMatrix;
	}
	
	public double[] solution(double[][] matrix) {
		double[] result = new double[matrix.length];	
		double determinante = matrix[0][0];
		
		for(int i = 0; i < matrix.length; i++) {
			result[i] = matrix[i][matrix[0].length-1] / determinante;
		}
		
		System.out.println("Solución: ");
		System.out.println(Arrays.toString(result));
		
		return result;
	}

	public double[][] getOriginalMatrix() {
		return originalMatrix;
	}

	public void setOriginalMatrix(double[][] originalMatrix) {
		this.originalMatrix = originalMatrix;
	}

	public double[][] getSolutionMatrix() {
		return solutionMatrix;
	}

	public void setSolutionMatrix(double[][] solutionMatrix) {
		this.solutionMatrix = solutionMatrix;
	}	

}
