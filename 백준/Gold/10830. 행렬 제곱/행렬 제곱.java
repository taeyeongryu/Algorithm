import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long B;
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
		long[][] result = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					result[i][j] += tmp[i][k] * tmp2[k][j];
					result[i][j] %= 1000;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		matrix = new long[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = (Integer.parseInt(st.nextToken())) % 1000;
			}
		}
		long[][] result = pow(matrix, B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.append(result[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.close();
		br.close();
	}
}