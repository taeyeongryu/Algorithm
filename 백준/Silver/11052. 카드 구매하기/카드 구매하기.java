import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		long[] dp = new long[1001];
		long[] P = new long[1001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			P[i] = Long.parseLong(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <=i; j++) {
				dp[i]=Math.max(dp[i], dp[i-j]+P[j]);
			}
		}
		System.out.println(dp[N]);
	}

}