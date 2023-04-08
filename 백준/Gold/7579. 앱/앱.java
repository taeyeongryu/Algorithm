import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 1];
		int[] price = new int[N + 1];
		int total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
			total += price[i];
		}
		int[][] dp = new int[N + 1][total + 1];
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(price));
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < total + 1; j++) {
				if (j - price[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j - price[i]] + arr[i], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		int min = 100*100;
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (dp[i][j]>=M) {
					min = Math.min(min, j);
				}
			}
		}
//		System.out.println(Arrays.deepToString(dp));
		System.out.println(min);
	}
}