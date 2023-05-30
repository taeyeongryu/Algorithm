import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;
		boolean crush;

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", crush=" + crush + "]";
		}

		public Point(int r, int c, boolean crush) {
			super();
			this.r = r;
			this.c = c;
			this.crush = crush;
		}
	}

	static int[][] map;
	static int[][][] visited;
	static int N, M;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, false));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				// 벽 없을 때
				if (map[nr][nc] == 0) {
					// 깬적 있을때
					if (cur.crush) {
						if (visited[1][nr][nc] == 0) {
							visited[1][nr][nc] = visited[1][cur.r][cur.c] + 1;
							q.add(new Point(nr, nc, true));
						}
					}
					// 깬적 없을때
					else {
						if (visited[0][nr][nc] == 0) {
							visited[0][nr][nc] = visited[0][cur.r][cur.c] + 1;
							q.add(new Point(nr, nc, false));
						}
					}
				}
				// 벽 있을 때
				else {
					if (!cur.crush) {
						if (visited[1][nr][nc] == 0) {
							visited[1][nr][nc] = visited[0][cur.r][cur.c] + 1;
							q.add(new Point(nr, nc, true));
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[2][N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs();
		if (N == 1 && M == 1) {
			System.out.println(1);
		} else if (visited[0][N - 1][M - 1] == 0 && visited[1][N - 1][M - 1] == 0) {
			System.out.println(-1);
		} else if (visited[0][N - 1][M - 1] == 0) {
			System.out.println(visited[1][N - 1][M - 1] + 1);
		} else if (visited[1][N - 1][M - 1] == 0) {
			System.out.println(visited[0][N - 1][M - 1] + 1);
		} else {
			System.out.println(Math.min(visited[0][N - 1][M - 1], visited[1][N - 1][M - 1]) + 1);
		}
	}
}