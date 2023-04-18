import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int size;

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", size=" + size + "]";
		}

		public Node(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}

		@Override
		public int compareTo(Node o) {
			if (this.r < o.r) {
				return -1;
			} else if (this.r > o.r) {
				return 1;
			} else {
				if (this.c < o.c) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}

	static void bfs(int r, int c) {
		Node start = new Node(r, c, 0);
		visited[r][c] = true;
		q.add(start);
		while (!pq.isEmpty() || !q.isEmpty()) {
			// 근거리에 먹잇감이 있을 때
			if (!pq.isEmpty()) {
//				System.out.println("pq : " + pq);
				Node cur = pq.poll();
				int cur_r = cur.r;
				int cur_c = cur.c;
				pq.clear();
				q.clear();
				lasttime = time;
				count++;
				time++;
				if (shark == count) {
					shark++;
					count=0;
				}
//				System.out.println("time : " + time + ", count : " + count + ", shark : " + shark);
				map[cur_r][cur_c] = 0;
				visited = new boolean[N][N];
				for (int i = 0; i < dc.length; i++) {

				}
				for (int i = 0; i < 4; i++) {
					int nr = cur_r + dr[i];
					int nc = cur_c + dc[i];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
						continue;
					}
					if (visited[nr][nc]) {
						continue;
					}
					// 못간다.
					if (map[nr][nc] > shark) {
						continue;
					}
					// 가긴간다.
					else if (map[nr][nc] == shark) {
						visited[nr][nc] = true;
						q.add(new Node(nr, nc, 0));
					}
					// 먹는다 먹는것은 pq에서 처리
					else {
						if (map[nr][nc] == 0) {
							visited[nr][nc] = true;
							q.add(new Node(nr, nc, map[nr][nc]));
						} else {
							visited[nr][nc] = true;
							pq.add(new Node(nr, nc, map[nr][nc]));
						}
					}
				}

			}
			// 없을때
			else {
//				System.out.println("q : " + q);
				int qsize = q.size();
				time++;
				for (int qs = 0; qs < qsize; qs++) {
					Node cur = q.poll();
					int cur_r = cur.r;
					int cur_c = cur.c;
					for (int i = 0; i < 4; i++) {
						int nr = cur_r + dr[i];
						int nc = cur_c + dc[i];
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
							continue;
						}
						if (visited[nr][nc]) {
							continue;
						}
						// 못간다.
						if (map[nr][nc] > shark) {
							continue;
						}
						// 가긴간다.
						else if (map[nr][nc] == shark) {
							visited[nr][nc] = true;
							q.add(new Node(nr, nc, 0));
						}
						// 먹는다 먹는것은 pq에서 처리
						else {
							if (map[nr][nc] == 0) {
								visited[nr][nc] = true;
								q.add(new Node(nr, nc, map[nr][nc]));
							} else {
								visited[nr][nc] = true;
								pq.add(new Node(nr, nc, map[nr][nc]));
							}
						}
					}
				}
			}
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// 라스트 타임은 상어를 먹을때마다 업데이트 한다.
	static int N, count, lasttime, time, shark;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Node> q = new LinkedList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		shark = 2;
		StringTokenizer st;
		int start = 0;
		int end = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					map[i][j] = 0;
					start = i;
					end = j;
				}
			}
		}
		bfs(start, end);
		System.out.println(lasttime);
	}

}