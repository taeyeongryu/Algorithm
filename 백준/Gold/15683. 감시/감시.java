import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Camera {
		int r;
		int c;
		int num;

		Camera(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

	}

	static List<Camera> list = new ArrayList<>();
	// 남 동 북 서 순으로 쓰자
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N;
	static int M;
	static int camera_num = 0;

	static int[][] board1 = new int[8][8];
	static int[][] board2 = new int[8][8];
	static int min = Integer.MAX_VALUE;

	// oob란 out of bound check 해주는 메서드
	static boolean OOB(int i, int j) {
		return 0 > i || N <= i || 0 > j || M <= j;
	}

	// 이것이 핵심
	static void upd(int r, int c, int dir) {
		dir %= 4;
		while (true) {
			r += dr[dir];
			c += dc[dir];
			if (OOB(r, c) || board2[r][c] == 6)
				return;
			if (board2[r][c] != 0)
				continue;
			board2[r][c] = 7;
		}
	}

	static void find() {
		int all_kind = 1;
		for (int i = 0; i < camera_num; i++) {
			all_kind *= 4;
		}
		int[] dir = new int[camera_num];
		// 총 모든 종류에 대해서 반복
		for (int i = 1; i <= all_kind; i++) {
			board2 = new int[8][8];
			for (int j = 0; j < 8; j++) {
				for (int j2 = 0; j2 < 8; j2++) {
					board2[j][j2] = board1[j][j2];
				}
			}
			int tmp = i;
			// 카메라의 갯수만큼 4진수로 바꿔준다.
			for (int j = 0; j < camera_num; j++) {
				dir[j] = tmp % 4;
				tmp /= 4;
			}
			// 방향은 모두 정해졌다.
			// 이제 리스트에 정해진 것들 하나씩 꺼내면서 방향 하면 됨
			for (int j = 0; j < list.size(); j++) {
				Camera cam = list.get(j);
				int r = cam.r;
				int c = cam.c;
				int num = cam.num;
				if (num == 1) {
					upd(r, c, dir[j]);
				} else if (num == 2) {
					upd(r, c, dir[j]);
					upd(r, c, dir[j] + 2);
				} else if (num == 3) {
					upd(r, c, dir[j]);
					upd(r, c, dir[j] + 1);
				} else if (num == 4) {
					upd(r, c, dir[j]);
					upd(r, c, dir[j] + 1);
					upd(r, c, dir[j] + 2);
				} else {
					upd(r, c, dir[j]);
					upd(r, c, dir[j] + 1);
					upd(r, c, dir[j] + 2);
					upd(r, c, dir[j] + 3);
				}
			}
//			System.out.println(Arrays.deepToString(board2));
			int total = 0;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if (board2[j][j2] == 0)
						total++;
				}
			}
			min = Math.min(min, total);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				board1[i][j] = tmp;
				if (1 <= tmp && tmp <= 5) {
					list.add(new Camera(i, j, tmp));
					camera_num++;
				}
			}
		}
		find();
		System.out.println(min);
	}
}