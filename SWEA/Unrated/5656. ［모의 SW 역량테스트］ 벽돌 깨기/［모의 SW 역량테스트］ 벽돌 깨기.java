import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Point {
		int r;
		int c;

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N;
	static int W;
	static int H;
	static int min = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] tmp;
	static int[] choice;
	static int[] remainblock;
	static int[] remainblock_tmp;
	static Queue<Point> q = new LinkedList<>();

	static void breakblock(Point point) {
		int r = point.r;
		int c = point.c;

		if (tmp[r][c] != 0) {
			int num = tmp[r][c];
			tmp[r][c] = 0;
			remainblock_tmp[c]--;
			for (int i = 1; i < num; i++) {
				for (int j = 0; j < dc.length; j++) {
					int nr = r + i * dr[j];
					int nc = c + i * dc[j];
					if (nr < 0 || nc < 0 || nr >= H || nc >= W)
						continue;
					if (tmp[nr][nc] != 0) {
						q.offer(new Point(nr, nc));
					}
				}
			}
		}
	}

	static void down() {
		// 열
		for (int i = 0; i < W; i++) {
			// 행
			for (int j = H - 1; j >= 0; j--) {
				// j,i에 알맞은 숫자를 적어넣는 작업
				if (tmp[j][i] != 0) {
					continue;
				}
				int j2 = j;
				j2--;
				while (j2 >= 0) {
					// 위로 올라가면서 숫자를 만나면 ji에 채워넣는다.
					if (tmp[j2][i] == 0) {
						j2--;
					} else {
						tmp[j][i] = tmp[j2][i];
						tmp[j2][i] = 0;
						break;
					}
				}
			}
		}
	}

	static void letsgo() {
		// 총 몇개의 경우를 따져볼지 정해지는 변수
		// 중복 순열이기 대문에 W^N가 되어야 한다.
		int manytime = 1;

		for (int i = 0; i < N; i++) {
			manytime *= W;
		}
		for (int i = 0; i < manytime; i++) {
			int a = i;
			for (int j = 0; j < choice.length; j++) {
				choice[j] = a % W;
				a /= W;
			}
			// choice에 구슬을 떨어트릴 열이 정해짐
			q = new LinkedList<>();
			remainblock_tmp = new int[W];
			for (int j = 0; j < remainblock.length; j++) {
				remainblock_tmp[j] = remainblock[j];
			}
			tmp = new int[H][W];
			for (int j = 0; j < H; j++) {
				for (int j2 = 0; j2 < W; j2++) {
					tmp[j][j2] = map[j][j2];
				}
			}
			outer: for (int j = 0; j < choice.length; j++) {
				// 벽이 다 격파됐는지 확인한다.
				boolean flag = true;
				for (int j2 = 0; j2 < remainblock_tmp.length; j2++) {
					if (remainblock_tmp[j2] != 0) {
						flag = false;
						break;
					}
				}
				if (flag) {
					min = 0;
					return;
				}
				// choice에서 선택된 열의 맨 위의 위치를 확인한다.
				for (int j2 = 0; j2 < H; j2++) {
					if (tmp[j2][choice[j]] != 0) {
						q.add(new Point(j2, choice[j]));
						while (!q.isEmpty()) {
							breakblock(q.poll());
						}
						down();
						continue outer;
					}
				}
			}
			int c = 0;
			for (int j = 0; j < H; j++) {
				for (int j2 = 0; j2 < W; j2++) {
					if (tmp[j][j2] != 0) {
						c++;
					}
				}
			}
			min = Math.min(min, c);
		}
	}

	// 부시는 메서드는 r,c를 매개변수로 받고
	// queue를 이용해서 저장한다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			choice = new int[N];
			remainblock = new int[W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						remainblock[j]++;
					}
				}
			}
			letsgo();
			System.out.printf("#%d %d%n", tc, min);
		}
	}
}