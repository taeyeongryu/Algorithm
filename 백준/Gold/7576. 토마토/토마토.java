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

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static int M, N, remain, max;
	static int[][] map, visited;
	static Queue<Point> q = new LinkedList<>();

	static void bfs() {
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (visited[nr][nc] == 0 && map[nr][nc] == 0) {
					remain--;
					map[nr][nc] = 1;
					visited[nr][nc] = visited[cur.r][cur.c] + 1;
					max = Integer.max(max, visited[nr][nc]);
					q.add(new Point(nr, nc));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					visited[i][j] = 0;
					q.add(new Point(i, j));
				} else if (map[i][j] == 0) {
					remain++;
				}
			}
		}
		if (remain == 0) {
			System.out.println(0);
		} else {
			bfs();
			if (remain > 0) {
				System.out.println(-1);
			} else {
				System.out.println(max);
			}
		}
	}
}