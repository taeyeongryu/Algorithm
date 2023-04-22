import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		int[] dp = new int[10001];
		int[] arr = new int[10001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp[1]=arr[1];
		dp[2]=arr[1]+arr[2];
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
			dp[i] = Math.max(dp[i], dp[i - 3] + arr[i - 1] + arr[i]);
		}
		System.out.println(dp[N]);
	}

}
