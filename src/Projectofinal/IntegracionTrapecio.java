package Projectofinal;

public class IntegracionTrapecio {
	
	private Funcion funcion;
	private Double limInf;
	private Double limSup;
	private int numPuntos;
	private Double[][] tabla;
	
	public IntegracionTrapecio(String funcion, Double limInf, Double limSup, int numPuntos) {
		super();
		this.funcion = new Funcion(funcion);
		this.limInf = limInf;
		this.limSup = limSup;
		this.numPuntos = numPuntos;
		this.tabla = new Double[numPuntos][4];
	}

	public Double solve() throws Exception {
			Double[] ordenadas = new Double[numPuntos];
			Double area = new Double(0);
			Double h = (limSup - limInf) / (numPuntos - 1);
			Double x = limInf;
			int i = 0;
			
			do {
				ordenadas[i] = funcion.eval(x);
				
				if(i == 0 ||  i == (numPuntos-1)) {
					area += ordenadas[i];
					tabla[i][2] = 1.0; // Factor multiplicador
					tabla[i][3] = ordenadas[i]; // Producto
				} else {
					area += 2 * ordenadas[i];
					tabla[i][2] = 2.0; // Factor multiplicador
					tabla[i][3] = 2 * ordenadas[i]; // Producto
				}
				
				tabla[i][0] = x; // Columna x
				tabla[i][1] = ordenadas[i]; // Columna y
				
				x += h;
				i++;
			} while(x <= limSup);
			
			area = area * (h/2);
			
			return area;		
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
