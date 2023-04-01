import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	// 크루스칼 알고리즘이 훨씬더 편할 것이라고 생각한다.
	// 왜냐햐면 이미 연결된 간선이 주어지기 때문에 유니온 파인드를 쓰면 더 좋을듯
	static class Point {
		int x;
		int y;

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		double distance;

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", distance=" + distance + "]";
		}

		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
			long x = Math.abs(points[start].x - points[end].x);
			long y = Math.abs(points[start].y - points[end].y);
			double x2 = Math.pow(x, 2);
			double y2 = Math.pow(y, 2);
			distance = Math.sqrt(x2 + y2);
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}

	static Point[] points;
	static ArrayList<Edge> edgelist;
	static int parent[];

	static int find(int idx) {
		if (parent[idx] != idx) {
			parent[idx] = find(parent[idx]);
		}
		return parent[idx];
	}

	static boolean union(int start, int end) {
		boolean flag = false;
		start = find(start);
		end = find(end);
		if (start != end) {
			parent[start] = end;
			flag = true;
		}
		return flag;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		points = new Point[N + 1];
		edgelist = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			points[i] = new Point(start, end);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			union(start, end);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				edgelist.add(new Edge(i, j));
			}
		}
		double result = 0;
		Collections.sort(edgelist);
		for (int i = 0; i < edgelist.size(); i++) {
			Edge edge = edgelist.get(i);
			boolean flag = union(edge.start, edge.end);
			if (flag) {
				result += edge.distance;
			}
		}
		System.out.printf("%.2f",result);
		
	}

}