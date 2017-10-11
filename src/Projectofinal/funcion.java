package Projectofinal;

import org.nfunk.jep.JEP;

public class funcion {
	
	  private static final double xvalue = 0;
	  String Exp;
	  JEP parser = new JEP();
	  
	  
	  public funcion(String exp) 
	  {
		  Exp=exp;
		  parser.initFunTab();
		  parser.initSymTab();
		  parser.addStandardConstants();
		  parser.addStandardFunctions();
		  parser.addComplex();
		  parser.addVariable("x", xvalue);
		  parser.parseExpression(exp);  
		  
	  }
	
	 public double eval(double x) throws Exception
	 {
		 double r=Double.NaN;
		 parser.addVariable("x", x);
		 r=parser.getValue();
		 return r;

		 
	 }
	 public double [] eval(double[] x) throws Exception
	 {
		 int n=x.length;
		 
		 double[] r= new double [n];
		 for(int i=0; i<n; i++) 
		 {
			 r[i]=eval(x[i]);
			 
		 }
		 
		 return r;
	 }
	 
	 public double[] rango(double x0,double xn,double d) 
	 {
		 int n = (int)(Math.abs(xn-x0)/d);
		 
		 double[] r=new double [n];
		 
		 for(int i=0; i<n; i++) 
		 {
			 r[i]=x0;
			 x0+=d;
			 
			 
		 }
		 return r;
		 
	 }

}
