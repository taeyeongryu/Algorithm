import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int location;
		int count;

		@Override
		public String toString() {
			return "Point [location=" + location + ", count=" + count + "]";
		}

		public Point(int location, int count) {
			super();
			this.location = location;
			this.count = count;
		}
	}

	static int N, K, result;
	static boolean[] visited;
	static int[] trace;

	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		visited[N] = true;
		trace[N] = N;
		queue.add(new Point(N, 0));
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int cur_location = cur.location;
			int cur_count = cur.count;
			if (cur_location == K) {
				result = cur_count;
				return;
			}
			// +1만 해준다.
			if (cur_location == 0) {
				if (!visited[cur_location + 1]) {
					Point next = new Point(cur_location + 1, cur_count + 1);
					visited[cur_location + 1] = true;
					trace[cur_location + 1] = cur_location;
					queue.add(next);
				}
			}
			// -1만 해준다.
			else if (cur_location >= 100000) {
				if (!visited[cur_location - 1]) {
					Point next = new Point(cur_location - 1, cur_count + 1);
					visited[cur_location - 1] = true;
					trace[cur_location - 1] = cur_location;
					queue.add(next);
				}
			}
			// +1, -1, 곱하기2 해준다.
			else {
				if (!visited[cur_location * 2]) {
					Point next = new Point(cur_location * 2, cur_count + 1);
					visited[cur_location * 2] = true;
					trace[cur_location * 2] = cur_location;
					queue.add(next);
				}
				if (!visited[cur_location - 1]) {
					Point next = new Point(cur_location - 1, cur_count + 1);
					visited[cur_location - 1] = true;
					trace[cur_location - 1] = cur_location;
					queue.add(next);
				}
				if (!visited[cur_location + 1]) {
					Point next = new Point(cur_location + 1, cur_count + 1);
					visited[cur_location + 1] = true;
					trace[cur_location + 1] = cur_location;
					queue.add(next);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[200000 + 1];
		trace = new int[200000 + 1];
		Stack<Integer> stack = new Stack<>();
		bfs();
		int endpoint = K;
		stack.push(endpoint);
		while (endpoint != trace[endpoint]) {
			stack.push(trace[endpoint]);
			endpoint = trace[endpoint];
		}
		bw.write("" + result + "\n");
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			bw.write("" + stack.pop() + " ");
		}
		bw.close();
	}
}