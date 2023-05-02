import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static int N, M, max = Integer.MIN_VALUE, total = 0;
	static boolean[][] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static void bfs(int r, int c) {
		int count = 1;
		Queue<Point> q = new LinkedList<>();
		Point start = new Point(r, c);
		visited[r][c] = true;
		q.add(start);
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (visited[nr][nc]) {
					continue;
				}
				if (map[nr][nc]==1) {
					count++;
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			}
		}
		max = Integer.max(max, count);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					total++;
					bfs(i, j);
				}
			}
		}
		System.out.println(total);
		System.out.println(total==0?0:max);
	}

}