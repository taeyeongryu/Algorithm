import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int[][][] chargemap;
	static int M, A, total;
	static int[] move1, move2;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	static void setcharge(int idx, int y, int x, int c, int p) {
		// 행이 늘어나는 것
		for (int i = 0; i <= c; i++) {
			for (int j = 0; j <= c; j++) {
				if (i + j <= c) {
					if (1 <= x + i && x + i <= 10 && 1 <= y + j && y + j <= 10) {
						chargemap[idx][x + i][y + j] = p;
					}
					if (1 <= x + i && x + i <= 10 && 1 <= y - j && y - j <= 10) {
						chargemap[idx][x + i][y - j] = p;
					}
					if (1 <= x - i && x - i <= 10 && 1 <= y + j && y + j <= 10) {
						chargemap[idx][x - i][y + j] = p;
					}
					if (1 <= x - i && x - i <= 10 && 1 <= y - j && y - j <= 10) {
						chargemap[idx][x - i][y - j] = p;
					}
				}
			}
		}
	}

	static void bfs() {
		int r1 = 1;
		int c1 = 1;
		int r2 = 10;
		int c2 = 10;
		int sum = chargecheck(r1, c1, r2, c2);
		total += sum;
		for (int i = 0; i < M; i++) {
			r1 = r1 + dr[move1[i]];
			c1 = c1 + dc[move1[i]];
			r2 = r2 + dr[move2[i]];
			c2 = c2 + dc[move2[i]];
			sum = chargecheck(r1, c1, r2, c2);
			total += sum;
		}
	}

	static int chargecheck(int r1, int c1, int r2, int c2) {
		// 첫째사람
		int max = 0;
		for (int i = 0; i < A; i++) {
			// 둘째사람
			for (int j = 0; j < A; j++) {
				int total = 0;
				// 같을때
				if (i == j) {
					if (chargemap[i][r1][c1] != 0 && chargemap[j][r2][c2] != 0) {
						total += chargemap[i][r1][c1];
					} else {
						total += chargemap[i][r1][c1];
						total += chargemap[j][r2][c2];
					}
				}
				// 다를때
				else {
					total += chargemap[i][r1][c1];
					total += chargemap[j][r2][c2];
				}
				max = Math.max(max, total);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			total = 0;
			M = sc.nextInt();
			A = sc.nextInt();
			chargemap = new int[A][10 + 2][10 + 2];
			move1 = new int[M];
			move2 = new int[M];
			for (int i = 0; i < M; i++) {
				move1[i] = sc.nextInt();
			}
			for (int i = 0; i < M; i++) {
				move2[i] = sc.nextInt();
			}
			for (int i = 0; i < A; i++) {
				int idx = i;
				int x = sc.nextInt();
				int y = sc.nextInt();
				int c = sc.nextInt();
				int p = sc.nextInt();
				setcharge(idx, x, y, c, p);
			}
			bfs();
			System.out.printf("#%d %d%n", tc, total);
		}
	}

}