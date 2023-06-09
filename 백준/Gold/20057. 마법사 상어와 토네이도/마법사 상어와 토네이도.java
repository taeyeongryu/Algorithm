import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int t_r;
	static int t_c;
	static int t_dir;

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int[][] map;
	static int N, remainSend;

	static void moveSend() {
		// 퍼트려야하는 모래의 위치
		// 유효성 검사 안함 왜? 딱 정해진 만큼만 할거라서
		int nr = t_r + dr[t_dir];
		int nc = t_c + dc[t_dir];
		int oneper = (int) (map[nr][nc] * 0.01);
		int twoper = (int) (map[nr][nc] * 0.02);
		int fiveper = (int) (map[nr][nc] * 0.05);
		int sevenper = (int) (map[nr][nc] * 0.07);
		int tenper = (int) (map[nr][nc] * 0.1);

		// 1% 지금 위치에서 좌우
		int nr11 = t_r + dr[(t_dir + 1) % 4];
		int nc11 = t_c + dc[(t_dir + 1) % 4];
		if (nr11 < 0 || nr11 >= N || nc11 < 0 || nc11 >= N) {
			remainSend += oneper;
			map[nr][nc] -= oneper;
		} else {
			map[nr11][nc11] += oneper;
			map[nr][nc] -= oneper;
		}

		int nr12 = t_r + dr[(t_dir + 3) % 4];
		int nc12 = t_c + dc[(t_dir + 3) % 4];
		if (nr12 < 0 || nr12 >= N || nc12 < 0 || nc12 >= N) {
			remainSend += oneper;
			map[nr][nc] -= oneper;
		} else {
			map[nr12][nc12] += oneper;
			map[nr][nc] -= oneper;
		}
		// 7%
		int nr71 = nr + dr[(t_dir + 1) % 4];
		int nc71 = nc + dc[(t_dir + 1) % 4];
		if (nr71 < 0 || nr71 >= N || nc71 < 0 || nc71 >= N) {
			remainSend += sevenper;
			map[nr][nc] -= sevenper;
		} else {
			map[nr71][nc71] += sevenper;
			map[nr][nc] -= sevenper;
		}
		int nr73 = nr + dr[(t_dir + 3) % 4];
		int nc73 = nc + dc[(t_dir + 3) % 4];
		if (nr73 < 0 || nr73 >= N || nc73 < 0 || nc73 >= N) {
			remainSend += sevenper;
			map[nr][nc] -= sevenper;
		} else {
			map[nr73][nc73] += sevenper;
			map[nr][nc] -= sevenper;
		}

		// 2%
		int nr72 = nr + dr[(t_dir + 1) % 4] * 2;
		int nc72 = nc + dc[(t_dir + 1) % 4] * 2;
		if (nr72 < 0 || nr72 >= N || nc72 < 0 || nc72 >= N) {
			remainSend += twoper;
			map[nr][nc] -= twoper;
		} else {
			map[nr72][nc72] += twoper;
			map[nr][nc] -= twoper;
		}
		int nr74 = nr + dr[(t_dir + 3) % 4] * 2;
		int nc74 = nc + dc[(t_dir + 3) % 4] * 2;
		if (nr74 < 0 || nr74 >= N || nc74 < 0 || nc74 >= N) {
			remainSend += twoper;
			map[nr][nc] -= twoper;
		} else {
			map[nr74][nc74] += twoper;
			map[nr][nc] -= twoper;
		}

		// 10%
		int nr101 = t_r + dr[t_dir] * 2 + dr[(t_dir + 1) % 4];
		int nc101 = t_c + dc[t_dir] * 2 + dc[(t_dir + 1) % 4];
		if (nr101 < 0 || nr101 >= N || nc101 < 0 || nc101 >= N) {
			remainSend += tenper;
			map[nr][nc] -= tenper;
		} else {
			map[nr101][nc101] += tenper;
			map[nr][nc] -= tenper;
		}
		int nr102 = t_r + dr[t_dir] * 2 + dr[(t_dir + 3) % 4];
		int nc102 = t_c + dc[t_dir] * 2 + dc[(t_dir + 3) % 4];
		if (nr102 < 0 || nr102 >= N || nc102 < 0 || nc102 >= N) {
			remainSend += tenper;
			map[nr][nc] -= tenper;
		} else {
			map[nr102][nc102] += tenper;
			map[nr][nc] -= tenper;
		}

		// 5%
		int nr5 = t_r + dr[t_dir] * 3;
		int nc5 = t_c + dc[t_dir] * 3;
		if (nr5 < 0 || nr5 >= N || nc5 < 0 || nc5 >= N) {
			remainSend += fiveper;
			map[nr][nc] -= fiveper;
		} else {
			map[nr5][nc5] += fiveper;
			map[nr][nc] -= fiveper;
		}

		// 알파
		int nra = t_r + dr[t_dir] * 2;
		int nca = t_c + dc[t_dir] * 2;
		if (nra < 0 || nra >= N || nca < 0 || nca >= N) {
			remainSend += map[nr][nc];
			map[nr][nc] = 0;
		} else {
			map[nra][nca] += map[nr][nc];
			map[nr][nc] = 0;
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		t_dir = 0;
		t_r = N / 2;
		t_c = N / 2;
		
		//i번 전진해야 하는것임
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < 2; j++) {
				for (int j2 = 0; j2 < i; j2++) {
					moveSend();
					t_r=t_r+dr[t_dir];
					t_c=t_c+dc[t_dir];
				}
				//방향전환
				t_dir = (t_dir+1)%4;
			}

		}
		for (int j = 0; j < 3; j++) {
			for (int j2 = 0; j2 < N-1; j2++) {
				moveSend();
				t_r=t_r+dr[t_dir];
				t_c=t_c+dc[t_dir];
			}
			//방향전환
			t_dir = (t_dir+1)%4;
		}
		System.out.println(remainSend);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		moveSend();
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

}