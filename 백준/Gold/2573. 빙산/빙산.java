import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static boolean[][] visited;
	static int[][] map;
	static int[][] minus_map;
	static int N;
	static int M;

	// 1 년이 지나서 빙산이 바뀌는 메서드
	// 방문체크는 dfs 들어오자 마자 바로하자
	static void DFS(int r, int c) {
		visited[r][c] = true;

		for (int i = 0; i < dc.length; i++) {
			int next_r = r + dr[i];
			int next_c = c + dc[i];
			if (map[next_r][next_c] != 0 && visited[next_r][next_c] == false) {
				DFS(next_r, next_c);
			}
		}
	}

	static void spen() {
		minus_map = new int[N][M];
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				int count = 0;
				if (map[i][j] != 0) {
					for (int j2 = 0; j2 < dc.length; j2++) {
						int next_r = i + dr[j2];
						int next_c = j + dc[j2];
						if (map[next_r][next_c] == 0) {
							count++;
						}
					}
				}
				minus_map[i][j] = count;
			}
		}
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				if (minus_map[i][j] != 0) {
					map[i][j] = map[i][j] - minus_map[i][j] > 0 ? map[i][j] - minus_map[i][j] : 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int year = 0;
		while (true) {
			boolean flag = false;
			spen();
			year++;
			visited = new boolean[N][M];
			int count = 0;
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					if (map[i][j] != 0 && visited[i][j] == false) {
						flag =true;
						count++;
						DFS(i, j);
					}
				}
			}
			if (count >= 2) {
				System.out.println(year);
				break;
			}
			if (!flag) {
				System.out.println(0);
				break;
			}
			
				
			
		}

//		spen();
//		System.out.println(Arrays.deepToString(map));
//		System.out.println(Arrays.deepToString(visited));
	}
}