import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[][] map;
	static int R, C, T, mr1, mc1, mr2, mc2;

	static void spread() {
		int[][] tmp = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int count = 0;
				if (map[i][j] > 0) {
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
							continue;
						}
						if (map[nr][nc] == -1) {
							continue;
						}
						tmp[nr][nc] += map[i][j] / 5;
						count++;
					}
				}
				tmp[i][j] -= map[i][j];
				tmp[i][j] += map[i][j] - (map[i][j] / 5) * count;
			}
		}
		map = tmp;
	}

	static int remain() {
		int total = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					total += map[i][j];
				}
			}
		}
		return total;
	}

	static int letsgo() {
		for (int i = 0; i < T; i++) {
			spread();
			cleanup(mr1, mc2);
			cleandown(mr2, mc2);
		}
		return remain();
	}

	static void cleanup(int r, int c) {
		int nr = r;
		int nc = c;
		int dir = 0;
		// for문으로 가자
		for (int i = 0; i < r; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				if (i == 0) {
					map[nr][nc] = 0;
				} else {
					map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
					map[nr][nc] = 0;
				}
			}
		}

		// 방향전환(우회전)
		dir = (dir + 1) % 4;
		for (int i = 0; i < C - 1; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
				map[nr][nc] = 0;
			}
		}
		// 방향전환(우회전)
		dir = (dir + 1) % 4;
		for (int i = 0; i < r; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
				map[nr][nc] = 0;
			}
		}
		dir = (dir + 1) % 4;
		for (int i = 0; i < C - 2; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
				map[nr][nc] = 0;
			}
		}
	}

	static void cleandown(int r, int c) {
		int nr = r;
		int nc = c;
		int dir = 2;
		// for문으로 가자
		for (int i = 0; i < R - r - 1; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				if (i == 0) {
					map[nr][nc] = 0;
				} else {
					map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
					map[nr][nc] = 0;
				}
			}
		}

		// 방향전환(좌회전)
		dir = (dir + 3) % 4;
		for (int i = 0; i < C - 1; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
				map[nr][nc] = 0;
			}
		}
		// 방향전환(좌회전)
		dir = (dir + 3) % 4;
		for (int i = 0; i < R - r - 1; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
				map[nr][nc] = 0;
			}
		}
		dir = (dir + 3) % 4;
		for (int i = 0; i < C - 2; i++) {
			// 다음 좌표를 찾고
			nr += dr[dir];
			nc += dc[dir];
//			System.out.println("nr : " + nr + ", nc : " + nc);
			if (map[nr][nc] != 0) {
				map[nr - dr[dir]][nc - dc[dir]] = map[nr][nc];
				map[nr][nc] = 0;
			}
		}

	}

	// 상, 우, 하, 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (count == 0) {
					if (map[i][j] == -1) {
						mr1 = i;
						mc1 = j;
						count++;
					}
				} else {
					if (map[i][j] == -1) {
						mr2 = i;
						mc2 = j;
					}
				}

			}
		}
		System.out.println(letsgo());
	}
}