package Projectofinal;

public class Falsa_posicion {
	
	String f;
    double x1; // elemento pivote
    double xI;
    double error;
    Double[][] Resultados;
    double Raiz;
    int Lim;
    Boolean Bandera;
    public Falsa_posicion(String f,double x1,double xi,double error,int Lim)
    {
    	this.f=f;
    	this.x1=x1;
    	this.xI=xi;
    	this.error=error;
    	this.Lim = Lim;
		Resultados = new Double [Lim][8];
		Bandera=false;
    	
    	
    }
    
    
    public void Iniciar() throws Exception 
    {  
    	int x=1;
    	double E=Math.abs(xI-x1);
    	
    	
        double XImas1 = 0;
        Funcion F= new Funcion(f);
        double FxI = F.eval(xI);
		double FxIminusFx1 = F.eval(xI)-F.eval(x1);
		Resultados [0][0]=(double) x;
        Resultados [0][1]=x1;
		Resultados [0][2]=F.eval(x1);
		Resultados [0][3]=(double) 0;
		Resultados [0][4]=(double) 0;
		Resultados [0][5]=(double) 0;
		Resultados [0][6]=(double) 0;
		Resultados [0][7]=(double) 0;
    
		double errorVerdadero = E;
		do {
			
			
    		 
    		 FxI = F.eval(xI);
    		 FxIminusFx1 = F.eval(xI)-F.eval(x1);
    	
    		
    		
    		
    		XImas1 = xI-FxI*(xI-x1)/FxIminusFx1;
    		        Resultados [x][0]=(double) x;	
    		        Resultados [x][1]=xI;	
    				Resultados [x][2]=F.eval(xI);
    				Resultados [x][3]=xI-x1;
    				Resultados [x][4]=F.eval(xI)-F.eval(x1);
    				Resultados [x][5]=XImas1;
    				Resultados [x][6]=E-Math.abs(XImas1-x1);
    				Resultados [x][7]=F.eval(XImas1);
    		 
    		   errorVerdadero=E-Math.abs(XImas1-x1);
    		  E=Math.abs(XImas1-x1);
    		
    		  
    	
    		xI= XImas1;
    	
    		
    		x++;
    		if(x>=Lim) 
    		{
    			break;
    		}
    		
    	}while( Math.abs(errorVerdadero)>error);
    	
		if(Math.abs(errorVerdadero)>error) 
		{
			Bandera=true;
		}
    	  Raiz = XImas1;
    	
    }
    public void Check() throws Exception 
    {
    	  Funcion F= new Funcion(f);
    	
    	System.out.println(""+(xI-F.eval(xI)*xI-x1/F.eval(xI)-F.eval(x1)));
    	
    }


	public String getF() {
		return f;
	}


	public void setF(String f) {
		this.f = f;
	}


	public double getX1() {
		return x1;
	}


	public void setX1(double x1) {
		this.x1 = x1;
	}


	public double getxI() {
		return xI;
	}


	public void setxI(double xI) {
		this.xI = xI;
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


	public Boolean getBandera() {
		return Bandera;
	}


	public void setBandera(Boolean bandera) {
		Bandera = bandera;
	}


	
    
}
