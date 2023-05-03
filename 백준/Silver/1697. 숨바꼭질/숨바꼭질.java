import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Location {
		int idx;
		int time;

		public Location(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}

	static int N, K, result;
	static boolean[] visited = new boolean[200000];

	static void bfs() {
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(N, 0));

		// 큐에서 폴한다음에 동생을 만났는지 체크한다.
		while (!q.isEmpty()) {
			Location cur = q.poll();
			int idx = cur.idx;
			int time = cur.time;
			if (idx == K) {
				result = time;
				return;
			}
			if (0 <= idx + 1 && idx + 1 < visited.length) {
				if (!visited[idx + 1]) {
					visited[idx + 1] = true;
					q.add(new Location(idx + 1, time + 1));
				}
			}
			if (0 <= idx - 1 && idx - 1 < visited.length) {
				if (!visited[idx - 1]) {
					visited[idx - 1] = true;
					q.add(new Location(idx - 1, time + 1));
				}
			}
			if (0 <= idx * 2 && idx * 2 < visited.length) {
				if (!visited[idx * 2]) {
					visited[idx * 2] = true;
					q.add(new Location(idx * 2, time + 1));
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs();
		System.out.println(result);
	}
}