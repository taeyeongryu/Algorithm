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

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}

	static int N, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static Queue<Point> q = new LinkedList<>();

	static void bfs(int h) {
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = p.r+dr[i];
				int nc = p.c+dc[i];
				
				if (nr<0||nc<0||nr>=N||nc>=N) {
					continue;
				}
				if (visited[nr][nc]) {
					continue;
				}
				if (map[nr][nc]>h) {
					visited[nr][nc]=true;
					q.add(new Point(nr, nc));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// 비는 1부터 최대값의 전 값까지 돌린다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		int maxval = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxval = Math.max(maxval, map[i][j]);
			}
		}
//		System.out.println(Arrays.deepToString(map));
		// 여기서 정해진 값이 물의 높이이다.
		for (int h = 0; h < maxval; h++) {
			visited = new boolean[N][N];
			int islandcnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > h) {
						islandcnt++;
						visited[i][j] = true;
						q.add(new Point(i, j));
						bfs(h);
					}
				}
			}
			max = Math.max(max, islandcnt);
		}
		System.out.println(max);
	}
}