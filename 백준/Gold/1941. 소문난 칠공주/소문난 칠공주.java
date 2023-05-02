import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] result = new int[7];
	static boolean[][] goal;
	static char[][] map;
	static int total = 0;

	// 이다솜 파가 4명 이상인지
	static boolean bfs() {
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		Point start = new Point(result[0] / 5, result[0] % 5);
		cnt++;
		q.add(start);
		goal[start.r][start.c] = false;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
					continue;
				}
				if (goal[nr][nc]) {
					cnt++;
					goal[nr][nc] = false;
					q.add(new Point(nr, nc));
				}

			}
		}
		if (cnt == 7) {
			return true;
		}
		return false;
	}

	static boolean check() {
		goal = new boolean[5][5];
		int lee = 0;
		for (int i = 0; i < result.length; i++) {
			int tmp = result[i];
			int r = tmp / 5;
			int c = tmp % 5;
			goal[r][c] = true;
			if (map[r][c] == 'S') {
				lee++;
			}
		}
		if (lee >= 4) {
			return true;
		}
		return false;
	}

	static void combi(int depth, int at) {
		if (depth == 7) {
//			System.out.println(Arrays.toString(result));
			// 임도연파인지 이다솜 파인지 확인해야한다.
			if (check()) {
				if (bfs()) {
					total++;
				}
			}
			return;
		}

		for (int i = at; i < 25; i++) {
			result[depth] = i;
			combi(depth + 1, i + 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String tmp = sc.next();
			for (int j = 0; j < 5; j++) {
				map[i][j]=tmp.charAt(j);
			}
		}
		combi(0, 0);
		System.out.println(total);
	}

}