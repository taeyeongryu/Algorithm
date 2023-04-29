import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.fill(dp, 1);
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}
		int max = 1;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(dp[i], max);
		}
		System.out.println(N-max);
	}

}