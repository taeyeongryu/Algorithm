import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, whitemax, blackmax;
	static int[] dr = { -1, -1 };
	static int[] dc = { -1, 1 };
	static boolean[][] visited;
	static int[][] map;

	static boolean check(int r, int c) {
		int k = 0;
		int nr = r;
		int nc = c;
		while (true) {
			nr = nr + dr[k];
			nc = nc + dc[k];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				break;
			}
			if (visited[nr][nc]) {
				return false;
			}
		}
		k = 1;
		nr = r;
		nc = c;
		while (true) {
			nr = nr + dr[k];
			nc = nc + dc[k];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				break;
			}
			if (visited[nr][nc]) {
				return false;
			}
		}
		return true;
	}

	static void back(int index, int color, int count) {
		if (index >= N * N) {
//			System.out.println(count);
			if (color == 0) {
//				System.out.println("color : "+color+" "+count);
//				System.out.println(Arrays.deepToString(visited));
				blackmax = Math.max(blackmax, count);
			} else {
//				System.out.println("color : "+color+" "+count);
//				System.out.println(Arrays.deepToString(visited));
				whitemax = Math.max(whitemax, count);
			}
//			System.out.println(Arrays.deepToString(visited));
			return;
		}
		int r = index / N;
		int c = index % N;
//		System.out.println(index);
		boolean flag = check(r, c);
//		System.out.println(flag);
		if (N % 2 == 0) {

			if (flag && map[r][c] == 1 && !visited[r][c]) {
//			System.out.println("yes");
				if (index % N == N - 2) {
					visited[r][c] = true;
					back(index + 3, color, count + 1);
					visited[r][c] = false;

				} else if (index % N == N - 1) {
					visited[r][c] = true;
					back(index + 1, color, count + 1);
					visited[r][c] = false;

				} else {
					visited[r][c] = true;
					back(index + 2, color, count + 1);
					visited[r][c] = false;

				}
			}
			if (index % N == N - 2) {
				back(index + 3, color, count);
			} else if (index % N == N - 1) {
				back(index + 1, color, count);
			} else {
				back(index + 2, color, count);
			}
		} else {
			if (flag && map[r][c] == 1 && !visited[r][c]) {
//				System.out.println("yes");
				visited[r][c] = true;
				back(index + 2, color, count + 1);
				visited[r][c] = false;
			}
			back(index + 2, color, count);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		blackmax = Integer.MIN_VALUE;
		whitemax = Integer.MIN_VALUE;
		visited = new boolean[N][N];
		back(0, 0, 0);
		back(1, 1, 0);
		System.out.println(whitemax+blackmax);
	}

}