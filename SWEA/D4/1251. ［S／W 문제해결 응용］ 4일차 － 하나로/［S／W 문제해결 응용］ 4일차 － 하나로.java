import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
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
	static int[] P;

	// 부모찾는 것이랑
	static int find(int x) {
		if (P[x] == x) {
			return P[x];
		} else {
			P[x] = find(P[x]);
			return P[x];
		}
	}

	// 합치는것이랑
	static void union(int x, int y) {
		P[find(x)] = find(y);
	}

	// 두 정점이 같은 그룹인지 확인하는 메서드
	static boolean unioncheck(int x, int y) {
		return find(x) == find(y);
	}

	static int[] choice = new int[2];

	static void combi(int count, int at) {
		if (count == 2) {
			island tmp1 = island_arr[choice[0]];
			island tmp2 = island_arr[choice[1]];
			// 거리 계산하자.
			long x = Math.abs(tmp1.x - tmp2.x);
			long y = Math.abs(tmp1.y - tmp2.y);
			double num = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			edgeList.add(new Edge(choice[0], choice[1], num));
			return;
		}
		for (int i = at; i <= N; i++) {
			choice[count] = i;
			combi(count + 1, i + 1);
		}
	}

	static List<Edge> edgeList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			edgeList = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			P = new int[N + 1];
			island_arr = new island[N + 1];
			String island_x = br.readLine();
			String island_y = br.readLine();
			StringTokenizer st1 = new StringTokenizer(island_x);
			StringTokenizer st2 = new StringTokenizer(island_y);
			for (int i = 1; i <= N; i++) {
				island_arr[i] = new island(Long.parseLong(st1.nextToken()), Long.parseLong(st2.nextToken()));
			}
			double tax = Double.parseDouble(br.readLine());
			combi(0, 1);
//			System.out.println(Arrays.toString(island_arr));
			Collections.sort(edgeList, new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					// TODO Auto-generated method stub
					return o1.num - o2.num >= 0 ? 1 : -1;
				}

			});
			for (int i = 1; i <= N; i++) {
				P[i] = i;
			}
			double ans = 0;
			int pick = 0;
			for (int i = 0; i < edgeList.size(); i++) {
				long x = edgeList.get(i).start;
				long y = edgeList.get(i).end;

				if (find((int) x) != find((int) y)) {
					union((int) x, (int) y);
					ans += Math.pow(edgeList.get(i).num, 2) * tax;
					pick++;
				}
				if (pick == N - 1) {
					break;
				}
			}
			System.out.printf("#%d %d%n", tc, (long) Math.round(ans));
		}

	}

}