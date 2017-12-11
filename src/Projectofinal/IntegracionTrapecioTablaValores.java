package Projectofinal;

public class IntegracionTrapecioTablaValores {
	
	private Funcion funcion;
	private Double limInf;
	private Double limSup;
	private int numPuntos;
	private Double[][] tabla;
	private Double[][] puntos;
	
	public static void main(String[] args) {
		Double[][] puntos = {{1.0,1.0,0.0,0.0},{1.2,1.49,0.0,0.0},{1.4,1.96,0.0,0.0},{1.6,2.56,0.0,0.0},{1.8,3.24,0.0,0.0},{2.0,4.0,0.0,0.0}};
		IntegracionTrapecioTablaValores integ = new IntegracionTrapecioTablaValores(puntos);
		try {
			integ.solve();
			integ.getTabla();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IntegracionTrapecioTablaValores(Double[][] _puntos_) {
		super();
		this.limInf = _puntos_[0][0];
		this.limSup = _puntos_[_puntos_.length-1][0];
		this.numPuntos = _puntos_.length;
		this.tabla = new Double[numPuntos][4];
		this.puntos = new Double[numPuntos][4];
		
		for(int i = 0; i < puntos.length; i++) {
			puntos[i] = _puntos_[i].clone();
			tabla[i] =_puntos_[i].clone();
		}
	}

	public Double solve() throws Exception {
			Double area = new Double(0);
			Double h = (limSup - limInf) / (numPuntos - 1);	
			
			for(int i = 0; i < this.puntos.length; i++) {	
				if(i == 0 ||  i == (numPuntos-1)) {
					area += puntos[i][1];
					tabla[i][2] = 1.0; // Factor multiplicador
					tabla[i][3] = puntos[i][1]; // Producto
				} else {
					area += 2 * puntos[i][1];
					tabla[i][2] = 2.0; // Factor multiplicador
					tabla[i][3] = 2 * puntos[i][1]; // Producto
				}
			}
			
			area = area * (h/2);
			displayMatrix(getTabla());
			System.out.println("area: " + area + ", h: " + h);
			return area;		
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

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Double getLimInf() {
		return limInf;
	}

	public void setLimInf(Double limInf) {
		this.limInf = limInf;
	}

	public Double getLimSup() {
		return limSup;
	}

	public void setLimSup(Double limSup) {
		this.limSup = limSup;
	}

	public int getNumPuntos() {
		return numPuntos;
	}

	public void setNumPuntos(int numPuntos) {
		this.numPuntos = numPuntos;
	}

	public Double[][] getTabla() {
		return tabla;
	}

	public void setTabla(Double[][] tabla) {
		this.tabla = tabla;
	}
	
}
