import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] adjlist = new ArrayList[N + 1];
		int[] indegree = new int[N + 1];
		ArrayList<Integer> resultlist = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adjlist[start].add(end);
			indegree[end]++;
		}
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				pq.add(i);
			}
		}
		while (!pq.isEmpty()) {
			int tmp = pq.poll();
			resultlist.add(tmp);
			for (int i = 0; i < adjlist[tmp].size(); i++) {
				int tmp2 = adjlist[tmp].get(i);
				indegree[tmp2]--;
				if (indegree[tmp2] == 0) {
					pq.add(tmp2);
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < resultlist.size(); i++) {
			bw.write(resultlist.get(i)+" ");
		}
		bw.close();
		br.close();
	}
}