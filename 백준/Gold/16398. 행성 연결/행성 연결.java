import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N;

	static class Node implements Comparable<Node> {
		int idx;
		long weight;

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + "]";
		}

		public Node(int idx, long weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}

	}

	static int[][] adjarr;
	static boolean[] choice;

	static long prim(int start) {
		long result = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int idx = cur_node.idx;
			long weight = cur_node.weight;
			if (choice[idx])
				continue;
			choice[idx] = true;
			result += weight;
			for (int i = 1; i <= N; i++) {
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
		adjarr = new int[N + 1][N + 1];
		choice = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {

				int num = sc.nextInt();
				adjarr[i][j] = num;
			}
		}
//		System.out.println(Arrays.deepToString(adjarr));
		long result = prim(1);
		System.out.println(result);

	}

}