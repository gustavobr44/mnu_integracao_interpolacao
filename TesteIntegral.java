package teste;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import integracao.*;

public class TesteIntegral {
	private static NumberFormat formatador;

	public static void main(String[] args) {
		CalculoIntegral integ = new CalculoIntegral();
		formatador = new DecimalFormat("0.0000"); // Sem "E00" no final da formatação

		// Slide1
		//double b = 7;
		//double a = 1;
		//Funcao y = (x) -> {
		//	return 1 / x;
		//};

		// Slide2
		// double[] xi = { 1, 1.5, 2, 2.5, 3 };
		// Funcao y = (x) -> {
		// return x * x * x * Math.log(x);
		// };
		
		int m = 4;
		double b = Math.PI/2;
		double a = 0;
		double h = (b - a)/m;
		double[] xi = {a, a+h, a+2*h, a+3*h, b};
		Funcao y = (x) -> {
			return Math.cos(x);
		};

		long start = System.nanoTime();
		double result = integ.integ38(b, a, y);
		long end = System.nanoTime();

		System.out.println("F(x) =");
		System.out.println(formatador.format(result));

		System.out.println("\nT =");
		System.out.println(end - start);
	}
}
