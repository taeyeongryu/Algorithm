import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

	static int N, M;
	static int[][] arr;
	static int[][] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = 1;
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (visited[nr][nc] == 0 && arr[nr][nc] == 1) {
					visited[nr][nc] = visited[p.r][p.c] + 1;
					q.add(new Point(nr, nc));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = tmp.charAt(j) - '0';
			}
		}
		bfs();
		System.out.println(visited[N-1][M-1]);
	}
}