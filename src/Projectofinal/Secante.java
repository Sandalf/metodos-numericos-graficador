package Projectofinal;

public class Secante {
   private String f;
   private double xI; // elemento pivote
   private double xImas1;
   private double error;
   private Double[][] Resultados;
   private double Raiz;
   private int Lim;
   private boolean Bandera;
public Secante(String f, double xI, double xImas1, double error, int lim) 
{
	
	this.f = f;
	this.xI = xI;
	this.xImas1 = xImas1;
	this.error = error;
	this.Lim = lim;
	Resultados = new Double [Lim][8];
	Bandera=false;
	
}
    public void inicia() throws Exception 
    {
    	
    	double E=Math.abs(xImas1-xI);
		  double xImas2;
		  int x=0;
		  Funcion F = new Funcion(f);
		
		do {
			
			
			   Resultados [x][0]=(double) x;
			    Resultados [x][1]=xI;
				Resultados [x][2]=xImas1;
				Resultados [x][3]=F.eval(xI);
				Resultados [x][4]=F.eval(xImas1);
				Resultados [x][5]= xImas1-F.eval(xImas1)*(xImas1-xI)/(F.eval(xImas1)-F.eval(xI));
				Resultados [x][6]=E;

				
		 
			
			xImas2 = xImas1-F.eval(xImas1)*(xImas1-xI)/(F.eval(xImas1)-F.eval(xI));
			
			
			       Resultados [x][7]=F.eval(xImas2);
			
			E=Math.abs(xImas2-xImas1);


			
			xI=xImas1;
			xImas1=xImas2;
			
			
			
			x++;
			if(x>=Lim) 
			{
				break;
			}
			
		}while(Math.abs(E)>error);
		if(Math.abs(E)>error) 
		{
			Bandera=true;
		}
		
		Raiz= xImas2;
    }
    	
    
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public double getxI() {
		return xI;
	}
	public void setxI(double xI) {
		this.xI = xI;
	}
	public double getxImas1() {
		return xImas1;
	}
	public void setxImas1(double xImas1) {
		this.xImas1 = xImas1;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
	public Double[][] getResultados() {
		return Resultados;
	}
	public void setResultados(Double[][] resultados) {
		Resultados = resultados;
	}
	public double getRaiz() {
		return Raiz;
	}
	public void setRaiz(double raiz) {
		Raiz = raiz;
	}
	public int getLim() {
		return Lim;
	}
	public void setLim(int lim) {
		Lim = lim;
	}
	public boolean getBandera() {
		return Bandera;
	}
	public void setBandera(boolean bandera) {
		Bandera = bandera;
	}
   

	
	
	

}
