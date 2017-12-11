package Projectofinal;

import java.util.ArrayList;

public class GaussSeidel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double[][] m = {{4.0,-1.0,1.0,3.0},{-1.0,4.0,1.0,-2.0},{2.0,1.0,5.0,3.0}};
		solve(m);
	}
	
	public static Double[][] solve(Double[][] matrix) {
		ArrayList<Double[]> tabla = new ArrayList<Double[]>();
		Double[] row = new Double[6];
		Double error = new Double(0);
		boolean fin = false;
		int iterador = 0;
		
		for(int index = 0; index < row.length; index++) {
			if(index <= 2) {
				row[index] = new Double(0);
			}
		}
		
		for(int i = 0; i < matrix.length; i++) {
			Double y = new Double(matrix[i][matrix[0].length-1]);
			//System.out.println("y1: " + y);
			for(int j = 3; j < matrix[0].length; j++) {
				if(i != j) {
					//System.out.println("y -= ("+matrix[i][j]+"*"+row[j-3]+")");
					y -= (matrix[i][j]*row[j-3]);
				}					
			}
			//System.out.println("y: " + y + " ,mii: " + matrix[i][i]);
			row[i+3] = y/matrix[i][i];
		}
		
		tabla.add(row);
		
		setTabla(tabla);
		
		do {
			row = new Double[6];
			
			for(int i = 0; i < matrix.length; i++) {
				
			}
			
			iterador++;
		}while(fin);
		
		
		return null;
	}
	
	public static void setTabla(ArrayList<Double[]> datos) {
		Double[][] tabla = new Double[datos.size()][datos.get(0).length];
		int i = 0;

		for (Double[] row : datos) {
			tabla[i] = row.clone();
			i++;
		}
		
		displayMatrix(tabla);
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
