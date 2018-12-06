package interpolacao;

import sistemas.CalculoSistema;
import raiz.CalculoRaiz;

public class CalculoFuncao {
	private CalculoSistema sis;
	private CalculoRaiz raiz;

	public CalculoFuncao() {
		sis = new CalculoSistema();
		raiz = new CalculoRaiz();
	}

	public double interSistemaLinear(double x, double[] fx, double[] xi, int N, int grau) {
		int s = getNewXi(x, xi, N, grau);
		int n = grau + 1;
		double[][] X = new double[n][n];
		double[] _fx = new double[n];
		double result = 0;

		for (int i = 0, k = s; i < n; i++, k++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = Math.pow(xi[k], j);
			}

			_fx[i] = fx[k];
		}

		double[] a = sis.gauss(X, _fx, n);

		for (int i = 0; i < n; i++) {
			result += a[i] * Math.pow(x, i);
		}

		return result;
	}

	public double interLagrange(double x, double[] fx, double[] xi, int N, int grau) {
		int s = getNewXi(x, xi, N, grau);
		int n = grau + 1;
		double Lk = 1, result = 0;

		for (int k = s; k < s + n; k++) {
			for (int j = s; j < s + n; j++) {
				if (j != k) {
					Lk *= ((x - xi[j]) / (xi[k] - xi[j]));
				}
			}

			result += fx[k] * Lk;
			Lk = 1;
		}

		return result;
	}

	public double interNewton(double x, double[] fx, double[] xi, int N, int grau) {
		int s = getNewXi(x, xi, N, grau);
		int n = grau + 1;
		double result = 0, prod = 1;

		for (int i = s; i < s + n; i++) {
			for (int j = s; j < i; j++) {
				prod *= (x - xi[j]);
			}

			result += (prod * getDifDiv(xi, fx, s, i));
			prod = 1;
		}

		return result;
	}

	public double interInversa(double f, double[] fx, double[] xi, int N, int grau) {
		int s = getNewXi(f, fx, N, grau);
		int n = grau + 1;
		double[][] X = new double[n][n];
		double[] _fx = new double[n];

		for (int i = 0, k = s; i < n; i++, k++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = Math.pow(xi[k], j);
			}

			_fx[i] = fx[k];
		}

		double[] a = sis.gauss(X, _fx, n);

		a[0] -= f;

		return raiz.newtonPolinomios(xi[s + (n / 2)], a, n, Math.pow(10, -4), 50);
	}

	/*------------------------------------------------------------------------------------*/

	private int getNewXi(double x, double[] xi, int N, int grau) {
		int n = grau + 1;
		int div = n / 2;
		int rest = n % 2;

		for (int i = 0; i < N; i++) {
			if (i + div >= N) {
				return (N - n);
			} else if ((xi[i] - x) >= 0) {
				return (i - div - rest < 0) ? 0 : (i - div - rest);
			}
		}

		return 0;
	}

	private double getDifDiv(double[] xi, double[] fx, int start, int end) {
		if (start == end) {
			return fx[start];
		} else {
			return (getDifDiv(xi, fx, start + 1, end) - getDifDiv(xi, fx, start, end - 1)) / (xi[end] - xi[start]);
		}
	}
}
