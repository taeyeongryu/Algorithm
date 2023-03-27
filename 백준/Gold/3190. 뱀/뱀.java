import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}

	// 사과는 1 뱀은 2로 두자
	// 상 우 하 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int snake_dir = 1;
	static List<Node> snake = new ArrayList<>();
	static int[][] map;
	static int N;
	static int K;

	// 왼쪽은 -1 오른쪽은 +1으로 한다.
	static int go() {
		int result = -1;

		int head_r = snake.get(0).r;
		int head_c = snake.get(0).c;
//		System.out.println("head_r : " + head_r + ", head_c : " + head_c);
//		System.out.println(snake_dir);
		// 만약 방향전환을 해야된다면 방향전환을하고
		// 앞으로 간다.
		int nr = head_r + dr[snake_dir];
		int nc = head_c + dc[snake_dir];
//		System.out.println("nr : " + nr + ", nc : " + nc);
		// 맵 밖으로 나간다면
		if (nr == 0 || nc == 0 || nr == N + 1 || nc == N + 1) {
//			System.out.println(1);
			return result;
		}
		// 맵 안이라면
		else {
			// 사과이거나
			if (map[nr][nc] == 1) {
				// 맵 바꿔주고 리스트 업데이트 앞쪽으로
				map[nr][nc] = 2;
				snake.add(0, new Node(nr, nc));
//				System.out.println(2);
			}
			// 빈칸이거나
			else if (map[nr][nc] == 0) {
				// 꼬리 잘라주고 앞으로 하나 늘려준다.
				map[nr][nc] = 2;
				snake.add(0, new Node(nr, nc));
				// 꼬리 노드
				Node tmp = snake.remove(snake.size() - 1);
				map[tmp.r][tmp.c] = 0;
//				System.out.println(3);
			}
			// 꼬리이거나
			else {
//				System.out.println(4);
				return result;
			}
		}
		return 1;
	}

	static void rotate(int dir) {
		if (dir != 0) {
			if (dir == -1) {
				snake_dir += 3;
				snake_dir %= 4;
			} else {
				snake_dir += 1;
				snake_dir %= 4;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 2][N + 2];
		map[1][1] = 2;
		snake.add(new Node(1, 1));
		for (int i = 0; i < K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			map[r][c] = 1;
		}
		int L = sc.nextInt();
		int[][] Larr = new int[L][2];
		for (int i = 0; i < Larr.length; i++) {
			int tmp1 = sc.nextInt();
			int tmp2 = (sc.next().charAt(0)) == 'D' ? 1 : -1;
			Larr[i][0] = tmp1;
			Larr[i][1] = tmp2;
		}
		int idx = 0;
		int time = 0;
//		System.out.println(Arrays.deepToString(Larr));
//		go();
		while (true) {
			time++;
			if (go() == -1)
				break;
			if (idx < L) {
				if (Larr[idx][0] == time) {
					rotate(Larr[idx++][1]);
				}
			}
//			System.out.println("time : "+time);
//			System.out.println(Arrays.deepToString(map));
//			System.out.println(snake);
		}
		System.out.println(time);
	}

}