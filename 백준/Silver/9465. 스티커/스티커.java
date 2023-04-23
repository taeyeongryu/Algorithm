import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[2][N + 1];
			int[][] arr = new int[2][N + 1];
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[0][j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[1][j] = Integer.parseInt(st.nextToken());
			}
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			for (int i = 2; i <= N; i++) {
				dp[0][i] = Math.max(dp[0][i - 2], dp[1][i - 2]) + arr[0][i];
				dp[1][i] = Math.max(dp[0][i - 2], dp[1][i - 2]) + arr[1][i];
				dp[0][i] = Math.max(dp[0][i], dp[1][i - 1] + arr[0][i]);
				dp[1][i] = Math.max(dp[1][i], dp[0][i - 1] + arr[1][i]);
			}
			bw.write("" + Math.max(dp[0][N], dp[1][N]) + "\n");
		}
		bw.close();
		br.close();
	}

}