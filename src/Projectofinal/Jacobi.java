package Projectofinal;

import java.util.Arrays;

public class Jacobi {

	
	public Jacobi() { }
	
	public Double[][] solve(Double[][] matrix, int numVars, double error, int maxIterations) {
		Double[][] result = new Double[numVars+numVars+1][maxIterations];
		boolean end;
		double[] xVars = new double[numVars];
		double[] yVars = new double[numVars];
		double delta = 0;
		double[] iterationResult = new double[numVars+numVars+1];
		int iterations = 0;
		
		for(int i = 0; i < numVars; i++) {
			xVars[i] = 0;
		}
		
		do {
			iterations++;
			end = true;
			for(int i = 0; i < numVars; i++) {
				yVars[i] = matrix[i][numVars];
				for(int j = 0; j < numVars; j++) {
					if(j != i) {
						yVars[i] = yVars[i] - (xVars[j] * matrix[i][j]);
					}
				}
				
				yVars[i] = yVars[i] / matrix[i][i];
				delta = Math.abs(xVars[i]-yVars[i]);
				
				if(delta <= error || iterations ==  maxIterations) {
					end = false;
				}
			}
			
			for(int i = 0; i < iterationResult.length; i++) {
				if(i < numVars) {
					result[iterations-1][i] = xVars[i];			
				} else if(i < (numVars*2)) {
					result[iterations-1][i] = yVars[i-numVars];
				} else {
					result[iterations-1][i] = delta;
				}
			}
			
			for(int i = 0; i < numVars; i++) {
				xVars[i] = yVars[i];
			}
			
			System.out.println(Arrays.toString(xVars));
			
		} while(end);
		

		return result;
	}

}
