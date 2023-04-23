import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean[] isVip = new boolean[41];
		int[] dp = new int[41];
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			isVip[sc.nextInt()] = true;
		}
		if (!isVip[1] && !isVip[2]) {
			dp[1] = 1;
			dp[2] = 2;
		} else {
			dp[1] = 1;
			dp[2] = 1;
		}
		for (int i = 3; i <= N; i++) {
			if (isVip[i]) {
  				dp[i]=dp[i-1];
			}
			else if (isVip[i-1]) {
				dp[i]=dp[i-1];
			}
			else {
				dp[i]=dp[i-1]+dp[i-2];
			}
		}
		System.out.println(dp[N]);
	}
}