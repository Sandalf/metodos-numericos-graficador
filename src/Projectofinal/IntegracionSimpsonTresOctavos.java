package Projectofinal;

public class IntegracionSimpsonTresOctavos {
	
	private Funcion funcion;
	private Double limInf;
	private Double limSup;
	private int numPuntos;
	private Double[][] tabla;
	
	public static void main(String[] args) {
		IntegracionSimpsonTresOctavos integ = new IntegracionSimpsonTresOctavos("1/(1+x)",0.0,1.0,6);
		try {
			integ.solve();
			integ.getTabla();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IntegracionSimpsonTresOctavos(String funcion, Double limInf, Double limSup, int numPuntos) {
		super();
		this.funcion = new Funcion(funcion);
		this.limInf = limInf;
		this.limSup = limSup;
		this.numPuntos = numPuntos;
		this.tabla = new Double[numPuntos+1][4];
	}

	public Double solve() throws Exception {
			Double[] ordenadas = new Double[numPuntos+1];
			Double area = new Double(0);
			Double h = (limSup - limInf) / (numPuntos);
			Double x = limInf;
			int i = 0;
			System.out.println("numPuntos: " + numPuntos);
			do {
				ordenadas[i] = funcion.eval(x);
				
				if(i == 0 ||  i == numPuntos) {
					area += ordenadas[i];
					tabla[i][2] = 1.0; // Factor multiplicador
					tabla[i][3] = ordenadas[i]; // Producto
				} else if (i >= 2 && esMultiplo(i+1,3)) {
					area += 2 * ordenadas[i];
					tabla[i][2] = 2.0; // Factor multiplicador
					tabla[i][3] = 2 * ordenadas[i]; // Producto
				} else if (i >= 1 && !esMultiplo(i+1,3)) {
					area += 3 * ordenadas[i];
					tabla[i][2] = 3.0; // Factor multiplicador
					tabla[i][3] = 3 * ordenadas[i]; // Producto
				}
				
				tabla[i][0] = x; // Columna x
				tabla[i][1] = ordenadas[i]; // Columna y
				
				System.out.println("x: " + x + ", " + "i: " + i + ", " + "area: " + area + ", y: " + ordenadas[i]);
				x += h;
				i++;
			} while(i <= numPuntos);
			/* SE RESUELVE ULTIMA ITERACION POR SEPARADO  POR QUE EL VALOR DE LA X
			 * SE SALE DEL LIMITE SUPERIOR */
			tabla[numPuntos][0] = limSup; // Columna x
			tabla[numPuntos][1] = funcion.eval(limSup); // Columna y
			tabla[numPuntos][2] = 1.0; // Factor multiplicador
			tabla[numPuntos][3] = funcion.eval(limSup); // Producto
			area += funcion.eval(limSup);
			
			area = area * ((3*h)/8);
			
			System.out.println("area: " + area );
			
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
	
	public boolean esMultiplo(int a,int n) {
		if(a % n == 0) {
			return true;
		}
		return false;
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
		displayMatrix(tabla);
		return tabla;
	}

	public void setTabla(Double[][] tabla) {
		this.tabla = tabla;
	}
	
}
