package integracao;

public class CalculoIntegral {
	public CalculoIntegral() {

	}

	public double integTrapezio(double b, double a, Funcao y) {
		double h = b - a;
		return h * (y.f(a) + y.f(b)) / 2;
	}

	public double integTrapezioRep(double[] x, int N, Funcao y) {
		double h = (x[N - 1] - x[0]) / (N - 1);
		double sum = y.f(x[0]);

		for (int i = 1; i < N - 1; i++) {
			sum += 2 * y.f(x[i]);
		}

		sum += y.f(x[N - 1]);

		return h * sum / 2;
	}

	public double integ13(double b, double a, Funcao y) {
		double h = (b - a) / 2;
		return h * (y.f(a) + 4 * y.f(a + h) + y.f(b)) / 3;
	}

	public double integ13Rep(double[] x, int N, Funcao y) {
		double h = (x[N - 1] - x[0]) / (N - 1);
		double sum = 0;

		for (int i = 0; i < N; i++) {
			if (i == 0 || i == N - 1) {
				sum += 1 * y.f(x[i]);
			} else if (i % 2 == 1) {
				sum += 4 * y.f(x[i]);
			} else {
				sum += 2 * y.f(x[i]);
			}
		}

		return h * sum / 3;
	}

	public double integ38(double b, double a, Funcao y) {
		double h = (b - a) / 3;
		return 3 * h * (y.f(a) + 3 * y.f(a + h) + 3 * y.f(b - h) + y.f(b)) / 8;
	}
}
