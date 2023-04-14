import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int count;
		int wall;

		public Point(int r, int c, int count, int wall) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
			this.wall = wall;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", count=" + count + ", wall=" + wall + "]";
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.count, o.count);
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][][] visited;
	static int N, M, K, result = -1;

	static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		visited[0][0][0] = true;
		pq.add(new Point(0, 0, 1, 0));
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			int cur_r = cur.r;
			int cur_c = cur.c;
			int cur_count = cur.count;
			int cur_wall = cur.wall;
			if (cur_r == N-1 && cur_c == M-1) {
				result = cur.count;
				return;
			}
			for (int i = 0; i < dc.length; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (map[nr][nc] == '0') {
					if (!visited[cur_wall][nr][nc]) {

						visited[cur_wall][nr][nc] = true;
						pq.add(new Point(nr, nc, cur_count + 1, cur_wall));
					}
				} else {
					// wall이 K이상이면 더이상 갈 수 없다.
					if (cur_wall < K) {
						if (!visited[cur_wall + 1][nr][nc]) {
							visited[cur_wall + 1][nr][nc] = true;
							pq.add(new Point(nr, nc, cur_count + 1, cur_wall + 1));
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[K+1][N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i] = str.toCharArray();
			}
		}
		bfs();
		System.out.println(result);
	}

}