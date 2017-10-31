package Projectofinal;

import java.util.Scanner;

public class testingFuncion {

	public static void main(String[] args) throws Exception {

		Scanner Leer = new Scanner(System.in);
		String Exp;
		double n = 5;

		Exp = Leer.next();
		Funcion f = new Funcion(Exp);

		System.out.println(+f.eval((double) 55));

	}

}
