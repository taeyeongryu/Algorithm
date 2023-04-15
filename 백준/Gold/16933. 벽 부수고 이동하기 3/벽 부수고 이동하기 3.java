import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static char[][] map;
	static int[][][][] visited;
	static int N, M, K, result;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	static void bfs() {
		Queue<int[]> pq = new LinkedList<>();
//		pq.add(new Point(0, 0, 1, 0, true));
		pq.add(new int[] { 0, 0, 1, 0, 1 });
		while (!pq.isEmpty()) {
//			System.out.println(pq);
			int[] cur = pq.poll();
			int cur_r = cur[0];
			int cur_c = cur[1];
			int cur_count = cur[2];
			int cur_wall = cur[3];
			int cur_day = cur[4];
			if (cur_r == N - 1 && cur_c == M - 1) {
				result = cur_count;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (map[nr][nc] == '0') {
					if (visited[1 - cur_day][cur_wall][nr][nc] > 0) {
						continue;
					}
					visited[1 - cur_day][cur_wall][nr][nc] = cur_count + 1;
					pq.add(new int[] { nr, nc, cur_count + 1, cur_wall, 1 - cur_day });
				} else {
					if (cur_wall == K) {
						continue;
					}
					// 밤이면
					if (cur_day == 0) {
						if (visited[1][cur_wall][cur_r][cur_c] > 0) {
							continue;
						}
						visited[1][cur_wall][cur_r][cur_c] = cur_count + 1;
						pq.add(new int[] { cur_r, cur_c, cur_count + 1, cur_wall, 1 });
					}
					// 낮이면
					else {
						if (visited[0][cur_wall + 1][nr][nc] > 0) {
							continue;
						}
						visited[0][cur_wall+1][nr][nc] = cur_count + 1;
						pq.add(new int[] { nr, nc, cur_count + 1, cur_wall + 1, 0 });
					}
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new int[2][K + 1][N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		bfs();
		System.out.println(result==0?-1:result);
	}

}