import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 나이트 처럼 몇번 이동했는지, r, c 로 나눈다.
	static class Monkey {
		int r;
		int c;
		int count;
		int num;

		@Override
		public String toString() {
			return "Monkey [r=" + r + ", c=" + c + ", count=" + count + ", num=" + num + "]";
		}

		public Monkey(int r, int c, int count, int num) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
			this.num = num;
		}

	}

	static int[] dr = { 1, -1, 0, 0, -2, -2, 2, 2, 1, -1, 1, -1 };
	static int[] dc = { 0, 0, 1, -1, 1, -1, 1, -1, 2, 2, -2, -2 };
	static boolean[][] map;

	static int visited[][];
	static int K, W, H;

	static int bfs() {
		Queue<Monkey> q = new LinkedList<>();
		visited[0][0] = 0;
		q.add(new Monkey(0, 0, 0, 0));
		while (!q.isEmpty()) {
//			System.out.println(q);
			Monkey curm = q.poll();
			int r = curm.r;
			int c = curm.c;
			int count = curm.count;
			int num = curm.num;
			if (r == H - 1 && c == W - 1) {
				return num;
			}
			for (int i = 0; i < dr.length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
					continue;
				}
				if (map[nr][nc]==false) {
					continue;
				}
				// 그냥 이동
				if (i < 4) {
					int ncount = count;
					if (visited[nr][nc] > ncount) {
						visited[nr][nc] = ncount;
						q.add(new Monkey(nr, nc, ncount, num + 1));
					}
				}
				// 말처럼 이동
				else {
					int ncount = count + 1;
					// K보다 작거나 같은지 체크
					if (ncount <= K) {
						if (visited[nr][nc] > ncount) {
							visited[nr][nc] = ncount;
							q.add(new Monkey(nr, nc, ncount, num + 1));
						}
					} else {
						// 더이상 볼필요가 없다.
						break;
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		visited = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		map = new boolean[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (sc.nextInt() == 0) {
					map[i][j] = true;
				}
			}
		}
		System.out.println(bfs());
//		System.out.println(Arrays.deepToString(visited));
	}

}