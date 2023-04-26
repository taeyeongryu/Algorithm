import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	static List<Integer> list;

	static void Back(int depth) {
		if (depth == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append("" + result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
//			result[depth] = 
			result[depth]=list.get(i);
			Back(depth+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M주어진다. N개중에서 순서를 고려해서 M개 뽑는 것이다.중복은 허용안됨
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		result = new int[M];
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		list = new ArrayList<>(set);
		Collections.sort(list);
		Back(0);
		System.out.println(sb.toString());
	}
}