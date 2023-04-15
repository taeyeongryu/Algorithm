import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] arr = new int[T + 1];
		int[][][] dp = new int[T + 1][W + 1][3];
		for (int i = 1; i <= T; i++) {
			int tmp = Integer.parseInt(br.readLine());
			arr[i] = tmp;
		}

		for (int i = 1; i <= T; i++) {
			dp[i][0][1] = dp[i - 1][0][1] + (arr[i] == 1 ? 1 : 0);
			for (int j = 1; j <= W; j++) {
				if (i < j)
					break;
				if (arr[i] == 1) {
					dp[i][j][1] = Math.max(dp[i - 1][j - 1][2], dp[i - 1][j][1]) + 1;
					dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]);
				} else {
					dp[i][j][1] = Math.max(dp[i - 1][j - 1][2], dp[i - 1][j][1]);
					dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]) + 1;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i <= W; i++) {
			ans = Math.max(ans, dp[T][i][1]);
			ans = Math.max(ans, dp[T][i][2]);
		}
		System.out.println(ans);
	}
}