import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
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

	static final int MX = 120;
	static int T, h, w, cnt;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] key;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static String keystr;
	static List<Point>[] door;
	static Queue<Point> q;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
//		T = sc.nextInt();
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
//			h = sc.nextInt();
//			w = sc.nextInt();
			cnt = 0;
			map = new char[h + 2][w + 2];
			visited = new boolean[h + 2][w + 2];
			key = new boolean[26];
			door = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				door[i] = new ArrayList<>();
			}
			for (int i = 0; i < h + 2; i++) {
				for (int j = 0; j < w + 2; j++) {
					map[i][j] = '.';
				}
			}
			for (int i = 1; i <= h; i++) {
				String tmp = br.readLine().trim();
//				String tmp = sc.next();
				for (int j = 1; j <= w; j++) {
					map[i][j] = tmp.charAt(j - 1);
				}
			}
			keystr = br.readLine().trim();

			//// 여기부터~~
			if (!keystr.equals("0")) {
				for (int i = 0; i < keystr.length(); i++) {
					int tmp = keystr.charAt(i) - 'a';
					key[tmp] = true;
				}
			}
			bfs();
			bw.write("" + cnt + "\n");
		}
		bw.close();
		br.close();
	}
	private static void bfs() {
		q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= h + 2 || nc >= w + 2) {
					continue;
				}
				if (!visited[nr][nc]) {
					if (map[nr][nc] != '*') {
						// 대문자라면
						if ('A' <= map[nr][nc] && map[nr][nc] <= 'Z') {
							// 키가 있다면
							if (key[map[nr][nc] - 'A']) {
								map[nr][nc] = '.';
								visited[nr][nc] = true;
								q.add(new Point(nr, nc));
							}
							// 키가 없다면
							else {
								door[map[nr][nc] - 'A'].add(new Point(nr, nc));
							}
						}
						// 소문자라면
						else if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
							key[map[nr][nc] - 'a'] = true;
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
						// 문서라면
						else if (map[nr][nc] == '$') {
							cnt++;
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						} else if (map[nr][nc] == '.') {
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
						// 빈칸이라면

					}
				}
				for (int j = 0; j < 26; j++) {
					if (door[j].size() != 0 && key[j]) {
						for (int k = 0; k < door[j].size(); k++) {
							Point tmp = door[j].get(k);
							map[tmp.r][tmp.c] = '.';
							visited[tmp.r][tmp.c] = true;
							q.add(tmp);
						}
						door[j].clear();
					}
				}
			}
		}
	}
}
