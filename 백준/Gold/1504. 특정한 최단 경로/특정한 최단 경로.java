import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		int num;

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", num=" + num + "]";
		}

		public Node(int idx, int num) {
			super();
			this.idx = idx;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.num, o.num);
		}

	}

	static ArrayList<Node>[] adjlist;
	static int N;
	static int E;
	static final int INF = Integer.MAX_VALUE;
	static int[] price;

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		price[start] = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int cur_idx = curr.idx;
			int cur_num = curr.num;
			if (price[cur_idx] < cur_num) {
				continue;
			}
			for (int i = 0; i < adjlist[cur_idx].size(); i++) {
				Node next_node = adjlist[cur_idx].get(i);
				int next_idx = next_node.idx;
				int next_num = next_node.num;
				if (price[next_idx] > price[cur_idx] + next_num) {
					price[next_idx] = price[cur_idx] + next_num;
					pq.add(new Node(next_idx, price[next_idx]));
				}
			}

		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		price = new int[N + 1];
		adjlist = new ArrayList[N + 1];
		for (int i = 0; i < adjlist.length; i++) {
			adjlist[i] = new ArrayList<Node>();
		}
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			adjlist[st].add(new Node(end, weight));
			adjlist[end].add(new Node(st, weight));
		}
		int start = 1;
		int end = N;
		int v1 = sc.nextInt();
		int v2 = sc.nextInt();

		Arrays.fill(price, INF);
		dijkstra(start);
		int[] price1 = price.clone();
		Arrays.fill(price, INF);
		dijkstra(v1);
		int[] price2 = price.clone();
		Arrays.fill(price, INF);
		dijkstra(v2);
		int[] price3 = price.clone();
		boolean flag1 = true;
		boolean flag2 = true;
		if (price1[end] == INF) {
			System.out.println(-1);
		} else {
			int result1 = 0;
			int result2 = 0;
			if (price1[v1] == INF || price2[v2] == INF || price3[end] == INF) {
				result1 = INF;
			} else {
				result1 = price1[v1] + price2[v2] + price3[end];
			}
			if (price1[v2] == INF || price3[v1] == INF || price1[end] == INF) {
				result2 = INF;

			} else {
				result2 = price1[v2] + price3[v1] + price2[end];
			}
			if (result1 == INF && result2 == INF) {
				System.out.println(-1);
			} else
				System.out.println(Math.min(result1, result2));
		}
	}
}