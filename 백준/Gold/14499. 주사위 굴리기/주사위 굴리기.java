import java.util.*;

public class Main {
	static int[] dice = new int[6];
	static int[][] map;
	static int N;
	static int M;
	static int dice_r;
	static int dice_c;
	static int K;
	// 1부터 동 서 북 남 순서
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };

	// 동 서 북 남 순서
	static void roll(int direction) {
		switch (direction) {
		case 1:
			roll_right();
			break;
		case 2:
			roll_right();
			roll_right();
			roll_right();
			break;
		case 3:
			roll_up();
			break;
		case 4:
			roll_up();
			roll_up();
			roll_up();
			break;
		}

	}

	static void roll_right() {
		int idx0 = 0;
		int idx1 = 0;
		int idx2 = 0;
		int idx3 = 0;
		int idx4 = 0;
		int idx5 = 0;
		idx0 = dice[4];
		idx1 = dice[5];
		idx2 = dice[2];
		idx3 = dice[3];
		idx4 = dice[1];
		idx5 = dice[0];
		dice[0] = idx0;
		dice[1] = idx1;
		dice[2] = idx2;
		dice[3] = idx3;
		dice[4] = idx4;
		dice[5] = idx5;

	}

	static void roll_up() {
		int idx0 = 0;
		int idx1 = 0;
		int idx2 = 0;
		int idx3 = 0;
		int idx4 = 0;
		int idx5 = 0;
		idx0 = dice[3];
		idx1 = dice[2];
		idx2 = dice[0];
		idx3 = dice[1];
		idx4 = dice[4];
		idx5 = dice[5];
		dice[0] = idx0;
		dice[1] = idx1;
		dice[2] = idx2;
		dice[3] = idx3;
		dice[4] = idx4;
		dice[5] = idx5;
	}

	static int cal(int direction) {
		int result = 0;
		int nr = dice_r + dr[direction];
		int nc = dice_c + dc[direction];
		if (nr < 0 || nc < 0 || nr >= N || nc >= M)
			return -1;
		else {
			// 굴린다.
			roll(direction);
			// 주사위 위치 고친다.
			dice_r = nr;
			dice_c = nc;
			if (map[nr][nc] == 0) {
				map[nr][nc] = dice[1];
			} else {
				dice[1] = map[nr][nc];
				map[nr][nc] = 0;
			}
			result = dice[0];
			return result;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		dice_r = sc.nextInt();
		dice_c = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < K; i++) {
			int tmp = cal(sc.nextInt());
			if (tmp != -1) {
				System.out.println(tmp);
			}
		}
	}
}