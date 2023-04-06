import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Ball {
		int r;
		int c;

		@Override
		public String toString() {
			return "Ball [r=" + r + ", c=" + c + "]";
		}

		public Ball(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class Pair {
		Ball red;
		Ball blue;
		int count;

		@Override
		public String toString() {
			return "Pair [red=" + red + ", blue=" + blue + ", count=" + count + "]";
		}

		public Pair(Ball red, Ball blue, int count) {
			super();
			this.red = red;
			this.blue = blue;
			this.count = count;
		}
	}

	// 움직인 볼을 반환
	// 방향 0부터 3까지
	// 리턴 된 값이 블루와 레드가 같은지 나중에 생각해줘야 함.
	// 같고 둘다 홀이면 더이상 살피지 않는다.
	static Ball move(Ball ball, int dir) {
		int r = ball.r;
		int c = ball.c;
		while (map[r + dr[dir]][c + dc[dir]] != '#') {
			r += dr[dir];
			c += dc[dir];
			if (r == hr && c == hc) {
				break;
			}
		}
		return new Ball(r, c);
	}

	static int bfs() {
		int result = 0;
		Queue<Pair> q = new LinkedList<>();
		visited[rr][rc][br][bc] = true;
		q.add(new Pair(new Ball(rr, rc), new Ball(br, bc), 0));
		while (!q.isEmpty()) {
			Pair curpair = q.poll();

			if (curpair.count > 10) {
				return -1;
			}

			if (curpair.red.r == hr && curpair.red.c == hc) {
				return curpair.count;
			}

			Ball red = curpair.red;
			Ball blue = curpair.blue;
			int count = curpair.count;
			for (int i = 0; i < 4; i++) {
				Ball nextred = move(red, i);
				Ball nextblue = move(blue, i);
				// 블루가 홀에 빠졌으면 그냥 넘긴다.
				if (nextblue.r == hr && nextblue.c == hc)
					continue;
				// 같으면 조정해줘야되고
				if (nextblue.r == nextred.r && nextblue.c == nextred.c) {
					switch (i) {
					// 하
					case 0:
						if (red.r > blue.r) {
							nextblue.r--;
						} else {
							nextred.r--;
						}
						break;
					// 상
					case 1:
						if (red.r > blue.r) {
							nextred.r++;
						} else {
							nextblue.r++;
						}
						break;
					// 우
					case 2:
						if (red.c > blue.c) {
							nextblue.c--;
						} else {
							nextred.c--;
						}
						break;
					// 좌
					case 3:
						if (red.c > blue.c) {
							nextred.c++;
						} else {
							nextblue.c++;
						}
						break;
					}// switch
				}
				if (!visited[nextred.r][nextred.c][nextblue.r][nextblue.c]) {
					visited[nextred.r][nextred.c][nextblue.r][nextblue.c] = true;
					q.add(new Pair(nextred, nextblue, count + 1));
				}
			}
		}
		return -1;
	}

	// 하 상 우 좌
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] map;
	static int N, M, hr, hc, br, bc, rr, rc;
	static boolean[][][][] visited = new boolean[10][10][10][10];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					rr = i;
					rc = j;
				} else if (map[i][j] == 'B') {
					br = i;
					bc = j;
				} else if (map[i][j] == 'O') {
					hr = i;
					hc = j;
				}
			}
		}
		int num = bfs();
		System.out.println(num);
	}
}