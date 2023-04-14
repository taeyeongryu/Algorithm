import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		int weight;

		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.weight, node.weight);
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

	static int N, M, result;
	static List<Node>[] adjlist;
	static boolean[] choice;

	static void prim() {
		int count = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int cur_idx = cur.idx;
			int cur_weight = cur.weight;
			if (choice[cur_idx]) {
				continue;
			}
			choice[cur_idx] = true;
			result += cur_weight;
			count++;
			if (count == N) {
				return;
			}
			for (int i = 0; i < adjlist[cur_idx].size(); i++) {
				Node next = adjlist[cur_idx].get(i);
				if (!choice[next.idx]) {
					pq.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		choice = new boolean[N + 1];
		StringTokenizer st;
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
			adjlist[end].add(new Node(start, weight));
		}
		prim();
		System.out.println(result);
	}

}