import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> list = new ArrayList<>();
	static int N, M;

	static int lower(int target) {
		int start = 0;
		int end = list.size();
		while (start < end) {
			int mid = (start + end) / 2;
			if (list.get(mid) >= target) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	static int upper(int target) {
		int start = 0;
		int end = list.size();
		while (start < end) {
			int mid = (start + end) / 2;
			if (list.get(mid) <= target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return start;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			int start = lower(tmp);
			int end = upper(tmp);
			sb.append(end-start+" ");
		}
		System.out.println(sb.toString());

	}

}
