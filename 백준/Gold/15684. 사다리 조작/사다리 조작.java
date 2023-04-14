import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	// 세로선, 가로선, 가로선을 놓을 수 있는 위치
	static int N, M, H;
	// 몇개의 가로선을 추가할 것인가
	static int ans;
	static boolean isfinished = false;

	static void dfs(int h, int w, int num) {
		if (isfinished) {
			return;
		}
		if (num == ans) {
			if (check()) {
				isfinished = true;
			}
			return;
		}

		for (int j = w; j < N; j++) {
			if (map[h][j] == 0 && map[h][j + 1] == 0) {
				map[h][j] = 1;
				map[h][j + 1] = 2;
				dfs(h, j + 2, num + 1);
				map[h][j] = 0;
				map[h][j + 1] = 0;
			}
		}

		for (int i = h + 1; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;
					dfs(i, j + 2, num + 1);
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}
	}

	private static boolean check() {
//		System.out.println(Arrays.deepToString(map));
		for (int i = 1; i <= N; i++) {
			int nw = i;
			int nh = 1;
			while (nh <= H) {
				if (map[nh][nw] == 1) {
					nw++;
				} else if (map[nh][nw] == 2) {
					nw--;
				}
				nh++;
			}
			if (nw != i)
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 여기 가로줄에
			int tmp1 = Integer.parseInt(st.nextToken());
			// 이 세로줄에 넣는다.
			int tmp2 = Integer.parseInt(st.nextToken());
			map[tmp1][tmp2] = 1;
			map[tmp1][tmp2 + 1] = 2;
		}
		for (int i = 0; i <= 3; i++) {
			ans = i;
			dfs(1, 1, 0);
			if (isfinished) {
				break;
			}
		}
		System.out.println(isfinished ? ans : -1);
	}
}