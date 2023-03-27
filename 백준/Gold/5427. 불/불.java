import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r;
		int c;
		int num;

		public Node(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] map;
	static boolean[][] visited;
	static int w;
	static int h;
	static int result;
	static Queue<Node> fire_queue;
	static Queue<Node> person_queue;

	static void BFS() {
		int size = 0;
		while (!person_queue.isEmpty()) {
//			System.out.println(Arrays.deepToString(map));
			size = fire_queue.size();
			for (int f = 0; f < size; f++) {
				Node tmp = fire_queue.poll();
				for (int dir = 0; dir < dc.length; dir++) {
					int nr = tmp.r + dr[dir];
					int nc = tmp.c + dc[dir];

					if (0 <= nr && nr < h && 0 <= nc && nc < w) {
						if (map[nr][nc] == '@' || map[nr][nc] == '.') {
							map[nr][nc] = '*';
							fire_queue.offer(new Node(nr, nc, tmp.num + 1));
						}
					}
				}
			}
			size = person_queue.size();
			for (int p = 0; p < size; p++) {
				Node tmp = person_queue.poll();
				for (int dir = 0; dir < dc.length; dir++) {
					int nr = tmp.r + dr[dir];
					int nc = tmp.c + dc[dir];
					if (!(0 <= nr && nr < h && 0 <= nc && nc < w)) {
						result = tmp.num + 1;
						return;
					}
					if (map[nr][nc] == '.') {
						map[nr][nc] = '@';
						person_queue.offer(new Node(nr, nc, tmp.num + 1));
					}
				}
			}
		}
	}

	static void personBFS(int r, int c) {
//		Node person = new Node(r, c, 0);
//		queue.offer(person);
//		while (!queue.isEmpty()) {
//			Node tmp = queue.poll();
//			if (tmp.r == 0 || tmp.r == h - 1 || tmp.c == w - 1 || tmp.c == 0) {
//				result = (tmp.num + 1);
//			}
//			for (int dir = 0; dir < 4; dir++) {
//				int nr = tmp.r + dr[dir];
//				int nc = tmp.c + dc[dir];
//				if (0 <= nr && nr < h && 0 <= nc && nc < w) {
//					if (map[nr][nc] != 1) {
//						if (map[nr][nc] > tmp.num + 1) {
//							queue.add(new Node(nr, nc, tmp.num + 1));
//						}
//					}
//				}
//			}
//		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			result = 0;
			fire_queue = new LinkedList<>();
			person_queue = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			visited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				char[] tmp = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (tmp[j] == '#') {
						map[i][j] = '#';
					} else if (tmp[j] == '*') {
						map[i][j] = '*';
						fire_queue.offer(new Node(i, j, 0));
					} else if (tmp[j] == '@') {
						map[i][j] = '@';
						person_queue.offer(new Node(i, j, 0));
					} else {
						map[i][j] = '.';
					}
				}
			}
			BFS();
			System.out.println(result == 0 ? "IMPOSSIBLE" : result);
		}
		br.close();
	}

}