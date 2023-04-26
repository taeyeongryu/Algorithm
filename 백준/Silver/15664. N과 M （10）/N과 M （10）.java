import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] result, arr;
	static StringBuilder sb = new StringBuilder();
	static Set<String> set = new HashSet<>();
	static List<String> list = new ArrayList<>();

	static void Back(int depth, int at) {
		if (depth == M) {
			sb = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i] + " ");
			}
			if (!set.contains(sb.toString())) {
				list.add(sb.toString());
				set.add(sb.toString());
			}
			return;
		}

		for (int i = at; i < N; i++) {
			result[depth] = arr[i];
			Back(depth + 1, i+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M주어진다. N개중에서 순서를 고려해서 M개 뽑는 것이다.중복은 허용안됨
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		result = new int[M];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		Back(0,0);
		sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + "\n");
		}
		System.out.println(sb.toString());
	}
}
