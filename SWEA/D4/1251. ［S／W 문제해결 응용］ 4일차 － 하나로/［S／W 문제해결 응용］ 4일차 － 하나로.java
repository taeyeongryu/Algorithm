import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static class Edge {
		long start;
		long end;
		double num;

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", num=" + num + "]";
		}

		public Edge(long start, long end, double num) {
			super();
			this.start = start;
			this.end = end;
			this.num = num;
		}

	}

	static class Node implements Comparable<Node> {
		int index;
		double num;

		@Override
		public String toString() {
			return "Node [index=" + index + ", num=" + num + "]";
		}

		public Node(int index, double num) {
			super();
			this.index = index;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.num, o.num);
		}

	}

	static class island {
		long x;
		long y;

		island(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "island [x=" + x + ", y=" + y + "]";
		}

	}

	static island[] island_arr;
	static int N;
	static ArrayList<Node>[] adjlist;
	static boolean[] choice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			adjlist = new ArrayList[N + 1];
			island_arr = new island[N + 1];
			choice = new boolean[N + 1];
			String island_x = br.readLine();
			String island_y = br.readLine();
			StringTokenizer st1 = new StringTokenizer(island_x);
			StringTokenizer st2 = new StringTokenizer(island_y);
			for (int i = 1; i <= N; i++) {
				island_arr[i] = new island(Long.parseLong(st1.nextToken()), Long.parseLong(st2.nextToken()));
			}
			for (int i = 0; i <= N; i++) {
				adjlist[i] = new ArrayList<>();
			}
//			System.out.println(Arrays.toString(island_arr));
			for (int i = 1; i <= N; i++) {
				island tmp1 = island_arr[i];
				for (int j = 1; j <= N; j++) {
					if (i != j) {
						island tmp2 = island_arr[j];
						// 거리 계산하자.
						long x = Math.abs(tmp1.x - tmp2.x);
						long y = Math.abs(tmp1.y - tmp2.y);
						double num = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
						adjlist[i].add(new Node(j, num));
					}
				}
			}
			double tax = Double.parseDouble(br.readLine());
//			for (int i = 1; i < adjlist.length; i++) {
//				ArrayList<Node> tmp = adjlist[i];
//				for (Node node : tmp) {
//					System.out.println(node);
//				}
//			}
			int start = 1;
			double ans = 0;
			int pick = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();

			pq.offer(new Node(1, 0));
			while (!pq.isEmpty()) {
				Node tmp = pq.poll();
				int cur_index = tmp.index;
				double cur_num = tmp.num;

				if (choice[tmp.index]) {
					continue;
				}
				ans += tax * Math.pow(cur_num, 2);
				choice[cur_index] = true;

				for (Node node : adjlist[cur_index]) {
					if (!choice[node.index]) {
						pq.add(node);
					}
				}
			}
			System.out.printf("#%d %d%n", tc, Math.round(ans));
//			System.out.printf("#%d %d%n", tc, (long) Math.round(ans));
		}
	}
}