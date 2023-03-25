import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static List<Node> list = new ArrayList<>();
	static Node[] arr;
	static int[][] map;
	static int N;
	static int M;
	static int max = Integer.MIN_VALUE;

	static void combination( int at, int count) {
		if (count == 3) {
			for (int i = 0; i < arr.length; i++) {
				Node tmp = arr[i];
				map[tmp.r][tmp.c] = 1;
			}
			// map을 수정했으니 bfs로 탐색해서 바이러스가 퍼지는지 확인한다.
			int result = BFS();
			if (result > max) {
				max = result;
			}

			// 2중for문을 돌면서 0의 갯수를 샌다.
			// map을 원래대로 돌린다.
			for (int i = 0; i < arr.length; i++) {
				Node tmp = arr[i];
				map[tmp.r][tmp.c] = 0;
			}
			return;
		}
		
		for (int i = at; i < list.size(); i++) {
			arr[count] = list.get(i);
			combination(i + 1, count + 1);
		}

	}

	// 남의 0의 갯수를 반환한다.
	static int BFS() {
		Queue<Node> queue = new LinkedList<>();
		int[][] new_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_map[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (new_map[i][j] == 2) {
					queue.add(new Node(i, j));
				}
			}
		}
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = tmp.r + dr[i];
				int nc = tmp.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (new_map[nr][nc] == 0) {
					new_map[nr][nc] = 2;
					queue.add(new Node(nr, nc));
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < new_map.length; i++) {
			for (int j = 0; j < new_map[0].length; j++) {
				if (new_map[i][j] == 0) {
					answer++;
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Node[3];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					list.add(new Node(i, j));
				}
			}
		}
		combination( 0, 0);
		System.out.println(max);
	}
}