import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int N, M, K, result;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						visited[i][j] = true;
						result++;
						q.add(new Point(i, j));
						bfs();
					}
				}
			}
			bw.append(result + "\n");
		}

		bw.close();
		br.close();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < dc.length; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (!visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			}
		}
	}

}
