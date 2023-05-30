import java.util.*;

public class Main {
	static class Node {
		int r;
		int c;
		int num;
		boolean bbreak;

		public Node(int r, int c, int num, boolean bbreak) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.bbreak = bbreak;
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;
	static int result = Integer.MAX_VALUE;
	static Queue<Node> queue = new LinkedList<>();

	static void BFS() {
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();

			if (tmp.r == N - 1 && tmp.c == M - 1) {
				result = tmp.num;
				return;
			}
			for (int dir = 0; dir < dc.length; dir++) {
				int nr = tmp.r + dr[dir];
				int nc = tmp.c + dc[dir];
				if (0 <= nr && nr < N && 0 <= nc && nc < M) {
					// 내가 벽을 부순적이 있을 때
					if (tmp.bbreak) {
						// 다음노드 빈칸일 때 만 갈수 있다. 방문체크 하고 방문하지 않았다면 방문
						if (map[nr][nc] == 0 && !visited[1][nr][nc]) {
							visited[1][nr][nc] = true;
							queue.offer(new Node(nr, nc, tmp.num + 1, tmp.bbreak));
						}

					}
					// 내가 벽을 부순적이 없을 때
					else {

						// 만약 빈공간이면 방문체크하고 방문
						if (map[nr][nc] == 0 && !visited[0][nr][nc]) {
							visited[0][nr][nc] = true;
							queue.offer(new Node(nr, nc, tmp.num + 1, tmp.bbreak));
						}
						// 만약 벽이면 방문 체크하고 방문
						else if (map[nr][nc] == 1) {
							visited[0][nr][nc] = true;
							queue.offer(new Node(nr, nc, tmp.num + 1, true));
						}
					}

				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		// 벽 부쉈는지, N,M
		visited = new boolean[2][N][M];
		for (int i = 0; i < N; i++) {
			String tmp = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
		queue.offer(new Node(0, 0, 1, false));
		visited[0][0][0] = true;
		visited[1][0][0] = true;
		BFS();

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
}