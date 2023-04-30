import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

	static int N, M, result, time;
	static int[][] map;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static boolean[][] visited, melting;
	static List<Point> list = new ArrayList<>();

	static void melt() {
		boolean flag = false;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (melting[i][j]) {
					flag = true;
					cnt++;
					map[i][j] = 0;
					melting[i][j] = false;
				}
			}
		}
		time = flag ? time + 1 : time;
		result = cnt > 0 ? cnt : result;
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		list.clear();
		// 리스트에 들어간 건 이미 방문 체크 해준것이라 따로 해줄 필요 없다.

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (!visited[nr][nc]) {
					if (map[nr][nc] == 1) {
						visited[nr][nc] = true;
						melting[nr][nc] = true;
						list.add(new Point(nr, nc));

					}
					// 0이면 그냥 바로 큐에 넣고 끝냄
					else {
						visited[nr][nc] = true;
						q.add(new Point(nr, nc));
					}
				}
			}
		}
		melt();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		melting = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		list.add(new Point(0, 0));
		while (list.size() != 0) {
			bfs();
		}
		System.out.println(time);
		System.out.println(result);
	}
}