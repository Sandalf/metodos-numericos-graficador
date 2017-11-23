package Projectofinal;

public class Newton_SegundoOrden {

	private String Funcion;
	private double xI;
	private double error;
	private int Lim;
	private double Raiz;
	private Double Resultados [][];
	private boolean bandera;

	
	public Newton_SegundoOrden(String F,double XI,double E,int Ite) 
	{
		
		Funcion=F;
		this.xI=XI;
		error=E;
		Lim=Ite;
		Resultados=new Double [Lim][8];
		bandera=false;
		
		
		
		
	}
	public void inicia() throws Exception
	{        int x=0;
		   Funcion f= new Funcion(Funcion);
		      String Derivada=f.derivada();
		      String DobleDerivada=f.dobleDerivada();
		      Funcion dif= new Funcion(Derivada);
		      Funcion Doubledif= new Funcion(DobleDerivada);
		      double xImas1;
		      double E=Math.abs((xI-f.eval(xI)/dif.eval(xI)-xI));
		      do {
		    	  xImas1 = xI-(f.eval(xI)/(dif.eval(xI)-((f.eval(xI)*Doubledif.eval(xI))/2*dif.eval(xI))));
		    	  
		    	   
						Resultados [x][0]=((double)x);
						Resultados [x][1]=xI;
						Resultados [x][2]=f.eval(xI);
						Resultados [x][3]=dif.eval(xI);
						Resultados [x][4]=Doubledif.eval(xI);
						Resultados [x][5]=xImas1;
						Resultados [x][6]=f.eval(xImas1); 
						Resultados [x][7]=E;
					
				
		    	  
		    	  E=Math.abs(xImas1-xI);
		    	  xI=xImas1;
		    	  
		    	  System.out.println();
		    	  x++;
		    	  
		    	  if(x>=Lim) 
		    		  break;
		    	  
		    
		    	  
		      }while(Math.abs(E)>error);
		      
		      
		     
		      if(Math.abs(E)>error)
		    	  bandera = true;
		      
		 
			Raiz= xImas1;
		
	}
	public String getFuncion() {
		return Funcion;
	}
	public void setFuncion(String funcion) {
		Funcion = funcion;
	}
	public double getXI() {
		return xI;
	}
	public void setXI(double xI) {
		xI = xI;
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
	
	public boolean getBandera() 
	{
		return bandera;
		
	}
	
	

}
