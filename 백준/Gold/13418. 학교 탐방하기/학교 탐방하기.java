import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

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
	static ArrayList<Node>[] adjlist;
	static boolean[] visited;

	static int prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		int pick = 0;
		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int cur_idx = cur_node.idx;
			int cur_weight = cur_node.weight;
			if (visited[cur_idx]) {
				continue;
			}
			visited[cur_idx] = true;
			if (cur_weight == 0 && cur_idx != 0) {
				pick++;
			}
			for (int i = 0; i < adjlist[cur_idx].size(); i++) {
				int n_idx = adjlist[cur_idx].get(i).idx;
				int n_weight = adjlist[cur_idx].get(i).weight;
				if (visited[n_idx]) {
					continue;
				}
				pq.add(new Node(n_idx, n_weight));
			}
		}
		return pick;
	}

	static int prim2(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		int pick = 0;
		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int cur_idx = cur_node.idx;
			int cur_weight = cur_node.weight;
			if (visited[cur_idx]) {
				continue;
			}
			visited[cur_idx] = true;
			if (cur_weight == 0 && cur_idx != 0) {
				pick++;
			}
			for (int i = 0; i < adjlist[cur_idx].size(); i++) {
				int n_idx = adjlist[cur_idx].get(i).idx;
				int n_weight = adjlist[cur_idx].get(i).weight;
				if (visited[n_idx]) {
					continue;
				}
				pq.add(new Node(n_idx, -n_weight));
			}
		}
		return pick;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adjlist = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjlist[i] = new ArrayList<>();
		}
		adjlist[sc.nextInt()].add(new Node(sc.nextInt(), -sc.nextInt()));
		for (int i = 0; i < M; i++) {
			int st = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			if (weight == 1) {
				weight = -1;
			}
			adjlist[st].add(new Node(end, weight));
			adjlist[end].add(new Node(st, weight));
		}
		int result1 = prim(0);
		visited = new boolean[N + 1];
		int result2 = prim2(0);
		System.out.println((int) (Math.pow(result2, 2) - Math.pow(result1, 2)));
	}

}