import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] result;
	static StringBuilder sb = new StringBuilder();

	static void Back(int depth, int at) {
		if (depth == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append("" + result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = at; i <= N; i++) {
			result[depth] = i;
			Back(depth + 1, i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M주어진다. N개중에서 순서를 고려해서 M개 뽑는 것이다.중복은 허용안됨
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M];
		Back(0, 1);
		System.out.println(sb.toString());
	}

}
