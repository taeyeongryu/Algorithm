import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static class Point {
		int r;
		int c;
		int use;

		public Point(int r, int c, int use) {
			super();
			this.r = r;
			this.c = c;
			this.use = use;
		}

	}

	// 상 우 하 좌
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int N, K;
	static int max = 0;
	static boolean[][] visited;
	static int[][] map;

	static void dfs(int depth, int r, int c, boolean flag) {
//		System.out.println("r : "+r+", c : "+c);
//		System.out.println(max);
//		System.out.println(Arrays.deepToString(visited));
//		System.out.println(Arrays.deepToString(map));
		for (int i = 0; i < dc.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}
			// 내가 부신놈
			if (flag) {
				// 만약 다음 높이가 더 높거나 같으면
				if (map[nr][nc] >= map[r][c]) {
					max = Math.max(depth + 1, max);
				} else {
					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						dfs(depth + 1, nr, nc, flag);
						visited[nr][nc] = false;
					}
				}
			}
			// 내가 안부신놈
			else {
				if (map[nr][nc] >= map[r][c]) {
					int cha = map[nr][nc] - map[r][c];
					if (K > cha) {
						for (int j = cha + 1; j <= K; j++) {
							if (map[nr][nc] - j >= 0) {
								if (!visited[nr][nc]) {
//								System.out.println("nr : "+nr+", nc : "+nc);
									map[nr][nc] -= j;
									visited[nr][nc] = true;
									dfs(depth + 1, nr, nc, true);
									visited[nr][nc] = false;
									map[nr][nc] += j;
								}
							}
						}
					}
					else {
						max = Math.max(depth + 1, max);
					}
				} else {
					visited[nr][nc] = true;
					dfs(depth + 1, nr, nc, flag);
					visited[nr][nc] = false;
				}
			}

		}
		// 여기에 리턴 조건 달아주자
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			N = sc.nextInt();
			K = sc.nextInt();
			visited = new boolean[N][N];
			map = new int[N][N];
			int a = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					a = Math.max(a, map[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == a) {
						visited = new boolean[N][N];
						visited[i][j] = true;
						dfs(0, i, j, false);
					}
				}
			}
			System.out.printf("#%d %d%n",tc,max);
		}
	}
}