import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		int[][] DP = new int[2][41];
		DP[0][0] = 1;
		DP[1][0] = 0;
		DP[0][1] = 0;
		DP[1][1] = 1;
		for (int i = 2; i <= 40; i++) {
			DP[0][i] = DP[0][i - 1] + DP[0][i - 2];
			DP[1][i] = DP[1][i - 1] + DP[1][i - 2];
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			bw.write(DP[0][tmp]+" "+DP[1][tmp]+"\n");
		}
		bw.close();
		br.close();
	}
}