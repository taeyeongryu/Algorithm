import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}

	static int R, C, s1_r, s1_c, s2_r, s2_c;
	static char[][] map;
	static boolean[][] visited, meltingvisited;

	static void meltingbfs() {
		// 물로 바꿔주고 주변에 아이스가 있는지 탐색한다.
		for (int i = 0; i < iceNode.size(); i++) {
			Node tmp = iceNode.get(i);
			map[tmp.r][tmp.c] = '.';
			iceq.add(tmp);
		}
		iceNode.clear();
		while (!iceq.isEmpty()) {
			Node cur = iceq.poll();
			int cur_r = cur.r;
			int cur_c = cur.c;
			for (int k = 0; k < 4; k++) {
				int nr = cur_r + dr[k];
				int nc = cur_c + dc[k];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
					continue;
				}
				// 방문하지 않았고
				if (!meltingvisited[nr][nc]) {
					// 한곳이라도 지나갈 곳이 있으면
					if (map[nr][nc] == 'X') {
						meltingvisited[nr][nc] = true;
						iceNode.add(new Node(nr, nc));
					}
				}
			}
		}
	}

	static boolean bfs() {
		boolean flag = false;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int cur_r = cur.r;
			int cur_c = cur.c;
			if (cur_r == s2_r && cur_c == s2_c) {
				flag = true;
				break;
			}
			boolean go = true;
			for (int k = 0; k < 4; k++) {
				int nr = cur_r + dr[k];
				int nc = cur_c + dc[k];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
					continue;
				}
				// 방문하지 않았고
				if (!visited[nr][nc]) {
					// 한곳이라도 지나갈 곳이 있으면
					if (map[nr][nc] != 'X') {
						q.add(new Node(nr, nc));
						visited[nr][nc] = true;
					} else if (map[nr][nc] == 'X') {
						go = false;
					}
				}
			}
			if (!go) {
				nextNode.add(cur);
			}
		}
		return flag;
	}


	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<Node> nextNode = new ArrayList<>();
	static List<Node> iceNode = new ArrayList<>();
	static Queue<Node> q = new LinkedList<>();
	static Queue<Node> iceq = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		meltingvisited = new boolean[R][C];
		int count = 0;
		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == 'L' && count == 0) {
					count++;
					s1_r = i;
					s1_c = j;
				}
				if (map[i][j] == 'L' && count == 1) {
					s2_r = i;
					s2_c = j;
				}
			}
		}
		// 먼저 첫번째로 녹일 빙판을 저장해 준다.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 빙하라면
				if (map[i][j] == 'X') {
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
							continue;
						}
						if (map[nr][nc] == '.' || map[nr][nc] == 'L') {
							meltingvisited[i][j] = true;
							iceNode.add(new Node(i, j));
							break;
						}
					}
				} else {
					meltingvisited[i][j] = true;
				}
			}
		}
		count = 0;
		q.add(new Node(s1_r, s1_c));
		visited[s1_r][s1_c] = true;
		while (!bfs()) {
			count++;
			for (int i = 0; i < nextNode.size(); i++) {
				q.add(nextNode.get(i));
			}
			nextNode.clear();
			meltingbfs();
		}
		System.out.println(count);
	}

}