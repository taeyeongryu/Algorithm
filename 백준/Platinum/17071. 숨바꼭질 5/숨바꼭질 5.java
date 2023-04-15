import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point {
		int location;
		int time;

		public Point(int location, int time) {
			super();
			this.location = location;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Point [location=" + location + ", time=" + time + "]";
		}

	}

	static int N, K, result;
	static boolean[][] visited;

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		visited[0][N] = true;
		q.add(new Point(N, 0));
		int time = 0;
		while (!q.isEmpty()) {
//			System.out.println(q);
			if (K > 500000)
				return;
			int nowtime = time % 2;
			if (visited[nowtime][K]) {
				result = time;
				return;
			}
			for (int i = 0, size = q.size(); i < size; i++) {
				Point cur = q.poll();
				int cur_loc = cur.location;
				int cur_time = cur.time;
				if (cur_loc + 1 <= 500000) {
					if (!visited[(cur_time + 1) % 2][cur_loc + 1]) {
						visited[(cur_time + 1) % 2][cur_loc + 1] = true;
						q.add(new Point(cur_loc + 1, cur_time + 1));
					}
				}
				if (cur_loc - 1 >= 0) {
					if (!visited[(cur_time + 1) % 2][cur_loc - 1]) {
						visited[(cur_time + 1) % 2][cur_loc - 1] = true;
						q.add(new Point(cur_loc - 1, cur_time + 1));
					}
				}
				if (cur_loc * 2 <= 500000) {
					if (!visited[(cur_time + 1) % 2][cur_loc * 2]) {
						visited[(cur_time + 1) % 2][cur_loc * 2] = true;
						q.add(new Point(cur_loc * 2, cur_time + 1));
					}
				}
			}
			time++;
			K+=time;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[2][500000 + 1];
		if (N == K) {
			System.out.println(0);
		} else {
			bfs();
			System.out.println(result == 0 ? -1 : result);
		}
	}

}