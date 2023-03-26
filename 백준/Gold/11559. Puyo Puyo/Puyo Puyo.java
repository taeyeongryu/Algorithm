import java.util.*;

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
	static int N = 12;
	static int M = 6;

	static boolean[][] visited;
	static char[][] map;

	static List<Node> list = new LinkedList<>();
	static int count = 1;

	// 시작 노드
	// 터트릴 라인의 시작노드를 찾는 메서드
	static void findBFS(Node node) {
		count = 1;
		Queue<Node> queue = new LinkedList<>();
		visited[node.r][node.c] = true;
		queue.offer((node));
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = tmp.r + dr[i];
				int nc = tmp.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6)
					continue;
				if (map[nr][nc] == map[tmp.r][tmp.c] && !visited[nr][nc]) {
					visited[nr][nc] = true;
					count++;
					queue.offer(new Node(nr, nc));
					if (count >= 4) {
						list.add(node);
						return;
					}
				}
			}

		}
	}

	static void changeBFS() {
		int listsize = list.size();
		Queue<Node> queue = new LinkedList<>();
		for (int i = 0; i < listsize; i++) {
			Node node = list.remove(0);
			char ch = map[node.r][node.c];
			// 여기서 부터 bfs시젼
			// 방문체크 필요 없다 왜? '.'으로 바꿔주면 되기 때문
			map[node.r][node.c] = '.';
			queue.offer(node);
			while (!queue.isEmpty()) {
				Node tmp = queue.poll();
				for (int j = 0; j < dc.length; j++) {
					int nr = tmp.r + dr[j];
					int nc = tmp.c + dc[j];
					if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6)
						continue;
					if (map[nr][nc] == ch) {
						map[nr][nc] = '.';
						queue.offer(new Node(nr, nc));
					}
				}
			}
		}
	}

	static void down() {
		for (int j = 0; j < M; j++) {
			for (int i = N - 2; i >= 0; i--) {
				// 점이 아닌 것을 만났다 이제 밑으로 계속 내려줄거다.

				if (map[i][j] != '.') {
					boolean flag = false;
					int nr = i;
					while (true) {
						nr += 1;
						if (map[nr][j] == '.')
							flag = true;
						if (nr < 11) {
							if (map[nr][j] == '.') {
								continue;
							} else {
								// swap
								if (flag) {
									char ch = map[i][j];
									map[i][j] = map[nr-1][j];
									map[nr-1][j] = ch;
								}
								break;
							}
						} else {
							if (map[11][j] == '.') {
								if (flag) {
									char ch = map[i][j];
									map[i][j] = map[11][j];
									map[11][j] = ch;
								}
							} else {
								// swap
								if (flag) {
									char ch = map[i][j];
									map[i][j] = map[10][j];
									map[10][j] = ch;
								}
							}
							break;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i] = str.toCharArray();
			}
		}
		int result=0;
		while(true) {
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != '.') {
						if (!visited[i][j]) {
							visited[i][j] = true;
							findBFS(new Node(i, j));
						}
					}
				}
			}
			if (list.size()>0) {
				result++;
				changeBFS();
				down();
//				System.out.println(Arrays.deepToString(map));
//				System.out.println(Arrays.deepToString(visited));
//				System.out.println(list);
			}
			else break;
		}
		System.out.println(result);
	}

}