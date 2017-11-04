package Projectofinal;

public class Biseccion 
{
        String f;
        double a;
        double b;
        double error;
        Double[][] Resultados;
        double Raiz;
        int Lim;
		public Biseccion(String f, double a, double b, double error ,int Lim) {
			
			this.f = f;
			this.a = a;
			this.b = b;
			this.error = error;
			this.Lim = Lim;
			Resultados = new Double [Lim][7];
        
		}
	public void Inciar() throws Exception 
	{   
int x = 0;
		
		double xm;
		Funcion F= new Funcion(f);
		if(F.eval(a)*F.eval(b)<0) {
		do {
			xm=(a+b)/2;
			    Resultados [x][0]=((double)x);
				Resultados [x][1]=a;
				Resultados [x][2]=b;
				Resultados [x][3]=b-a;
				Resultados [x][4]=xm;
				Resultados [x][5]=F.eval(a);
				Resultados [x][6]=F.eval(xm);
			
			
			if(F.eval(xm) ==0) 
			{
				Raiz= xm;
				
				
			}else 
				if(F.eval(a)*F.eval(xm)<0)
				{
					b=xm;
					
					
				}
				else
					a=xm;
			
			
			
			
			x++;
		}while(Math.abs(b-a)>error);
	

		
		Raiz= xm;
	}else {
			System.out.println("no hay raiz en ese intervalo");}
	
	}
	
	

	
	public void Check() throws Exception 
	
	{
		Funcion F = new Funcion(f);
		System.out.println(F.eval(a));
		System.out.println(Math.abs(b-a));
		System.out.println(error);
	     System.out.println(F.eval(a)*F.eval(b));
	     System.out.println(Raiz);
	     
		
	}
		




	public String getF() {
		return f;
	}


	public void setF(String f) {
		this.f = f;
	}


	public double getA() {
		return a;
	}


	public void setA(double a) {
		this.a = a;
	}


	public double getB() {
		return b;
	}


	public void setB(double b) {
		this.b = b;
	}


	public double getError() {
		return error;
	}


	public void setError(int error) {
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


}
