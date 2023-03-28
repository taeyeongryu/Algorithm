import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int[][] maze;
	static int[][] distance;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static void DFS(int r, int c) {
		for (int i = 0; i < dc.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nc < 0 || nr >= 16 || nc >= 16)
				continue;
			if (maze[nr][nc] == 0||maze[nr][nc] == 3) {
				if (distance[nr][nc] == 0) {
					distance[nr][nc] = distance[r][c] + 1;
					DFS(nr, nc);
				} else if (distance[r][c] + 1 < distance[nr][nc]) {
					distance[nr][nc] = distance[r][c] + 1;
					DFS(nr, nc);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int a = sc.nextInt();
			maze = new int[16][16];
			distance = new int[16][16];
			int start_r = 0;
			int start_c = 0;
			int end_r = 0;
			int end_c = 0;
			for (int i = 0; i < 16; i++) {
				String str = sc.next();
				for (int j = 0; j < 16; j++) {
					maze[i][j] = str.charAt(j) - '0';
					if (maze[i][j] == 2) {
						start_r = i;
						start_c = j;
					} else if (maze[i][j] == 3) {
						end_r = i;
						end_c = j;
					}
				}
			}
//			System.out.println(Arrays.deepToString(maze));
			DFS(start_r, start_c);
//			System.out.println(Arrays.deepToString(distance));
			System.out.printf("#%d %d%n",tc,distance[end_r][end_c]>0?1:0);
		}

	}

}