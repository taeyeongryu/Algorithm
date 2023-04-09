import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] R = new int[N + 1];
		int[] G = new int[N + 1];
		int[] B = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			R[i] = sc.nextInt();
			G[i] = sc.nextInt();
			B[i] = sc.nextInt();
		}
		int[][] dp = new int[3][N + 1];
		dp[0][1] = R[1];
		dp[1][1] = G[1];
		dp[2][1] = B[1];
		for (int i = 2; i <= N; i++) {
			dp[0][i]=Math.min(dp[1][i-1], dp[2][i-1])+R[i];
			dp[1][i]=Math.min(dp[0][i-1], dp[2][i-1])+G[i];
			dp[2][i]=Math.min(dp[1][i-1], dp[0][i-1])+B[i];
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, dp[i][N]);
		}
		System.out.println(min);
	}

}