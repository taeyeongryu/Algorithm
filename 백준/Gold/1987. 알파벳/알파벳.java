import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int R;
	static int C;
	static char[][] arr;
//	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;

	static void Back(int r, int c, String str) {
//		System.out.println("r : " + r + ", c : " + c + ", str : " + str);
		if (str.indexOf(arr[r][c]) != -1) {
			max = Integer.max(max, str.length());
			return;
		}
//		visited[r][c] = true;
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
				continue;
			}
//			if (visited[nr][nc]) {
//				continue;
//			}
			Back(nr, nc, str + arr[r][c]);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
//		visited = new boolean[R][C];
		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			arr[i] = sc.next().toCharArray();
		}
		Back(0, 0, "");
		System.out.println(max);
	}

}