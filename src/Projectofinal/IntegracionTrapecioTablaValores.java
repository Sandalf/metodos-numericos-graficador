package Projectofinal;

public class IntegracionTrapecioTablaValores {
	
	private Funcion funcion;
	private Double limInf;
	private Double limSup;
	private int numPuntos;
	private Double[][] tabla;
	private Double[][] puntos;
	
	public IntegracionTrapecioTablaValores(String funcion, Double limInf, Double limSup, Double[][] _puntos_) {
		super();
		this.funcion = new Funcion(funcion);
		this.limInf = limInf;
		this.limSup = limSup;
		this.numPuntos = _puntos_[0].length;
		this.tabla = new Double[2][numPuntos];
		this.puntos = new Double[2][numPuntos];
		
		for(int i = 0; i < puntos.length; i++) {
			puntos[0] = _puntos_[0].clone();
		}
	}

	public Double solve() throws Exception {
			Double[] productos = new Double[numPuntos];
			Double area = new Double(0);
			Double h = (limSup - limInf) / (numPuntos - 1);
			Double x = limInf;
			
			
			for(int i = 0; i < this.puntos[0].length; i++) {
				productos[i] = funcion.eval(x);
				
				if(i == 0 ||  i == (numPuntos-1)) {
					area += productos[i];
					tabla[i][2] = 1.0; // Factor multiplicador
					tabla[i][3] = productos[i]; // Producto
				} else {
					area += 2 * productos[i];
					tabla[i][2] = 2.0; // Factor multiplicador
					tabla[i][3] = 2 * productos[i]; // Producto
				}
			}
			
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
