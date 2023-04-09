import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[][] D = new long[N + 1][11];
		D[1][1] = 1;
		D[1][2] = 1;
		D[1][3] = 1;
		D[1][4] = 1;
		D[1][5] = 1;
		D[1][6] = 1;
		D[1][7] = 1;
		D[1][8] = 1;
		D[1][9] = 1;

		for (int i = 2; i <= N; i++) {
			D[i][0] = D[i - 1][1];
			D[i][9] = D[i - 1][8];

			// 행
			for (int j = 1; j <= 8; j++) {
				D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % 1000000000;

			}
		}
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = (sum + D[N][i]) % 1000000000;
		}
		System.out.println(sum);
	}

}