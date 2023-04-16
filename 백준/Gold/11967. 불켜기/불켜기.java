import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

	static int N, M, light = 1;
	static List<Point>[][] list;
	static int[][] map;
	static int[][] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		visited[1][1] = 1;
		q.add(new Point(1, 1));
		while (!q.isEmpty()) {
//			System.out.println(Arrays.deepToString(map));
//			System.out.println(q);
			Point cur = q.poll();
			int cur_r = cur.r;
			int cur_c = cur.c;
			// 스위치 켜준다.
			for (int i = 0; i < list[cur_r][cur_c].size(); i++) {
				Point p = list[cur_r][cur_c].get(i);
				if (map[p.r][p.c] == 0) {
					map[p.r][p.c] = 1;
					light++;
				}
			}
			// 이제 움직인다.
			//
			for (int i = 0; i < 4; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (1 <= nr && nr <= N && 1 <= nc && nc <= N) {
					//그 방불이 켜져있고
					//불을 새로 켰으면!!
					if (map[nr][nc]==1&&visited[nr][nc]<light) {
						visited[nr][nc]=light;
						q.add(new Point(nr, nc));
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
		visited = new int[N + 1][N + 1];
		map = new int[N + 1][N + 1];
		map[1][1] = 1;
		list = new ArrayList[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list[a][b].add(new Point(c, d));
		}
		bfs();
		System.out.println(light);
	}

}