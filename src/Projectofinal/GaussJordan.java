package Projectofinal;

import java.util.Arrays;

public class GaussJordan {
	
	private Double[][] originalMatrix;
	private Double[][] solutionMatrix;
	
	public GaussJordan(Double[][] om) {
		this.originalMatrix = new Double[om.length][];
		for(int i = 0; i < om.length; i++) {
			this.originalMatrix[i] = om[i].clone();
		}
	}
	
	public static void displayMatrix(Double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public Double[][] solve(Double[][] matrix) throws Exception {	
		double fm = 0;// FACTOR MULTIPLICADOR
		
		this.solutionMatrix = new Double[matrix.length][];
		for(int i = 0; i < matrix.length; i++) {
			this.solutionMatrix[i] = matrix[i].clone();
		}
		
		for(int i = 0; i <solutionMatrix.length;  i++) {
			double pivote = solutionMatrix[i][i];
			for(int j = 0; j < solutionMatrix[0].length; j++) {
				System.out.println("i: " + i + " j: " + j);
				displayMatrix(solutionMatrix);
				solutionMatrix[i][j] = solutionMatrix[i][j]/pivote;
			}
			
			System.out.println("Se dividio renglon:");
			displayMatrix(solutionMatrix);
			
			System.out.println("Llega a segundo for");
			for(int k = 0; k < solutionMatrix.length; k++) {
				if(k != i) {
					fm = solutionMatrix[k][i];
					for(int j = 0; j < solutionMatrix[0].length; j++) {
						solutionMatrix[k][j] = solutionMatrix[k][j] - (fm * solutionMatrix[i][j]);
					}
				}
			}
		}
		displayMatrix(solutionMatrix);
		return solutionMatrix;
	}
	
	public Double[] solution(Double[][] matrix) {
		Double[] result = new Double[matrix.length];
		
		for(int i = 0; i < matrix.length; i++) {
			result[i] = matrix[i][matrix[0].length-1];
		}
		
		System.out.println("Solución: ");
		System.out.println(Arrays.toString(result));
		
		return result;
	}
	

}
