import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		long weight;
		int k;

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}

		public Node(int idx, long weight, int k) {
			super();
			this.idx = idx;
			this.weight = weight;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + ", k=" + k + "]";
		}
	}

	static int N, M, K;
	static final long MAX = Long.MAX_VALUE;
	static long[][] dist;
	static List<Node>[] adjlist;

	static void dij() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0, 0));

		while (!pq.isEmpty()) {
//			System.out.println("pq : "+ pq);
//			System.out.println(Arrays.deepToString(dist));
			Node cur = pq.poll();
			int idx = cur.idx;
			long weight = cur.weight;
			int k = cur.k;

			if (weight > dist[k][idx]) {
				continue;
			}

			for (int i = 0; i < adjlist[idx].size(); i++) {
				Node next = adjlist[idx].get(i);
				int nidx = next.idx;
				long nweight = next.weight;
				if (dist[k][nidx] > weight + nweight) {
					dist[k][nidx] = weight + nweight;
					pq.add(new Node(nidx, weight + nweight, k));
				}
				if (k < K && dist[k + 1][nidx] > weight) {
					dist[k + 1][nidx] = weight;
					pq.add(new Node(nidx, weight, k + 1));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new long[K + 1][N + 1];
		adjlist = new ArrayList[N + 1];
		for (int i = 0; i < adjlist.length; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i], MAX);
		}
		for (int i = 0; i < K + 1; i++) {
			dist[i][1] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjlist[s].add(new Node(e, w, 0));
			adjlist[e].add(new Node(s, w, 0));
		}
		dij();
		long min = Long.MAX_VALUE;
		for (int i = 0; i < K + 1; i++) {
			min = Long.min(dist[i][N], min);
		}
		System.out.println(min);
//		System.out.println(Arrays.deepToString(dist));
	}
}