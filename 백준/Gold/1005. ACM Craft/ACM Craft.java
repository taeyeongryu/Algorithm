import java.io.*;
import java.util.*;

public class Main {
	static int N, K, W;
	// 건물번호에 맞게 1번부터 시작한다.
	static int[] buildtime, time, indegree;

	static List<Integer>[] outlist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			buildtime = new int[N + 1];
			time = new int[N + 1];
			indegree = new int[N + 1];
			outlist = new ArrayList[N + 1];
			for (int i = 0; i < outlist.length; i++) {
				outlist[i] = new ArrayList<>();
			}
			for (int i = 1; i <= N; i++) {
				buildtime[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				indegree[end]++;
				outlist[start].add(end);
			}
			W = Integer.parseInt(br.readLine());
			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0) {
					time[i] = buildtime[i];
					q.add(i);
				}
			}

			while (!q.isEmpty()) {
				int cur_b = q.poll();
				for (int j = 0; j < outlist[cur_b].size(); j++) {
					int next_b = outlist[cur_b].get(j);
					time[next_b] = Math.max(time[next_b], time[cur_b] + buildtime[next_b]);
					indegree[next_b]--;
					if (indegree[next_b] == 0) {
						q.add(next_b);
					}
				}
			}
			System.out.println(time[W]);
		}
	}
}