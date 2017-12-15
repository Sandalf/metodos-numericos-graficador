package Projectofinal;

import java.util.ArrayList;

public class GaussSeidel {
	
	private Double[][] tabla;
	private ArrayList<Double[]> datos;
	
	public GaussSeidel() {
		this.datos = new ArrayList<Double[]>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GaussSeidel gaussSeidel = new GaussSeidel();
		Double[][] m = {{4.0,-1.0,1.0,3.0},{-1.0,4.0,1.0,-2.0},{2.0,1.0,5.0,3.0}};
		try {
			gaussSeidel.solve(m, 0.001);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayMatrix(gaussSeidel.getTabla());
		//solve(m);
	}
	
	public Double[][] solve(Double[][] matrix, Double ep) throws Exception {
		int xVars = matrix.length;
		Double[] row = new Double[(xVars*2)+1];
		Double[] x = new Double[xVars];
		Double delta = new Double(0);
		Double error = ep;
		boolean fin = false;
		int iterador = 0;
		
		do {
			row = new Double[(xVars*2)+1];
			
			/* INICIALIZAR VARIABLES EN CEROS */
			if(iterador == 0) {
				for(int h = 0; h < x.length; h++) {
					row[h] = x[h] = new Double(0);
				}
			}
			
			for(int i = 0; i < matrix.length; i++) {
				Double y = new Double(matrix[i][matrix[0].length-1]);
				for(int j = 0; j < matrix[0].length-1; j++) {
					if(i != j) {
						y -= (matrix[i][j]*x[j]);
					}					
				}
				
				y = y/matrix[i][i];
				row[i+xVars] = y;
				delta = Math.abs(x[i]-y);
				
				if(delta <= error) {
					fin = true;
				}
				
				/* GUARDAR CALCULO ANTERIOR */
				row[i] = x[i];
				x[i] = y;
			}
			
			/* INSERTAR ERROR */
			row[row.length-1] = delta;
			
			datos.add(row);
			
			iterador++;
			
			if (iterador > 100000) {
				throw new Exception(); 
			}
			
		}while(!fin);
		
		this.setTabla(datos);
		
		return getTabla();
	}
	
	public void setTabla(ArrayList<Double[]> datos) {
		tabla = new Double[datos.size()][datos.get(0).length];
		int i = 0;

		for (Double[] row : datos) {
			tabla[i] = row.clone();
			i++;
		}
	}
	
	public Double[][] getTabla() {
		return this.tabla;
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
