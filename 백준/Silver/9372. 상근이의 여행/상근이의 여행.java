import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N;

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

	static int[][] adjarr;
	static boolean[] choice;

	static int prim(int start) {
		int result = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int idx = cur_node.idx;
			int weight = cur_node.weight;
			if (choice[idx])
				continue;
			choice[idx] = true;
			result += weight;
			for (int i = 1; i <= N; i++) {
				if (choice[i])
					continue;
				if (adjarr[idx][i] == 1) {
					pq.offer(new Node(i, adjarr[idx][i]));

				}

			}

		}
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			int M = sc.nextInt();
			adjarr = new int[N + 1][N + 1];
			choice = new boolean[N + 1];
			for (int i = 1; i <= M; i++) {
				int st = sc.nextInt();
				int end = sc.nextInt();
				adjarr[st][end] = 1;
				adjarr[end][st] = 1;
			}
//			System.out.println(Arrays.deepToString(adjarr));
			int result = prim(1);
			System.out.println(result);

		}
	}

}