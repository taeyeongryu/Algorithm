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
		int pick = 0;
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
			for (int i = 1; i <= N + 1; i++) {
				if (choice[i])
					continue;
				pq.offer(new Node(i, adjarr[idx][i]));

			}

		}
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjarr = new int[N + 2][N + 2];
		choice = new boolean[N + 2];
		for (int i = 1; i <= N; i++) {
			adjarr[N + 1][i] = sc.nextInt();
			adjarr[i][N + 1] = adjarr[N + 1][i];
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				adjarr[i][j] = sc.nextInt();
				adjarr[j][i] = adjarr[i][j];
			}
		}
		int result = prim(1);
		System.out.println(result);
	}

}