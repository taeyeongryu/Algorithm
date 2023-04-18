import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int idx;
		int weight;

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static int N, M;
	static int[] dist;
	static List<Node>[] adjlist;
	static boolean[][] check;
	static List<Integer>[] trace;

	static void dijkstra(int start, int end) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int cur_idx = cur.idx;
			int cur_weight = cur.weight;

			// 이거 부등호 중요하다 등호 있으면 안된다.
			if (dist[cur_idx] < cur_weight) {
				continue;
			}

			for (Node next : adjlist[cur_idx]) {
				int next_idx = next.idx;
				int next_weight = next.weight;
				// 갈수 있는 길이면
				// default값이 false이다
				if (!check[cur_idx][next_idx]) {
					if (dist[next_idx] == dist[cur_idx] + next_weight) {
						trace[next.idx].add(cur_idx);
					} else if (dist[next_idx] > dist[cur_idx] + next_weight) {
						dist[next_idx] = dist[cur_idx] + next_weight;
						trace[next.idx].clear();
						trace[next.idx].add(cur.idx);
						pq.offer(new Node(next_idx, dist[cur_idx] + next_weight));
					}
				}
			}
		}
	}

	// node란 임시끝점을 의미한다.
	// s는 시작점
	static void back(int s, int node) {
		if (node == s) {
			return;
		}
		for (int n : trace[node]) {
			if (!check[n][node]) {
			check[n][node] = true;
			back(s, n);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0) {
				break;
			}
			dist = new int[N];
			trace = new ArrayList[N];
			check = new boolean[N][N];
			adjlist = new ArrayList[N];
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				adjlist[i] = new ArrayList<>();
				trace[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				adjlist[s].add(new Node(e, w));
			}
			dijkstra(start, end);
			back(start, end);
			dijkstra(start, end);
			if (dist[end] == Integer.MAX_VALUE) {
				bw.write(-1 + "\n");
			} else {
				bw.write(dist[end] + "\n");
			}
		}
		bw.close();
		br.close();
	}
}