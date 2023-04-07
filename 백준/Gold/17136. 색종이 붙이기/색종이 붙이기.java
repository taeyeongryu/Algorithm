import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	// 하 상 우 좌
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[] remain = { 0, 5, 5, 5, 5, 5 };
	static int[][] map = new int[10][10];
	static boolean[][] visited = new boolean[10][10];
	static int count = 0;
	static int min = Integer.MAX_VALUE;

	// r,c부터 시작해서 오른쪽 아래로 size만큼의 색종이를 붙일수 있는지 확인 하는 메서드
	static boolean check(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nr = r + i * dr[0] + j * dr[2];
				int nc = c + i * dc[0] + j * dc[2];
				if (nr < 0 || nc < 0 || nr >= 10 || nc >= 10) {
					return false;
				}
				if (map[nr][nc] == 0) {
					return false;
				}
				if (visited[nr][nc] == true) {
					return false;
				}
			}
		}
		return true;
	}

	static void put(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nr = r + i * dr[0] + j * dr[2];
				int nc = c + i * dc[0] + j * dc[2];
				visited[nr][nc] = true;
			}
		}
	}

	static void off(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nr = r + i * dr[0] + j * dr[2];
				int nc = c + i * dc[0] + j * dc[2];
				visited[nr][nc] = false;
			}
		}
	}

	// 스티커 붙이고 다음 붙일 자리 찾아서 붙이는 메서드
	// remain검사도 이미 했다.
	static void back(int r, int c, int size) {
		// 여기 들어왔다는 것은 1이 있다는 의미이다.

		if (count >= min) {
			return;
		}
		put(r, c, size);
		remain[size]--;
		count++;
		// 붙일 자리 있니?
		boolean flag1 = false;
		// 뭐라도 붙였나?
		boolean flag2 = false;
		outer: for (int i = r; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					// 붙여야 될 자리 찾음
					flag1 = true;
					for (int j2 = 1; j2 <= 5; j2++) {
						if (check(i, j, j2) && remain[j2] > 0) {
							back(i, j, j2);
							// 뭐라도 붙였다는 의미
							flag2 = true;
						}
					}
					break outer;
				}
			}
		}
		if (!flag1) {
			min = Math.min(count, min);
		}
		count--;
		remain[size]++;
		off(r, c, size);
		if (flag1) {
			if (!flag2) {
				// 붙일자리 있는데 못붙인경우
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		int start = -1;
		int end = -1;
		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (count == 0 && map[i][j] > 0) {
					start = i;
					end = j;
					count++;
				}
			}
		}
		for (int i = 1; i <= 5; i++) {
			if (check(start, end, i) && remain[i] > 0) {
				back(start, end, i);
			}
		}
		if (start == -1 && end == -1) {
			System.out.println(0);
		} else {
			System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		}
	}

}