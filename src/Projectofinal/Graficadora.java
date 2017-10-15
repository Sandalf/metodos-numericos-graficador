package Projectofinal;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficadora
{
	
	 JFreeChart grafica;
	 XYSeriesCollection datos= new XYSeriesCollection();
	 String titulo;
	 String EtiquetaX;
	 String EtiquetaY;
	 
	 public Graficadora(String t,String EX,String EY) 
	  { 
		 titulo=t;
		 EtiquetaX=EX;
		 EtiquetaY=EY;
		 grafica=ChartFactory.createXYLineChart(titulo, EtiquetaX, EtiquetaY, datos, PlotOrientation.VERTICAL, true, true, true);
	 }
	
	public Graficadora() 
	{	
		this("sin titulo","x","y");		
	}
	
	public void agregarGrafica(String id,double[] x, double [] y) 
	{
		XYSeries s= new XYSeries(id);
		int n=x.length;
		for(int i =0; i<n; i++) 
		{		
			s.add(x[i],y[i]);		
		}
		
		datos.addSeries(s);	
	}
	
	public void crearGrafica(String id,double [] x,double [] y) 
	{
		datos.removeAllSeries();
		agregarGrafica(id,x,y);
		
	}
	
	public JPanel obtieneGrafica() 
	{
		return new ChartPanel(grafica);		
	}
	
}
