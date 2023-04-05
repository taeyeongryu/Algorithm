import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		int weight;

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + "]";
		}

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static int N;
	static int M;
	static int X;
	static int INF = Integer.MAX_VALUE;
	// 최종 왕복 거리를 저장할 배열
	static int[] price;
	// 다익스트라를 돌릴때마다 저장할 배열
	static int[] distance;
	static ArrayList<Node>[] adjlist;

	static void dji(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node c_n = pq.poll();
			int c_i = c_n.idx;
			int c_w = c_n.weight;
			
			for (int i = 0; i < adjlist[c_i].size(); i++) {
				Node n_n = adjlist[c_i].get(i);
				int n_i = n_n.idx;
				int n_w = n_n.weight;
				// 여기 고쳐야 할 수도 있겠다
				if (distance[n_i] > c_w + n_w) {
					distance[n_i] = c_w + n_w;
					pq.add(new Node(n_i, distance[n_i]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		price = new int[N + 1];
		distance = new int[N + 1];
		adjlist = new ArrayList[N + 1];
		for (int i = 0; i < adjlist.length; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjlist[start].add(new Node(end, weight));
		}
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				Arrays.fill(distance, INF);
				dji(i);
				for (int j = 1; j <= N; j++) {
					price[j] += distance[j];
				}
			} else {
				Arrays.fill(distance, INF);
				dji(i);
				price[i] += distance[X];
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, price[i]);
		}
		System.out.println(max);
	}

}