package teste;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import interpolacao.CalculoFuncao;

public class TesteInter {
	private static NumberFormat formatador;

	public static void main(String[] args) {
		CalculoFuncao func = new CalculoFuncao();
		formatador = new DecimalFormat("0.0000"); // Sem "E00" no final da formatação

		// Slide1 - Interpolação
		//int N = 3;
		//double x = 1;
		//double[] xi = { -1, 0, 2 };
		//double[] fx = { 4, 1, -1 };

		// Slide2 - Interpolação Inversa
		int N = 6;
		double f = 2;
		double[] xi = { 0, 0.1, 0.2, 0.3, 0.4, 0.5 };
		double[] fx = { 1, 1.1052, 1.2214, 1.3499, 1.4918, 1.6487 };

		// Ex1
		//int N = 6;
		//double x = 0.6;
		//double[] xi = { 0, 0.2618, 0.5234, 0.7854, 1.0472, 1.3090 };
		//double[] fx = { 0, 1.0353, 2.0000, 2.8284, 3.4641, 3.8637 };

		long start = System.nanoTime();
		double result = func.interInversa(f, fx, xi, N, 2);
		long end = System.nanoTime();

		System.out.println("f(x) =");
		System.out.println(formatador.format(result));

		System.out.println("\nT =");
		System.out.println(end - start);
	}
}
