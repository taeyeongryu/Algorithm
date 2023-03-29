import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int index;
		int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance >= o.distance ? 1 : -1;
		}
	}

	static final int INF = Integer.MAX_VALUE;

	static int n, m, start;

	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] d;

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		d[start] = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int dist = node.distance;
			int now = node.index;
			if (d[now] < dist) {
				continue;
			}
			for (int i = 0; i < graph.get(now).size(); i++) {
				Node next_node = graph.get(now).get(i);
				int index = next_node.index;
				int distance = next_node.distance;

				if (d[now] + distance < d[index]) {
					d[index] = d[now] + distance;
					pq.offer(new Node(index, d[now] + distance));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		d = new int[n + 1];
		StringTokenizer st;
		Arrays.fill(d, INF);
		for (int i = 0; i < n+1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Node(end, num));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
//		System.out.println(Arrays.toString(d));
		System.out.println(d[end]);
	}
}