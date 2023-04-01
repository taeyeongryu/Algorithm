import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		int weight;

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + "]";
		}

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
	}

	static int N;
	static int M;
	static int K;
	static boolean visited[];
	static ArrayList<Node>[] adjlist;

	static int prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int pick = 0;
		int result = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
//			System.out.println(pq);
			Node c_node = pq.poll();
			int c_idx = c_node.idx;
			int c_weight = c_node.weight;

			if (visited[c_idx]) {
				continue;
			}
			visited[c_idx] = true;
			pick++;
			result += c_weight;
			if (pick == N+1) {
				break;
			}
			for (int i = 0; i < adjlist[c_idx].size(); i++) {
				Node n_node = adjlist[c_idx].get(i);
				int n_idx = n_node.idx;
				int n_weight = n_node.weight;
				if (visited[n_idx]) {
					continue;
				}
				pq.offer(n_node);
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 도시 개수
		N = Integer.parseInt(st.nextToken());
		// 케이블 수
		M = Integer.parseInt(st.nextToken());
		// 발전소 개수
		K = Integer.parseInt(st.nextToken());
		adjlist = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < adjlist.length; i++) {
			adjlist[i] = new ArrayList();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			adjlist[0].add(new Node(tmp, 0));
			adjlist[tmp].add(new Node(0, 0));
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjlist[start].add(new Node(end, weight));
			adjlist[end].add(new Node(start, weight));
		}
		int result = prim(0);
		System.out.println(result);
	}
}