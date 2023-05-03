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

	static int R, C, result;
	static boolean flag = false;
	static char[][] map;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<Point> fire = new LinkedList<>();
	static Queue<Point> person = new LinkedList<>();

	static void bfs() {
		int time = 0;
		int pnum = person.size();
		int fnum = fire.size();
		while (pnum > 0) {
			// q에 넣을때 탈출을 파악한다.
			// System.out.println(Arrays.deepToString(map));
			time++;
			while (fnum-- > 0) {
				Point cur = fire.poll();
				for (int i = 0; i < dc.length; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
						continue;
					}
					if (map[nr][nc] != '#' && map[nr][nc] != '*') {
						map[nr][nc] = '*';
						fire.add(new Point(nr, nc));
					}
				}
			}
			fnum = fire.size();
			while (pnum-- > 0) {
				Point cur = person.poll();

				for (int i = 0; i < dc.length; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
						continue;
					}
					if (map[nr][nc] == '.') {
						if (nr == 0 || nc == 0 || nr == R - 1 || nc == C - 1) {
							result = time;
							flag = true;
							return;
						} else {
							map[nr][nc] = '@';
							person.add(new Point(nr, nc));
						}
					}
				}
			}
			pnum = person.size();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			fire = new LinkedList<>();
			person= new LinkedList<>();
			flag=false;
			map = new char[R][C];
			result = 0;
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '@') {
						if (i == 0 || j == 0 || i == R - 1 || j == C - 1) {
							result = 1;
						}
						person.add(new Point(i, j));
					} else if (map[i][j] == '*') {
						fire.add(new Point(i, j));
					}
				}
			}
			if (result == 1) {
				System.out.println(result);
			} else {
				bfs();
				if (!flag) {
					System.out.println("IMPOSSIBLE");
				} else {
					System.out.println(result + 1);
				}
			}
		}
	}
}