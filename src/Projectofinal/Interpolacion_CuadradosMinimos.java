package Projectofinal;







public class Interpolacion_CuadradosMinimos {
	private Double [][] xn;
	private Double [][] Producto;
	private Double[] A;
	private Double[] B;
	private Double[][] C;
	private Double[] res;
	private Double[][] Table;
	private int Orden;
	private Double[][] resultados;
	private int Tablelenght;
	
	
	public Interpolacion_CuadradosMinimos(Double[][] Table, int Orden) throws Exception 
	{
		
		Tablelenght=Table.length;
		Producto=	xn=new Double[Tablelenght][Orden];
		xn=new Double[Tablelenght][(Orden*2)];
		A= new Double[(Orden*2)+1];
		B=new Double[Orden+1];
		C=new Double [Orden+1][Orden+2];
		res = new Double[Orden+1];
		this.Table=Table;
		this.Orden=Orden;
		B[0]=(double) 0;
		A[0]=(double) Table.length;
	   //Inicializar sumatorias
	    for(int i=1;i<A.length; i++) 
	    {
	    
	    	A[i]=(double) 0;
	    } for(int i=1;i<B.length; i++) 
	    {
	    
	    	B[i]=(double) 0;
	    }
		//CALCULA SUMATORIAS DE LAS Y
		for(int i=0; i<=Table.length-1; i++) 
		{	
		   B[0]=B[0]+Table[i][1];
		   System.out.print("  "+B[0]);
		   
		 //Calcular Sumatorias de las X ala M potencia
		
			for(int j=1; j<A.length;j++) 
			{
				
				A[j]=A[j]+Math.pow(Table[i][0], (double)j);
				
				try {
					
					xn [i][j-1]=Math.pow(Table[i][0], (double)j);
					
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
					
				}
			
				
				
			}
		
			//Calcular Sumatoria de Los Producto
			
			for(int j=1; j<=Orden; j++) 
			{
				B[j]=B[j]+(Table[i][1]*Math.pow(Table[i][0], j));
				try {
					
				Producto[i][j-1]=(Table[i][1]*Math.pow(Table[i][0], j));
					
					
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
					
				}
			
			}
		
			
			
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		for(int i=0; i<xn.length; i++) 
		{
			System.out.println("");
			for(int j=0; j<xn[i].length; j++) 
			{
				System.out.print(xn[i][j]);
				
				
			}
			
			
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
		for(int i=0; i<Producto.length; i++) 
		{
			System.out.println("");
			for(int j=0; j<Producto[i].length; j++) 
			{
				System.out.print(""+Producto[i][j]);
				
				
			}
			
			
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Calcular matriz De ecuaciones lineales
		for(int i=1; i<=Orden+1; i++) 
		{
			for(int j=1; j<=Orden+1; j++) 
			{
				
				
					C[i-1][j-1]=A[i+j-2];
			
				
			}
			C[i-1][Orden+1]=B[i-1];
			
		}
		

		//llamar gauss jordan para resolver el sistema de Ecuaciones lineales
		GaussJordan Gauss= new GaussJordan(C);
		Double [][] MatrixFinal= Gauss.solve(C);
		res=Gauss.solution(MatrixFinal);
	}
	
	public double Solve(double X) {
		double solucion = 0;
		for(int i=Orden; i>=0; i--) 
		{
			
			solucion=solucion+(res[i]*Math.pow(X, i));
			
			
		}
		return solucion;
		
	}
	
	
	
	
	public Double [][] Resultados()
	{
		
		resultados=new Double[Tablelenght+1][2+Orden+Orden+1];
		//puntos
		for(int i=0; i<2; i++) 
		{
			for(int j=0; j<Tablelenght;j++) 
			{
				
				
				resultados[j][i]=Table[j][i];
				
			}
			
			
		}	
		// X ala N
			
			for(int i=2; i<(Orden*2)+1; i++) 
			{
				for(int j=0; j<Tablelenght;j++) 
				{
					
					
					resultados[j][i]=xn[j][i-1];
					
				}
				
				
			}
			//Productos
			for(int i=2+Orden+1;i<resultados[0].length;i++) 
			{
				for(int j=0; j<resultados.length-1;j++) 
				{
					
					resultados[j][i]=Producto[j][i-5];
					
				}
				
			}
			for(int i=2; i<Orden; i++) 
			{
				for(int j=0; j<Tablelenght;j++) 
				{
					
					
					resultados[j][i]=xn[j][i-2];
					
				}
				
				
			}
			resultados[Tablelenght][0]=A[1];
			resultados[Tablelenght][1]=B[0];
			
			// Sumatorias
			for(int i =3; i<Orden+4; i++) 
			{
				
				resultados[Tablelenght][i-1]=A[i-1];
			}
			  //Sumatoria de Productos
			int k=B.length-1;
			for(int i =resultados[0].length-1; i>resultados[0].length-3; i--) 
			{
				
				resultados[Tablelenght][i]=B[k];
				k--;
			}
			
			
			
			
			for(int i=0; i<resultados.length; i++) 
			{
				System.out.println("");
				for(int j=0; j<resultados[0].length; j++) 
				{
					
					System.out.print(""+resultados[i][j]);
					
				}
				
			
				
			}
			return resultados;
			
		
	}			
	
		
	
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
	}
	
	
	

		
		
		
		
		
		
	
	
	
	
	

