import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static class Node {
		int r;
		int c;
		int num;

		public Node(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", num=" + num + "]";
		}
	}

	// 상 우 하 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M, R, C, L;
	static int[][] map;
	static int[][] visited;

	static void BFS(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		Node start = new Node(r, c, 1);
		visited[r][c] = 1;

		q.add(start);
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int cr = cur.r;
			int cc = cur.c;
			int cnum = cur.num;
			if (L == cnum) {
				continue;
			}
			for (int i = 0; i < dr.length; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				int nnum = cnum + 1;
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				// 상
				if (i == 0) {
					// 지금꺼랑 다음꺼 비교한다.
					if (map[cr][cc] == 1 || map[cr][cc] == 2 || map[cr][cc] == 4 || map[cr][cc] == 7) {
						if (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6) {
							if (visited[nr][nc] == 0) {
								visited[nr][nc] = nnum;
								q.add(new Node(nr, nc, nnum));
							}
						}
					}
				}
				// 우
				else if (i == 1) {
					if (map[cr][cc] == 1 || map[cr][cc] == 3 || map[cr][cc] == 4 || map[cr][cc] == 5) {
						if (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7) {
							if (visited[nr][nc] == 0) {
								visited[nr][nc] = nnum;
								q.add(new Node(nr, nc, nnum));
							}
						}
					}
				}
				// 하
				else if (i == 2) {
					if (map[cr][cc] == 1 || map[cr][cc] == 2 || map[cr][cc] == 5 || map[cr][cc] == 6) {
						if (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7) {
							if (visited[nr][nc] == 0) {
								visited[nr][nc] = nnum;
								q.add(new Node(nr, nc, nnum));
							}
						}
					}
				}
				// 좌
				else if (i == 3) {
					if (map[cr][cc] == 1 || map[cr][cc] == 3 || map[cr][cc] == 6 || map[cr][cc] == 7) {
						if (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5) {
							if (visited[nr][nc] == 0) {
								visited[nr][nc] = nnum;
								q.add(new Node(nr, nc, nnum));
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			map = new int[N][M];
			visited = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			BFS(R, C);
			int c = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] != 0) {
						c++;
					}
				}
			}
			System.out.printf("#%d %d%n", tc, c);
		}
	}

}