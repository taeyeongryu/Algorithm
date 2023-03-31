import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int num;
		int x;
		int y;
		int z;

		@Override
		public String toString() {
			return "Point [num=" + num + ", x=" + x + ", y=" + y + ", z=" + z + "]";
		}

		public Point(int num, int x, int y, int z) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int[] parent;
	static ArrayList<Edge> edgelist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];
		for (int i = 0; i < points.length; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			points[i] = new Point(i, x, y, z);
		}
		edgelist = new ArrayList<>();

		Arrays.sort(points, (p1, p2) -> p1.x - p2.x);
		for (int i = 0; i < N - 1; i++) {
			int weight = Math.abs(points[i].x - points[i + 1].x);
			edgelist.add(new Edge(points[i].num, points[i + 1].num, weight));
		}
		Arrays.sort(points, (p1, p2) -> p1.y - p2.y);
		for (int i = 0; i < N - 1; i++) {
			int weight = Math.abs(points[i].y - points[i + 1].y);
			edgelist.add(new Edge(points[i].num, points[i + 1].num, weight));
		}
		Arrays.sort(points, (p1, p2) -> p1.z - p2.z);
		for (int i = 0; i < N - 1; i++) {
			int weight = Math.abs(points[i].z - points[i + 1].z);
			edgelist.add(new Edge(points[i].num, points[i + 1].num, weight));
		}
		parent = new int[N];
		for (int i = 0; i < points.length; i++) {
			parent[i] = i;
		}
		Collections.sort(edgelist);

		int ans = 0;
		for (int i = 0; i < edgelist.size(); i++) {
			Edge edge = edgelist.get(i);
			if (find(edge.start) == find(edge.end)) {
				continue;
			}
			ans += edge.weight;
			union(edge.start, edge.end);
		}
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();

	}

	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			parent[y] = x;
		}
	}

}