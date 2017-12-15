package Projectofinal;

public class Gauss {
	
	private Double[][] originalMatrix;
	
	public Gauss (Double[][] matrix) {
		this.originalMatrix = matrix;
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
	
	public Double[][] triangulateMatrix(Double[][] matrix) throws Exception  {	
		Double[][] resultado = new Double[matrix.length][matrix[0].length];
		double fm = 0;// FACTOR MULTIPLICADOR
		double ep = 0;// ELEMENTO PIVOTE
		
		// CREAR COPIA DE MATRIZ
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				resultado[i][j] = matrix[i][j];
			}
		}
		
		for(int k = 0;k < resultado.length - 1; k++) {
			ep = resultado[k][k];
			for(int i = k+1; i < resultado.length; i++) {
				fm = resultado[i][k]/ep;
				for(int j = 0; j < resultado[i].length; j++) {
					resultado[i][j] =  resultado[i][j] - (fm*resultado[k][j]);			
				}
			}			
		}
		
		return resultado;
	}
	
	public Double[] calculateSolution(Double[][] matrix) throws Exception {
		Double[] variables = new Double[matrix.length];
		
		// ULTIMA INCOGNITA
		variables[matrix.length-1] = matrix[matrix.length-1][matrix[0].length-1]/matrix[matrix.length-1][matrix[0].length-2];
		
		for(int i = matrix.length - 2; i >= 0; i--) {
			variables[i] = matrix[i][matrix[i].length-1];
			for(int j = matrix[i].length - 2; j >= i; j--) {
				if(i != j) {
					variables[i] = variables[i] - (matrix[i][j]*variables[j]);
				} else {
					variables[i] = variables[i]/matrix[i][j];
				}	
			}
		}	
		return variables;
	}

	public Double[][] getOriginalMatrix() {
		return originalMatrix;
	}

	public void setOriginalMatrix(Double[][] originalMatrix) {
		this.originalMatrix = originalMatrix;
	}

}
