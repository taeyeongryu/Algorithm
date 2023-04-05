import java.util.Scanner;

public class Solution {
	static int N, M, H;
	static int[][] map;
	static int max;

	static int cal(int r, int c, int k) {
		int price = (int) (2 * Math.pow(k, 2) - 2 * k + 1);
		int count = 0;
		int left = c;
		int right = c;
		for (int i = r - k + 1; i < r + k; i++) {
			for (int j = left; j <= right; j++) {
				if (i < 0 || j < 0 || i >= N || j >= N) {
					continue;
				}
				if (map[i][j] == 1) {
					count++;
				}
			}
			if (i < r) {
				left--;
				right++;

			} else {
				left++;
				right--;
			}
		}
		if (price <= (count * M)) {
			return count;
		} else {
			return -1;
		}
	}

	static void letsgo() {
		// k정해짐
		for (int k = 1; (int) (2 * Math.pow(k, 2) - 2 * k + 1) <= M * H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int num = cal(i, j, k);
					max = Math.max(max, num);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			H = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						H++;
					}
				}
			}
			letsgo();
			System.out.printf("#%d %d%n",tc,max);
		}
	}
}