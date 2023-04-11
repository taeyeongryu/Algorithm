import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static boolean check(int r, int c, int dir) {
		int row = r;
		int col = c;
		// 지금 높이
		int height = map[r][c];
		int count = 1;
		// 갯수를 정해놨기 때문에 범위를 벗어날 일은 없다.
		for (int i = 1; i < N; i++) {
//			System.out.println("row : " + row + ", col : " + col);
//			System.out.println("height : " + height);
//			System.out.println("count : " + count);
			int nr = row + dr[dir];
			int nc = col + dc[dir];
			if (map[nr][nc] == height) {
				count++;
			}
			// 다음 높이가 더 낮을때
			// 일단 그냥 지나간다
			else if (map[nr][nc] < height) {
				// 차이가 1이면 갱신하고 넘어가고
				if (map[nr][nc] + 1 == height) {
					count = 1;
					height = map[nr][nc];
				}
				// 차이가 1보다 크면
				// 바로 false return
				else {
					return false;
				}
			}
			// 다음 높이가 더 높을때
			else {
				// 차이가 1일때
				// 의미가 있다
				if (map[nr][nc] == height + 1) {
					// 지금 위치에서 반대방향으로 count 만큼 검사한다.
					if (count >= L) {
						// 붙일수 있는지 check 하고
						if (putcheck(row, col, L, (dir + 2) % 4)) {
							// 붙인다.
							put(row, col, L, (dir + 2) % 4);
							height = map[nr][nc];
							count = 1;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
			// 한칸 앞으로 보낸다
//			System.out.println(123);
			row += dr[dir];
			col += dc[dir];
		}
		return true;
	}

	static boolean putcheck(int r, int c, int count, int dir) {
		for (int i = 0; i < count; i++) {
			int row = r + i * dr[dir];
			int col = c + i * dc[dir];
			if (stair[row][col]) {
				return false;
			}
		}
		return true;
	}

	static void put(int r, int c, int count, int dir) {
		for (int i = 0; i < count; i++) {
			int row = r + i * dr[dir];
			int col = c + i * dc[dir];
			stair[row][col] = true;
		}
	}

	static int N, L;
	// 하 우 상 좌
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] stair;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		map = new int[N][N];
		stair = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 행검사
		int count = 0;
		for (int i = 0; i < N; i++) {
			boolean flag1 = check(i, 0, 1);
			boolean flag2 = check(i, N - 1, 3);
			if (flag1 && flag2) {
				count++;
			}
		}
		stair = new boolean[N][N];
		// 열 검사
		for (int i = 0; i < N; i++) {
			boolean flag1 = check(0, i, 0);
			boolean flag2 = check(N - 1, i, 2);
			if (flag1 && flag2) {
				count++;
			}
		}
		System.out.println(count);
//		System.out.println(Arrays.deepToString(map));
//		System.out.println(Arrays.deepToString(stair));
//		System.out.println(check(N-1,N-2,2));
//		System.out.println(Arrays.deepToString(map));
//		System.out.println(Arrays.deepToString(stair));
	}
}