import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int location;
		int count;

		public Point(int location, int count) {
			super();
			this.location = location;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Point [location=" + location + ", count=" + count + "]";
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.count, o.count);
		}
	}

	static int N, K, result;
	static boolean[] visited;

	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		visited[N] = true;
		visited[2 * N] = true;
		queue.add(new Point(N, 0));
		queue.add(new Point(2 * N, 0));
		while (!queue.isEmpty()) {
//			System.out.println(queue);
			Point cur = queue.poll();
			int cur_location = cur.location;
			int cur_count = cur.count;
			if (cur_location == K) {
				result = cur.count;
				return;
			}
			// +1만 해준다.
			if (cur_location == 0) {
				if (!visited[cur_location + 1]) {
					visited[cur_location + 1] = true;
					queue.add(new Point(cur_location + 1, cur_count + 1));
				}
			}
			// -1만 해준다.
			else if (cur_location >= 100000) {
				if (!visited[cur_location - 1]) {
					visited[cur_location - 1] = true;
					queue.add(new Point(cur_location - 1, cur_count + 1));
				}
			}
			// +1, -1, 곱하기2 해준다.
			else {
				if (!visited[cur_location * 2]) {
					visited[cur_location * 2] = true;
					queue.add(new Point(cur_location * 2, cur_count));
				}
				if (!visited[cur_location - 1]) {
					visited[cur_location - 1] = true;
					queue.add(new Point(cur_location - 1, cur_count + 1));
				}
				if (!visited[cur_location + 1]) {
					visited[cur_location + 1] = true;
					queue.add(new Point(cur_location + 1, cur_count + 1));
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[200000 + 1];
		bfs();
		System.out.println(result);
	}

}