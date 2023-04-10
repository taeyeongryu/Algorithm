import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int Test = sc.nextInt();
		for (int tc = 1; tc <=Test; tc++) {
			int N = sc.nextInt();
			int L = sc.nextInt();
			int T[] = new int[N+1];
			int K[] = new int[N+1];
			int dp[][] = new int[N+1][10001];
			for (int i = 1; i <= N; i++) {
				T[i]=sc.nextInt();
				K[i]=sc.nextInt();
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= 10000; j++) {
					if (K[i]>j) {
						dp[i][j]=dp[i-1][j];
					}
					else {
						dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-K[i]]+T[i]);
					}
				}
			}
			System.out.printf("#%d %d%n",tc,dp[N][L]);
		}
	}
}