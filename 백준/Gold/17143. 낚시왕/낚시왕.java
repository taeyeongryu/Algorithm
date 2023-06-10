import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Shark {
		int r;
		int c;
		int dir;
		int speed;
		int size;

		public Shark(int r, int c, int dir, int speed, int size) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.speed = speed;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", dir=" + dir + ", speed=" + speed + ", size=" + size + "]";
		}
	}

	// 상 하 우 좌 순서
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };
	static int[][] map;
	static int R, C, M, total;
	static Shark[] sharkarr;

	static void moveShark() {
		int[][] tmp = new int[R + 1][C + 1];
		for (int i = 1; i < sharkarr.length; i++) {
			if (sharkarr[i] != null) {
				Shark cur = sharkarr[i];
				int speed = cur.speed;
				int dir = cur.dir;
				int realmove = 0;
				if (cur.r == R && dir == 2) {
					cur.dir = 1;
				}
				if (cur.r == 1 && dir == 1) {
					cur.dir = 2;
				}
				if (cur.c == C && dir == 3) {
					cur.dir = 4;
				}
				if (cur.c == 1 && dir == 4) {
					cur.dir = 3;
				}
				dir = cur.dir;
				switch (dir) {
				// 상
				case 1:
					realmove = speed % ((R - 1) * 2);
					if (realmove > cur.r - 1) {
						for (int j = 0; j < realmove; j++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];
							if (nr < 1 || nc < 1 || nr > R || nc > C) {
								cur.r -= dr[dir];
								cur.c -= dc[dir];
								dir = dir == 1 ? 2 : 1;
							} else {
								cur.r += dr[dir];
								cur.c += dc[dir];

							}
						}
					}
					// 방향전환 안한다.
					else {
						cur.r = cur.r + dr[dir] * realmove;
						cur.c = cur.c + dc[dir] * realmove;
					}
					break;
				// 하
				case 2:
					realmove = speed % ((R - 1) * 2);
					if (realmove > R - cur.r) {
						for (int j = 0; j < realmove; j++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];
							if (nr < 1 || nc < 1 || nr > R || nc > C) {
								cur.r -= dr[dir];
								cur.c -= dc[dir];
								dir = dir == 1 ? 2 : 1;
							} else {
								cur.r += dr[dir];
								cur.c += dc[dir];

							}
						}
					}
					// 방향전환 안한다.
					else {
						cur.r = cur.r + dr[dir] * realmove;
						cur.c = cur.c + dc[dir] * realmove;
					}
					break;
				// 우
				case 3:
					realmove = speed % ((C - 1) * 2);
					if (realmove > C - cur.c) {
						for (int j = 0; j < realmove; j++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];
							if (nr < 1 || nc < 1 || nr > R || nc > C) {
								cur.r -= dr[dir];
								cur.c -= dc[dir];
								dir = dir == 4 ? 3 : 4;
							} else {
								cur.r += dr[dir];
								cur.c += dc[dir];

							}
						}
					}
					// 방향전환 안한다.
					else {
						cur.r = cur.r + dr[dir] * realmove;
						cur.c = cur.c + dc[dir] * realmove;
					}
					break;
				// 좌
				case 4:
					realmove = speed % ((C - 1) * 2);
					if (realmove > cur.c - 1) {
						for (int j = 0; j < realmove; j++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];
							if (nr < 1 || nc < 1 || nr > R || nc > C) {
								cur.r -= dr[dir];
								cur.c -= dc[dir];
								dir = dir == 4 ? 3 : 4;
							} else {
								cur.r += dr[dir];
								cur.c += dc[dir];

							}
						}
					}
					// 방향전환 안한다.
					else {
						cur.r = cur.r + dr[dir] * realmove;
						cur.c = cur.c + dc[dir] * realmove;
					}
					break;

				}
				cur.dir=dir;
				// 이제 상어를 먹을 차례
//				System.out.println("i : " + i);
				if (tmp[cur.r][cur.c] > 0) {
					if (sharkarr[tmp[cur.r][cur.c]].size < cur.size) {
						sharkarr[tmp[cur.r][cur.c]] = null;
						tmp[cur.r][cur.c] = i;
					}
					else {
						sharkarr[i] = null;
					}
				} else {
					tmp[cur.r][cur.c] = i;
				}
			}
		}
		map = tmp;
	}

	static void fishing(int c) {
		for (int i = 1; i <= R; i++) {
			if (map[i][c] > 0) {
				Shark a = sharkarr[map[i][c]];
				total += a.size;
				sharkarr[map[i][c]] = null;
				map[i][c] = 0;
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		sharkarr = new Shark[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharkarr[i] = new Shark(r, c, d, s, z);
			map[r][c] = i;
		}
		for (int i = 1; i <= C; i++) {
			fishing(i);
			moveShark();
//			for (int j = 1; j <= R; j++) {
//				for (int j2 = 1; j2 <= C; j2++) {
//					System.out.print(map[j][j2] + " ");
//
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println(Arrays.toString(sharkarr));
		}
		System.out.println(total);
	}

}