import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] result, arr;
	static StringBuilder sb = new StringBuilder();

	static void Back(int depth, int at) {
		if (depth == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append("" + result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = at; i < N; i++) {
			result[depth] = arr[i];
			Back(depth + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M주어진다. N개중에서 순서를 고려해서 M개 뽑는 것이다.중복은 허용안됨
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		Back(0, 0);
		System.out.println(sb.toString());
	}

}