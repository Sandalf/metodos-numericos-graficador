package Projectofinal;

import java.util.Scanner;

import org.nfunk.jep.JEP;

public class testingJep {

	/** Current xValue */
	private static double xValue; // variable donde se guardara el valor de X
	private static String exprField;// variable string para guardar la expresion

	public static void main(String[] args) {
		JEP n = new JEP();

		Scanner Leer = new Scanner(System.in);

		System.out.println("ingrese la exresion que desea evaluar  ");
		exprField = Leer.next();
		System.out.println("ingrese el valor de X  ");
		xValue = Leer.nextDouble();

		parseExpression(n);

	}

	private static void parseExpression(JEP n) {

		n.initSymTab();
		n.addStandardConstants();// agrega la constantes mas utilizadas en el calculo
		n.addStandardFunctions();// agrega funciones trigonometricas logaritmicas etc.
		n.addComplex();
		n.addVariable("x", xValue);// agrega la variable X a la Expresion para ser reconocidad
		n.parseExpression(exprField);// evalua la expresion
		updateResult(n);// evalua el valor de X en la funcion y te arroja el resultado.

	}

	private static void updateResult(JEP n) {
		Object result;

		result = n.getValueAsObject();

		System.out.println("Resultado  " + result);

	}

}
