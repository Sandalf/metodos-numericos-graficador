package Projectofinal;

import java.util.ArrayList;

public class EulerMejorado {
	
	private double X0;
	private double Y0;
	private double X;
	private double Y;
	private int N;
	private String Exp;
	private Double[][] tabla;
	
	
	public EulerMejorado(String Exp,double X0,double Y0,int N,double X) 
	{
		this.Exp=Exp;
		this.X0=X0;
		this.Y0=Y0;
		this.N=N;
		this.X=X;
		
	
	}
	 public Double solve() throws Exception 
	 {
		 ArrayList<Double[]> datos = new ArrayList<Double[]>();
		 Funcion F = new Funcion(Exp);
		 double h=X/N;
		 Double Yn=Y0;
		 double Xn=X0;
		 double Ximas1;
		 double Yast;
		 
		 for(int i=0; i<=N-1; i++) 
		 {
			 Double[] row = new Double[3];
			 row[0]=(double) i;
			 row[1]=Xn;
			 row[2]=Yn;
			 datos.add(row);
			 Ximas1=Xn+h;
			 Yast=Yn+(h*F.evalXY(Xn, Yn));
			 
			 Yn=Yn+((h/2)*(F.evalXY(Xn, Yn)+F.evalXY(Ximas1, Yast)));
			 Xn=Ximas1;
			 
		 }
		 setTabla(datos);
		 return Yn;
		 
	 }
	 public void setTabla(ArrayList<Double[]> datos) {
			this.tabla = new Double[datos.size()][datos.get(0).length];
			int i = 0;

			for (Double[] row : datos) {
				tabla[i] = row.clone();
				i++;
			}
		}
	
	 public Double[][] getTabla() {
			return tabla;
		}
}




