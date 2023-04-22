import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int r;
		int c;
		int cnt;
		char color;

		public Pos(int cnt, char color) {
			this.cnt = cnt;
			this.color = color;
		}

		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int N, M, G, R, len, ans;
	static int[] arrG, arrR;
	static int[][] map;
	static Pos[][] visited;
	static ArrayList<Pos> al = new ArrayList<>();
	static Queue<Pos> q = new LinkedList<>();

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean check(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0) {
			return false;
		}
		return true;
	}

	static void dfs(int idx, int g, int r) {
		if (g == G && r == R) {
			bfs();
			return;
		}
		if (g < G) {
			for (int i = idx; i < len; i++) {
				arrG[g] = i;
				dfs(i + 1, g + 1, r);
			}
		}
		if (r < R) {
			for (int i = idx; i < len; i++) {
				arrR[r] = i;
				dfs(i + 1, g, r + 1);
			}
		}

	}

	private static void bfs() {
		visited = new Pos[N][M];

		for (int i = 0; i < G; i++) {
			Pos p = al.get(arrG[i]);
			q.offer(p);
			visited[p.r][p.c] = new Pos(p.cnt, 'G');
		}
		for (int i = 0; i < R; i++) {
			Pos p = al.get(arrR[i]);
			q.offer(p);
			visited[p.r][p.c] = new Pos(p.cnt, 'R');
		}
		int flower = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			int cnt = visited[now.r][now.c].cnt;
			char color = visited[now.r][now.c].color;

			if (visited[now.r][now.c].color == 'F') {
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (!check(nr, nc)) {
					continue;
				}
				if (visited[nr][nc] == null) {
					visited[nr][nc] = new Pos(now.cnt + 1, color);
					q.offer(new Pos(nr, nc, now.cnt + 1));
				} else if (visited[nr][nc].color == 'G') {
					if (color == 'G' || visited[nr][nc].cnt != cnt + 1) {
						continue;
					}
					flower++;
					visited[nr][nc].color = 'F';
				} else if (visited[nr][nc].color == 'R') {
					if (color == 'R' || visited[nr][nc].cnt != cnt + 1) {
						continue;
					}
					flower++;
					visited[nr][nc].color = 'F';
				}
			}
		}
		ans = Math.max(flower, ans);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		arrG = new int[G];
		arrR = new int[R];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					al.add(new Pos(i, j, 0));
				}
			}
		}
		len = al.size();
		dfs(0, 0, 0);
		System.out.println(ans);
	}
}
