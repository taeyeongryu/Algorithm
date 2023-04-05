import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.idx, o.idx);
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + "]";
		}

		public Node(int idx) {
			super();
			this.idx = idx;
		}

	}

	static int[] indegree;
	static int[] time, dp;
	static int N;
	static List<Node>[] adjlist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		indegree = new int[N + 1];
		time = new int[N + 1];
		dp = new int[N + 1];
		adjlist = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			int weight = sc.nextInt();
			int many = sc.nextInt();
			time[i] = weight;
			if (i == 1) {
			} else {
				indegree[i] += many;
				for (int j = 0; j < many; j++) {
					int tmp = sc.nextInt();
					adjlist[tmp].add(new Node(i));
				}
			}
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				dp[i] = time[i];
				pq.add(new Node(i));
			}
		}
		while (!pq.isEmpty()) {
			Node curnode = pq.poll();
			int idx = curnode.idx;
			for (int i = 0; i < adjlist[idx].size(); i++) {
				Node nextnode = adjlist[idx].get(i);
				int nextidx = nextnode.idx;
				dp[nextidx] = Math.max(dp[nextidx], dp[idx] + time[nextidx]);
				indegree[nextidx]--;
				if (indegree[nextidx] == 0) {
					pq.add(new Node(nextidx));
				}
			}
		}
		int max = 0;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}