package Projectofinal;

import org.apache.commons.math.util.MathUtils;

public class DerivacionDiferenciasFinitas {
	private Double[][] puntos;
	private Double[][] tablaDiferencias;
	
	public DerivacionDiferenciasFinitas(Double[][] p) {
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
	
	/* RESOLVER FUNCION */
	public Double solve(Double x) {
		diferencias();
		Double k = (x - puntos[0][0])/(puntos[1][0] - puntos[0][0]);
		Double h = (puntos[1][0]-puntos[0][0]);
		Double y = new Double(0);
		int lastCol = 0;
		double dif1,dif2,dif3,dif4,dif5;
		
		for(int j = 1; j < tablaDiferencias[0].length; j++) {
			if(tablaDiferencias[0][j] == 0) {
				lastCol = j - 1;
				break;
			}
		}
		
		displayMatrix(tablaDiferencias);
		switch(lastCol) {
			case 1:
				dif1 = tablaDiferencias[0][1];
				y = dif1;
				break;
			case 2:
				dif1 = tablaDiferencias[0][1];
				dif2 = (((2 * k) - 1) * tablaDiferencias[0][2])/MathUtils.factorial((2));
				y = dif1 + dif2;
				break;
			case 3:
				dif1 = tablaDiferencias[0][1];
				dif2 = (((2 * k) - 1) * tablaDiferencias[0][2])/MathUtils.factorial((2));
				dif3 = (((3 * Math.pow(k, 2)) - (6 * k) + 2) * tablaDiferencias[0][3])/MathUtils.factorial((3));
				y = dif1 + dif2 + dif3;
				break;
			case 4:
				dif1 = tablaDiferencias[0][1];
				dif2 = (((2 * k) - 1) * tablaDiferencias[0][2])/MathUtils.factorial((2));
				dif3 = (((3 * Math.pow(k, 2)) - (6 * k) + 2) * tablaDiferencias[0][3])/MathUtils.factorial((3));
				dif4 = (((4 * Math.pow(k, 3) - (18 * Math.pow(k, 3) + (22*k) - 6))) * tablaDiferencias[0][4])/MathUtils.factorial((4));
				y = dif1 + dif2 + dif3 + dif4;
				break;
			case 5:
				dif1 = tablaDiferencias[0][1];
				dif2 = (((2 * k) - 1) * tablaDiferencias[0][2])/MathUtils.factorial((2));
				dif3 = (((3 * Math.pow(k, 2)) - (6 * k) + 2) * tablaDiferencias[0][3])/MathUtils.factorial((3));
				dif4 = (((4 * Math.pow(k, 3) - (18 * Math.pow(k, 3) + (22*k) - 6))) * tablaDiferencias[0][4])/MathUtils.factorial((4));
				dif5 = (((5 * Math.pow(k, 4) - (40 * Math.pow(k, 3) + (150 * Math.pow(k, 2) - (100 * k)))) + 24) * tablaDiferencias[0][5])/MathUtils.factorial((5));
				y = dif1 + dif2 + dif3 + dif4 + dif5;
				break;
			default:
				y = new Double(0);
				break;
		}
		
		y = y / h;
		
		return y;
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
