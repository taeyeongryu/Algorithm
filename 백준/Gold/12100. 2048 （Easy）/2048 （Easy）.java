import java.util.Arrays;
import java.util.Scanner;

public class Main {
	// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] map;
	static int[][] tmp;
	static int[] direction = new int[5];
	static boolean[][] sum;
	static int N;
	static int max = Integer.MIN_VALUE;

	// r과c에 있는 원소 끝으로 이동하는 메서드
	// 숫자가 있는 곳에서 메서드 실행 시키면 됨
	// 숫자를 옮기고 나면 원래 있던 자리 0으로 바꿔준다.
	static void go(int r, int c, int dir) {
		int before_r = r;
		int before_c = c;
		while (true) {
			r += dr[dir];
			c += dc[dir];
			if (r < 0 || c < 0 || r >= N || c >= N) {
				return;
			}
			// 끝에 도착하고 빈칸이면
			if (r == 0 && dir == 0 && tmp[r][c] == 0) {
				// 처리하고 return
				tmp[r][c] = tmp[before_r][before_c];
				tmp[before_r][before_c] = 0;
				return;
			} else if (c == 0 && dir == 2 && tmp[r][c] == 0) {
				tmp[r][c] = tmp[before_r][before_c];
				tmp[before_r][before_c] = 0;
				return;

			} else if (r == N - 1 && dir == 1 && tmp[r][c] == 0) {
				tmp[r][c] = tmp[before_r][before_c];
				tmp[before_r][before_c] = 0;
				return;

			} else if (c == N - 1 && dir == 3 && tmp[r][c] == 0) {
				tmp[r][c] = tmp[before_r][before_c];
				tmp[before_r][before_c] = 0;
				return;

			}

			else {
				// 이 자리가 빈칸인지 숫자인지 체크
				if (tmp[r][c] == 0) {
					continue;
				}
				// 숫자라면
				else {
					// 숫자가 같은지 아닌지 확인한다.
					if (tmp[before_r][before_c] == tmp[r][c]) {
						// 바뀐적이 있는지 없는치 체크
						// 바로 앞자린지 확인해야 한다.
						if (sum[r][c]) {
							r -= dr[dir];
							c -= dc[dir];
							tmp[r][c] = tmp[before_r][before_c];
							tmp[before_r][before_c] = 0;
							return;
						}

						else {
							tmp[r][c] = 2 * tmp[r][c];
							sum[r][c] = true;
							tmp[before_r][before_c] = 0;
							return;
						}
					}
					// 삽입
					// 숫자가 다르면 그전 인덱스에 저장하고 리턴
					else {
						// 새위치에 넣고
						// 전 위치에 삭제
						r -= dr[dir];
						c -= dc[dir];
						if (r == before_r && c == before_c) {
							return;
						}
						tmp[r][c] = tmp[before_r][before_c];
						tmp[before_r][before_c] = 0;
						return;
					}
				}
			}
		}
	}

	// 4가지 방향을 정해주는 메서드 중복도 상관없이 무작위 즉 4의 5제곱
	static void dir(int depth) {
		// 방향이 다 정해졌으니 계산하고 리턴해준다.
		if (depth == 5) {
			tmp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			// 5번의 이동을
			for (int i = 0; i < direction.length; i++) {
				sum = new boolean[N][N];
				// 모든 배열을 돌면서 숫자에 적용시킨다.
				switch (direction[i]) {
				// 상
				case 0:
					for (int j = 0; j < N; j++) {
						for (int j2 = 0; j2 < N; j2++) {
							if (tmp[j][j2] != 0) {
								go(j, j2, direction[i]);
							}
						}
					}
					break;
				// 하
				case 1:
					for (int j = N - 1; j >= 0; j--) {
						for (int j2 = 0; j2 < N; j2++) {
							if (tmp[j][j2] != 0) {
								go(j, j2, direction[i]);
							}
						}
					}
					break;
				// 좌
				case 2:
					for (int j = 0; j < N; j++) {
						for (int j2 = 0; j2 < N; j2++) {
							if (tmp[j2][j] != 0) {
								go(j2, j, direction[i]);
							}
						}
					}
					break;
				// 우
				case 3:
					for (int j = N - 1; j >= 0; j--) {
						for (int j2 = 0; j2 < N; j2++) {
							if (tmp[j2][j] != 0) {
								go(j2, j, direction[i]);
							}
						}
					}
					break;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, tmp[i][j]);
				}
			}
			return;
		}
		for (int i = 0; i < dr.length; i++) {
			direction[depth] = i;
			dir(depth + 1);
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
		dir(0);
		System.out.println(max);
//		tmp = new int[N][N];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				tmp[i][j] = map[i][j];
//			}
//		}
//		sum = new boolean[N][N];
//		go(0, 2, 3);
//		go(1, 2, 3);
//		go(2, 2, 3);
//		go(0, 1, 3);
//		go(1, 1, 3);
//		go(2, 1, 3);
//		go(0, 0, 3);
//		go(1, 0, 3);
//		go(2, 0, 3);
	}

}