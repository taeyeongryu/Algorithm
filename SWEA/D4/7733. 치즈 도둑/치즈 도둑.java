import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;

		}
	}

	static int N;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[][] cheese;
	static boolean[][] visited;
	static Queue<Node> queue = new LinkedList<>();

	static void BFS() {
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = tmp.r + dr[i];
				int nc = tmp.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				if (!visited[nr][nc] && cheese[nr][nc] != 0) {
					visited[nr][nc] = true;
					queue.offer(new Node(nr, nc));
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int max = 1;
			N = sc.nextInt();
			cheese = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cheese[i][j] = sc.nextInt();
				}
			}
			for (int i = 1; i <= 100; i++) {
				for (int j = 0; j < N; j++) {
					for (int j2 = 0; j2 < N; j2++) {
						if (cheese[j][j2] == i) {
							cheese[j][j2] = 0;
						}
					}
				}
				// i번 째날에 모두 먹어치웠다.
				visited = new boolean[N][N];
				int count = 0;
				for (int j = 0; j < N; j++) {
					for (int j2 = 0; j2 < N; j2++) {
						if (!visited[j][j2] && cheese[j][j2] != 0) {
							count++;
							visited[j][j2]=true;
							queue.offer(new Node(j, j2));
							BFS();
						}
					}
				}
				max=Math.max(max, count);
			}
			System.out.printf("#%d %d%n",tc,max);
		}
	}

}