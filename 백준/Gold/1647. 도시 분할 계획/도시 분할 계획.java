import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
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
	static int price[];
	static boolean visited[];

	static ArrayList<Node>[] adjlist;

	static int prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		int result = 0;
		int pick = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int cur_idx = cur_node.idx;
			int cur_weight = cur_node.weight;
			if (visited[cur_idx]) {
				continue;
			}
			result += cur_weight;
			visited[cur_idx] = true;
			price[cur_idx] = cur_weight;
			for (int i = 0; i < adjlist[cur_idx].size(); i++) {
				Node next_node = adjlist[cur_idx].get(i);
				int n_idx = next_node.idx;
				int n_weight = next_node.weight;
				if (visited[n_idx]) {
					continue;
				}
				pq.add(new Node(n_idx, n_weight));
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		price = new int[N + 1];
		visited = new boolean[N + 1];
		adjlist = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjlist[start].add(new Node(end, weight));
			adjlist[end].add(new Node(start, weight));
		}
		int result = prim(1);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < price.length; i++) {
			max = Math.max(max, price[i]);

		}
//		System.out.println(Arrays.toString(price));
		System.out.println(result - max);
	}

}