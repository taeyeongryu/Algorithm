import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N];
		dp[0] = arr[0];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}