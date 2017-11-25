package Projectofinal;

public class newton_raphson {
	private String Funcion;
	private double XI;
	private double error;
	private int Lim;
	private double Raiz;
	private Double Resultados [][];
	private Boolean Bandera;
	
	public newton_raphson(String F,double XI,double E,int Ite) 
	{
		
		Funcion=F;
		this.XI=XI;
		error=E;
		Lim=Ite;
		Resultados=new Double [Lim][7];
		Bandera=false;
		
		
		
		
	}
	public void inicia() throws Exception
	{
		int x=0;
		 Funcion f= new Funcion(Funcion);
	      String Derivada=f.derivada();
	      Funcion dif= new Funcion(Derivada);
	      double xImas1;
	      double E=Math.abs((XI-f.eval(XI)/dif.eval(XI)-XI));
	      do {
	    	  xImas1 = XI-(f.eval(XI)/dif.eval(XI));
	    	  
	    	    Resultados [x][0]=((double)x);
				Resultados [x][1]=XI;
				Resultados [x][2]=f.eval(XI);
				Resultados [x][3]=dif.eval(XI);
				Resultados [x][4]=xImas1;
				Resultados [x][5]=E;
				Resultados [x][6]=f.eval(xImas1);
	    	  E=Math.abs(xImas1-XI);
	    	  XI=xImas1;
	    	  
	    	  x++;
	    	  if(x>=Lim) 
	    	  {   
	    		  break;
	    		  
	    	  }
	    	  
	    	  
	      }while(Math.abs(E)>error);
	      
	      if(Math.abs(E)>error) {
	      Bandera=true;
	      }
	      
	 
		Raiz= xImas1;
		
		
		
	}
	public String getFuncion() {
		return Funcion;
	}
	public void setFuncion(String funcion) {
		Funcion = funcion;
	}
	public double getXI() {
		return XI;
	}
	public void setXI(double xI) {
		XI = xI;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
	public int getLim() {
		return Lim;
	}
	public void setLim(int lim) {
		Lim = lim;
	}
	public double getRaiz() {
		return Raiz;
	}
	public void setRaiz(double raiz) {
		Raiz = raiz;
	}
	public Double[][] getResultados() {
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
