import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;
		int count;

		public Point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", count=" + count + "]";
		}

	}

	static int min = Integer.MAX_VALUE;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[][] map;
	static boolean[][] visited;
	static int N;

	static void bfs(int r, int c, int num) {
		Queue<Point> q = new LinkedList<>();
		Point start = new Point(r, c, 0);
		q.add(start);
		visited[r][c] = true;
		map[r][c] = num;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			int cur_r = cur.r;
			int cur_c = cur.c;
			for (int i = 0; i < dc.length; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (map[nr][nc] == 0) {
					map[nr][nc] = -num;
				}
				// 방문 안했고
				if (!visited[nr][nc]) {
					if (map[nr][nc] == 1) {
						visited[nr][nc] = true;
						map[nr][nc] = num;
						q.add(new Point(nr, nc, 0));
					}
				}
			}

		}
	}

	//
	static void bfsfind(int r, int c, int num) {
		visited = new boolean[N][N];
		// 다음 곳을 탐색할때 num이랑 같은곳, 0인곳은 그냥 가고
		// 아니면 탐색 종료 다른 섬 만난 것.
		Queue<Point> q = new LinkedList<>();
		Point start = new Point(r, c, 1);
		visited[r][c] = true;
		q.add(start);
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int cur_r = cur.r;
			int cur_c = cur.c;
			int cur_count = cur.count;
			for (int i = 0; i < dc.length; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				// 다음곳이 만약 num이랑 같거나, 0이면 q에 넣어주고
				if (map[nr][nc] > 0) {
					continue;
				}
				if (map[nr][nc] == num || map[nr][nc] == 0) {
					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new Point(nr, nc, cur_count + 1));
					}
				} else {
//					System.out.println(cur);
//					System.out.println(Arrays.deepToString(visited));
//					System.out.println(cur_count+1);
					min = Math.min(min, cur_count + 1);
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (map[i][j] == 1) {
//					for (int j2 = 0; j2 < 4; j2++) {
//						int nr = i + dr[j2];
//						int nc = j + dc[j2];
//						if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
//							continue;
//						}
//						if (map[nr][nc]==0) {
//							map[nr][nc] = -1;
//						}
//					}
//				}
//			}
//		}
		int num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, ++num);
//					System.out.println(Arrays.deepToString(map));

				}
			}
		}
		boolean flag = true;
		outer : for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] < 0) {
					// 여기서 사방탐색을 해서 만약에 서로다른 양수가 나오면 볼필요도 없이
					// 그냥 1 리턴
					Set<Integer> over = new HashSet<>();
					for (int k = 0; k < dc.length; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
							continue;
						}
						if (map[nr][nc]>0) {
							over.add(map[nr][nc]);
						}
					}
					if (over.size()>1) {
						System.out.println(1);
						flag = false;
						break outer;
					}
					bfsfind(i, j, map[i][j]);
				}
			}
		}
		if (flag) {
			System.out.println(min == Integer.MAX_VALUE ? 1 : min);
		}
//		System.out.println(Arrays.deepToString(map));
	}
}