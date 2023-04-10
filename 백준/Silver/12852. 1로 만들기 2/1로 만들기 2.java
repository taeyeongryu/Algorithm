import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = sc.nextInt();
		int[][] dp = new int[2][N + 1];
		dp[0][0] = 0;
		dp[1][0] = 0;
		dp[0][1] = 0;
		dp[1][1] = 0;
		for (int i = 2; i <= N; i++) {
			// 6의 약수라면
			if (i % 6 == 0) {
				if (dp[0][i / 3] > dp[0][i / 2]) {
					if (dp[0][i / 2] > dp[0][i - 1]) {
						dp[0][i] = dp[0][i - 1] + 1;
						dp[1][i] = i - 1;
					} else {
						dp[0][i] = dp[0][i / 2] + 1;
						dp[1][i] = i / 2;
					}
				} else {
					if (dp[0][i / 3] > dp[0][i - 1]) {
						dp[0][i] = dp[0][i - 1] + 1;
						dp[1][i] = i - 1;
					} else {
						dp[0][i] = dp[0][i / 3] + 1;
						dp[1][i] = i / 3;
					}

				}
			} else if (i % 3 == 0) {
				if (dp[0][i / 3] > dp[0][i - 1]) {
					dp[0][i] = dp[0][i - 1] + 1;
					dp[1][i] = i - 1;
				} else {
					dp[0][i] = dp[0][i / 3] + 1;
					dp[1][i] = i / 3;
				}
			} else if (i % 2 == 0) {
				if (dp[0][i / 2] > dp[0][i - 1]) {
					dp[0][i] = dp[0][i - 1] + 1;
					dp[1][i] = i - 1;
				} else {
					dp[0][i] = dp[0][i / 2] + 1;
					dp[1][i] = i / 2;
				}
			} else {
				dp[0][i] = dp[0][i - 1] + 1;
				dp[1][i] = i - 1;
			}
		}
		bw.write(""+dp[0][N]+"\n");
		bw.write(N+" ");
		int tmp = dp[1][N];
		for (int i = 0; i <dp[0][N]; i++) {
			bw.write(tmp+" ");
			tmp = dp[1][tmp];
		}
		bw.flush();
//		System.out.println(Arrays.deepToString(dp));
	}

}