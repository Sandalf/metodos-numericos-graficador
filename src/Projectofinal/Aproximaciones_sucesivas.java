package Projectofinal;

public class Aproximaciones_sucesivas {

	private String exp;
	private double x0;
	private int lim;
	private double error;
	private double Raiz;
	private Double[][] Resultados;
	private Boolean Bandera;

	public Aproximaciones_sucesivas(String exp, double x0, int lim, double error) {

		this.x0 = x0;
		this.exp = exp;
		this.lim = lim;
		this.error = error;
		Resultados = new Double[lim][7];
		Bandera = false;
	}

	public void inicia() throws Exception {
		int x = 0;
		Funcion F = new Funcion(exp);
		Funcion g = new Funcion("x");
		double xI = x0;
		double E = Math.abs((F.eval(xI) + g.eval(xI)) - xI);
		double xImas1;
		do {

			xImas1 = F.eval(xI) + g.eval(xI);
			Resultados[x][0] = ((double) x);
			Resultados[x][1] = xI;
			Resultados[x][2] = xImas1;
			Resultados[x][3] = E;
			Resultados[x][4] = F.eval(xImas1);

			E = Math.abs(xImas1 - xI);
			xI = xImas1;

			x++;
			if (x >= lim) {
				break;
			}

		} while (Math.abs(E) > error);
		if (Math.abs(E) > error) {
			Bandera = true;
		}

		Raiz = xImas1;

	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public int getLim() {
		return lim;
	}

	public void setLim(int lim) {
		this.lim = lim;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public double getRaiz() {
		return Raiz;
	}

	public void setRaiz(double raiz) {
		Raiz = raiz;
	}

	public Object[][] getResultados() {
		return Resultados;
	}

	public void setResultados(Double[][] resultados) {
		Resultados = resultados;
	}

	public Boolean getBandera() {
		return Bandera;
	}

	public void setBandera(Boolean bandera) {
		Bandera = bandera;
	}

}
