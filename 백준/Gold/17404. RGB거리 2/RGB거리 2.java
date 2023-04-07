import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] R, G, B;
	static int[][] dp1, dp2, dp3;
	static int N;
	static final int INF = 1000 * 1000 + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		R = new int[N + 1];
		G = new int[N + 1];
		B = new int[N + 1];
		dp1 = new int[3][N + 1];
		dp2 = new int[3][N + 1];
		dp3 = new int[3][N + 1];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			R[i] = Integer.parseInt(st.nextToken());
			G[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		dp1[0][1] = R[1];
		dp1[1][1] = INF;
		dp1[2][1] = INF;

		dp2[1][1] = G[1];
		dp2[0][1] = INF;
		dp2[2][1] = INF;

		dp3[2][1] = B[1];
		dp3[0][1] = INF;
		dp3[1][1] = INF;
		for (int i = 2; i <= N; i++) {
			dp1[0][i] = Math.min(dp1[1][i - 1], dp1[2][i - 1]) + R[i];
			dp1[1][i] = Math.min(dp1[0][i - 1], dp1[2][i - 1]) + G[i];
			dp1[2][i] = Math.min(dp1[0][i - 1], dp1[1][i - 1]) + B[i];
			dp2[0][i] = Math.min(dp2[1][i - 1], dp2[2][i - 1]) + R[i];
			dp2[1][i] = Math.min(dp2[0][i - 1], dp2[2][i - 1]) + G[i];
			dp2[2][i] = Math.min(dp2[0][i - 1], dp2[1][i - 1]) + B[i];
			dp3[0][i] = Math.min(dp3[1][i - 1], dp3[2][i - 1]) + R[i];
			dp3[1][i] = Math.min(dp3[0][i - 1], dp3[2][i - 1]) + G[i];
			dp3[2][i] = Math.min(dp3[0][i - 1], dp3[1][i - 1]) + B[i];
		}
		int min = Integer.MIN_VALUE;
		min = Math.min(dp1[1][N], dp1[2][N]);
		min = Math.min(min, Math.min(dp2[0][N], dp2[2][N]));
		min = Math.min(min, Math.min(dp3[0][N], dp3[1][N]));
		bw.write(""+min);
		bw.close();
		br.close();

	}

}