import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] result;
	static boolean[] choice;
	static StringBuilder sb = new StringBuilder();
	static void Back(int depth, int N, int M) {
		if (depth == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append(""+result[i]+" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (choice[i] == false) {
				choice[i] = true;
				result[depth] = i;
				Back(depth + 1, N, M);
				choice[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		result = new int[M];
		choice = new boolean[N + 1];
		Back(0, N, M);
		System.out.println(sb.toString());
	}
}

