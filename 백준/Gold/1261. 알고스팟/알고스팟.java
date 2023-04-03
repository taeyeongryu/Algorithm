import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int count;

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.count, o.count);
		}

		

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", count=" + count + "]";
		}



		public Point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] map;
	static boolean[][] visited;
	static int N;
	static int M;


	static int bfsPriorityQueue(int r, int c) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int result = 0;
		pq.offer(new Point(r, c, 0));
		visited[r][c] = true;
		while (!pq.isEmpty()) {
			Point cur_p = pq.poll();
			int cur_r = cur_p.r;
			int cur_c = cur_p.c;
			int cur_count = cur_p.count;
			if (cur_r == N - 1 && cur_c == M - 1) {
				result = cur_count;
			}
			for (int i = 0; i < dc.length; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (visited[nr][nc])
					continue;
				if (map[nr][nc] == '1') {
					visited[nr][nc] = true;
					pq.offer(new Point(nr, nc, cur_count + 1));
				} else {
					visited[nr][nc] = true;
					pq.offer(new Point(nr, nc, cur_count));
				}
			}

		}
		return result;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(""+bfsPriorityQueue(0, 0));
		bw.close();
		br.close();
	}

}