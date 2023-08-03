import java.util.Scanner;

public class Main {
	static long[][] matrix;

	static long[][] pow(long[][] m, long ex) {
		if (ex == 1L) {
			return m;
		}

		long[][] tmp = pow(m, ex / 2);
		tmp = multi(tmp, tmp);
		if (ex % 2 == 1) {
			tmp = multi(tmp, matrix);
		}
		return tmp;
	}

	private static long[][] multi(long[][] tmp, long[][] tmp2) {
		long[][] result = new long[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j] += tmp[i][k] * tmp2[k][j];
					result[i][j] %= 1000000;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		matrix = new long[][] { { 1, 1 }, { 1, 0 } };
		long[][]result=pow(matrix, N);
		System.out.println(result[1][0]);
	}
}