package Projectofinal;

import org.apache.commons.math.util.MathUtils;

public class InterpolacioNewton {
	
	private Double[][] puntos;
	private Double[][] tablaDiferencias;
	
	public InterpolacioNewton(Double[][] p) {
		puntos = new Double[p.length][p[0].length];
		tablaDiferencias = new Double[p.length][p.length];
		
		/* CREARA TABLA DE PUNTOS */
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
	}
	
	/* RESOLVER FUNCION */
	public Double solve(Double x) {
		diferencias();
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
	
	/* TABLA DE DIFERENCIAS FINITAS */
	public Double[][] diferencias() {
		
		int row = 1;
		for(int j = 0; j < tablaDiferencias.length-1; j++) {
			for(int i = 0; i < tablaDiferencias.length-row; i++) {
				tablaDiferencias[i][j+1] = new Double(tablaDiferencias[i+1][j] - tablaDiferencias[i][j]);
			}
			row++;
		}
		
		return tablaDiferencias;
	}
	
	/* PINTAR TABLA */
	public void displayMatrix(Double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public Double[][] getPuntos() {
		return puntos;
	}

	public void setPuntos(Double[][] puntos) {
		this.puntos = puntos;
	}

	public Double[][] getTablaDiferencias() {
		/* REGRESAR TABLA DE DIFERENCIAS HASTA LA ULTIMA COLUMNA
		 * QUE NO CONTIENE CEROS
		 */	
		int ultimaColumna = 0;
		for(int j = 1; j < tablaDiferencias[0].length; j++ ) {
			if(tablaDiferencias[0][j] == 0) {
				ultimaColumna = j;
				break;
			}
		}
			
		Double[][] tabla = new Double[tablaDiferencias.length][ultimaColumna-1];
		
		for(int i = 0; i < tabla.length; i++ ) {
			for(int j = 0; j < tabla[0].length; j++ ) {
				tabla[i][j] = tablaDiferencias[i][j+1];
			}
		}
		
		return tabla;
	}

	public void setTablaDiferencias(Double[][] tablaDiferencias) {
		this.tablaDiferencias = tablaDiferencias;
	}
	
}