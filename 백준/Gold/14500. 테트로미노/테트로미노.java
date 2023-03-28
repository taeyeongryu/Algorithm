import java.util.*;
import java.io.*;

public class Main {
	// 시계방향
	// 상 우 하 좌
	// 0 1 2 3
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N;
	static int M;
	static int[][] map;

	static int go(int r, int c, int shape, int dir, int flip) {
		int[] arr_r = new int[4];
		int[] arr_c = new int[4];
		if (shape == 1) {
			int total = 0;
			for (int i = 0; i < arr_r.length; i++) {
				arr_r[i] = r + i * dr[dir];
				arr_c[i] = c + i * dc[dir];
			}
			// 4가지 모든곳 다 정해졌다.
			// 이제 이 모든 곳이 다 유효한 범위인지 확인해 줘야 한다.
			for (int i = 0; i < arr_r.length; i++) {
				if (arr_r[i] < 0 || arr_r[i] >= N || arr_c[i] < 0 || arr_c[i] >= M) {
					return -1;
				}
			}
			for (int i = 0; i < arr_r.length; i++) {
				total += map[arr_r[i]][arr_c[i]];
			}
			return total;
		} else if (shape == 2) {
			int total = 0;
			arr_r[0] = r;
			arr_c[0] = c;
			arr_r[1] = arr_r[0] + dr[dir];
			arr_c[1] = arr_c[0] + dc[dir];
			arr_r[2] = arr_r[1] + dr[(dir + 1) % 4];
			arr_c[2] = arr_c[1] + dc[(dir + 1) % 4];
			arr_r[3] = arr_r[2] + dr[(dir + 2) % 4];
			arr_c[3] = arr_c[2] + dc[(dir + 2) % 4];
			// 4가지 모든곳 다 정해졌다.
			// 이제 이 모든 곳이 다 유효한 범위인지 확인해 줘야 한다.
			for (int i = 0; i < arr_r.length; i++) {
				if (arr_r[i] < 0 || arr_r[i] >= N || arr_c[i] < 0 || arr_c[i] >= M) {
					return -1;
				}
			}
			for (int i = 0; i < arr_r.length; i++) {
				total += map[arr_r[i]][arr_c[i]];
			}
			return total;
		} else if (shape == 3) {
			int total = 0;
			if (flip == 1) {
				arr_r[0] = r;
				arr_c[0] = c;
				arr_r[1] = arr_r[0] + dr[dir];
				arr_c[1] = arr_c[0] + dc[dir];
				arr_r[2] = arr_r[1] + dr[dir];
				arr_c[2] = arr_c[1] + dc[dir];
				arr_r[3] = arr_r[2] + dr[(dir + 3) % 4];
				arr_c[3] = arr_c[2] + dc[(dir + 3) % 4];
			} else {
				arr_r[0] = r;
				arr_c[0] = c;
				arr_r[1] = arr_r[0] + dr[dir];
				arr_c[1] = arr_c[0] + dc[dir];
				arr_r[2] = arr_r[1] + dr[dir];
				arr_c[2] = arr_c[1] + dc[dir];
				arr_r[3] = arr_r[2] + dr[(dir + 1) % 4];
				arr_c[3] = arr_c[2] + dc[(dir + 1) % 4];
			}
			// 4가지 모든곳 다 정해졌다.
			// 이제 이 모든 곳이 다 유효한 범위인지 확인해 줘야 한다.
			for (int i = 0; i < arr_r.length; i++) {
				if (arr_r[i] < 0 || arr_r[i] >= N || arr_c[i] < 0 || arr_c[i] >= M) {
					return -1;
				}
			}
			for (int i = 0; i < arr_r.length; i++) {
				total += map[arr_r[i]][arr_c[i]];
			}
			return total;
		} else if (shape == 4) {
			int total = 0;
			if (flip == 1) {
				arr_r[0] = r;
				arr_c[0] = c;
				arr_r[1] = arr_r[0] + dr[dir];
				arr_c[1] = arr_c[0] + dc[dir];
				arr_r[2] = arr_r[1] + dr[(dir + 3) % 4];
				arr_c[2] = arr_c[1] + dc[(dir + 3) % 4];
				arr_r[3] = arr_r[2] + dr[dir];
				arr_c[3] = arr_c[2] + dc[dir];
			} else {
				arr_r[0] = r;
				arr_c[0] = c;
				arr_r[1] = arr_r[0] + dr[dir];
				arr_c[1] = arr_c[0] + dc[dir];
				arr_r[2] = arr_r[1] + dr[(dir + 1) % 4];
				arr_c[2] = arr_c[1] + dc[(dir + 1) % 4];
				arr_r[3] = arr_r[2] + dr[dir];
				arr_c[3] = arr_c[2] + dc[dir];
			}
			// 4가지 모든곳 다 정해졌다.
			// 이제 이 모든 곳이 다 유효한 범위인지 확인해 줘야 한다.
			for (int i = 0; i < arr_r.length; i++) {
				if (arr_r[i] < 0 || arr_r[i] >= N || arr_c[i] < 0 || arr_c[i] >= M) {
					return -1;
				}
			}
			for (int i = 0; i < arr_r.length; i++) {
				total += map[arr_r[i]][arr_c[i]];
			}
			return total;
		} else {
			int total = 0;
			arr_r[0] = r;
			arr_c[0] = c;
			arr_r[1] = arr_r[0] + dr[dir];
			arr_c[1] = arr_c[0] + dc[dir];
			arr_r[2] = arr_r[1] + dr[(dir + 1) % 4];
			arr_c[2] = arr_c[1] + dc[(dir + 1) % 4];
			arr_r[3] = arr_r[1] + dr[dir];
			arr_c[3] = arr_c[1] + dc[dir];
			// 4가지 모든곳 다 정해졌다.
			// 이제 이 모든 곳이 다 유효한 범위인지 확인해 줘야 한다.
			for (int i = 0; i < arr_r.length; i++) {
				if (arr_r[i] < 0 || arr_r[i] >= N || arr_c[i] < 0 || arr_c[i] >= M) {
					return -1;
				}
			}
			for (int i = 0; i < arr_r.length; i++) {
				total += map[arr_r[i]][arr_c[i]];
			}
			return total;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 모양
				for (int j2 = 1; j2 <= 5; j2++) {
					// 방향
					for (int k = 0; k < 4; k++) {
						// 플립
						int tmp1 = go(i, j, j2, k, 1);
						int tmp2 = go(i, j, j2, k, -1);
						if (tmp1 != -1) {
							max = Math.max(max, tmp1);
						}
						if (tmp2 != -1) {
							max = Math.max(max, tmp2);
						}
					}
				}
			}
		}
		System.out.println(max);
	}
}