import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] T;
	static int[] P;
	static int N;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T = new int[N + 1];
		P = new int[N + 1];
		for (int i = 1; i < T.length; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
//		if (N==1) {
//			if (T[1]==1) {
//				System.out.println(P[1]);
//			}
//			else {
//				System.out.println(0);
//			}
//		}
//		else {
			int[][] dp = new int[2][N + 1];
			dp[0][1] = 1 + T[1];
			dp[1][1] = P[1];

			for (int j = 2; j < dp[0].length; j++) {
				int max = 0;
				for (int j2 = 1; j2 < j; j2++) {
					if (dp[0][j2] <= j && dp[1][j2] >= max) {
						max = dp[1][j2];
					}
				}
				dp[0][j] = j + T[j];
				dp[1][j] = max + P[j];
			}
			for (int i = 1; i < dp[0].length; i++) {
				if (dp[0][i]<=N+1) {
					max = Math.max(max, dp[1][i]);
				}
			}
			System.out.println(max==Integer.MIN_VALUE?0:max);
//		}
		
	}
}