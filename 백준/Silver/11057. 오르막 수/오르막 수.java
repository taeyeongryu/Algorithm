import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[10][1001];
		for (int i = 0; i <= 9; i++) {
			dp[i][1] = 1;
		}
		for (int j = 2; j <= N; j++) {
			for (int i = 0; i <= 9; i++) {
				for (int k = 0; k <= i; k++) {
					dp[i][j] += dp[k][j - 1];
					dp[i][j] %= 10007;
				}
			}
		}
		int result = 0;
		for (int i = 0; i <= 9; i++) {
			result += dp[i][N];
		}
		System.out.println(result%10007);
	}
}