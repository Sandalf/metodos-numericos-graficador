package Projectofinal;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;

public class Funcion {
	
	  private static final double xvalue = 0;
	  private static final double Yvalue = 0;
	  String Exp;
	  static String Derivada;
	  JEP parser = new JEP();
	  public Funcion(String exp) 
	  {
		  Exp=exp;
		  Derivada = exp;
		  parser.initFunTab();
		  parser.initSymTab();
		  parser.addStandardConstants();
		  parser.addStandardFunctions();
		  parser.addComplex();
		  parser.addVariable("x", xvalue);
		  parser.addVariable("y",Yvalue);
		  parser.setImplicitMul(true);
		  parser.parseExpression(exp);  
		  
	  }
	
	 public double eval(double x) throws Exception
	 {
		 double r=Double.NaN;
		 parser.addVariable("x", x);
		 r=parser.getValue();
		 return r;
		 

		 
	 }
	 public double evalXY(double x,double y) throws Exception
	 {
		 double r=0;
		
		
		 parser.addVariable("x",x);
		 parser.addVariable("y",y);
		 parser.setImplicitMul(true);
		 parser.setImplicitMul(true);
		 parser.parse(Exp);
		 r= parser.getValue();
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
	 public static String derivada() throws Exception {
 		 DJep j = new DJep();
 	    j.addStandardConstants();
 	    j.addStandardFunctions();
 	    j.addComplex();
 	    j.setAllowUndeclared(true);
 	    j.setAllowAssignment(true);
 	    j.setImplicitMul(true);

 	    // Sets up standard rules for differentiating sin(x) etc.
 	    j.addStandardDiffRules();

 	     // parse the string
 	        Node node = j.parse(Derivada);
 	        // differentiate wrt x
 	        Node diff = j.differentiate(node,"x");
 	        
 	        Node simp = j.simplify(diff);
 	         
 	      
			return j.toString(simp);
 	        
 	        
 	        
 	         
 	        
 	     
 	  
 	}
	 public static String dobleDerivada( ) throws Exception {
 		 DJep j = new DJep();
	 	    j.addStandardConstants();
	 	    j.addStandardFunctions();
	 	    j.addComplex();
	 	    j.setAllowUndeclared(true);
	 	    j.setAllowAssignment(true);
	 	    j.setImplicitMul(true);

	 	    // Sets up standard rules for differentiating sin(x) etc.
	 	    j.addStandardDiffRules();

	 	     // parse the string
	 	        Node node = j.parse(Derivada);
	 	        // differentiate wrt x
	 	        Node diff = j.differentiate(node,"x");
	 	        
	 	        Node simp = j.simplify(diff);
	 	       
	 	         String derivateField = j.toString(simp);
	 	        
	 	        
	 	      return  derivada1( derivateField);
	 	         
	 	        
	 	     
	 	    
	 	    }
	 
	 private static String derivada1(String Exp) throws Exception {
 		 DJep j = new DJep();
 	    j.addStandardConstants();
 	    j.addStandardFunctions();
 	    j.addComplex();
 	    j.setAllowUndeclared(true);
 	    j.setAllowAssignment(true);
 	    j.setImplicitMul(true);

 	    // Sets up standard rules for differentiating sin(x) etc.
 	    j.addStandardDiffRules();

 	     // parse the string
 	        Node node = j.parse(Exp);
 	        // differentiate wrt x
 	        Node diff = j.differentiate(node,"x");
 	        
 	        Node simp = j.simplify(diff);
 	         
 	      
			return j.toString(simp);
 	        
 	        
 	        
 	         
 	        
 	     
 	  
 	}

}
