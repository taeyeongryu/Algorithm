import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 섬 역할
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

	// 다리 역할
	static class edge implements Comparable<edge>{
		int idx;
		int weight;

		@Override
		public String toString() {
			return "edge [idx=" + idx + ", weight=" + weight + "]";
		}

		public edge(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

	}

	// MST문제다
	// 프림하고 크루스칼 둘다로 풀어보자
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<edge>[] bridgelist;

	static void number(int r, int c, int num) {
		Queue<Node> q = new LinkedList<>();
		Node start = new Node(r, c);
		q.add(start);
		visited[r][c] = true;
		map[r][c] = num;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int cur_r = cur.r;
			int cur_c = cur.c;
			for (int i = 0; i < dr.length; i++) {
				int nr = cur_r + dr[i];
				int nc = cur_c + dc[i];
				if (nr < 0 || nc < 0 | nr >= N || nc >= M) {
					continue;
				}
				if (map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					map[nr][nc] = num;
					q.add(new Node(nr, nc));
				}
			}
		}
	}

	// 다리를 연결할 수 있는지 확인하고 있다면
	static void bridge(int r, int c, int dir) {
		int count = 0;
		int row = r;
		int col = c;
		while (true) {
			row += dr[dir];
			col += dc[dir];
			count++;
			if (row < 0 || col < 0 | row >= N || col >= M) {
				break;
			}
			if (map[row][col] == 0) {
				continue;
			}
			if (map[row][col] != 0) {
				if (map[r][c] != map[row][col]) {
					if (count > 2) {
						// 인접리스트 만든다.
						bridgelist[map[r][c]].add(new edge(map[row][col], count-1));
					}
				}
				break;
			}
		}
	}
	static int prim() {
		int count=0;
		int weightsum=0;
		boolean choice[] = new boolean[bridgelist.length];
		PriorityQueue<edge> pq = new PriorityQueue<>();
		pq.add(new edge(1, 0));
		while (!pq.isEmpty()) {
//			System.out.println(pq);
//			System.out.println(count);
			edge cur = pq.poll();
			int cur_idx = cur.idx;
			int cur_weight = cur.weight;
			
			if(choice[cur_idx]) {
				continue;
			}
			choice[cur_idx]=true;
			count++;
			weightsum+=cur_weight;
//			System.out.println(count);
//			System.out.println(weightsum);
			if (count==bridgelist.length-1) {
				return weightsum;
			}
			for (int i = 0; i < bridgelist[cur_idx].size(); i++) {
				edge next=bridgelist[cur_idx].get(i);
				if (!choice[next.idx]) {
					pq.add(new edge(next.idx, next.weight));
				}
			}
		}
		return -1;
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && visited[i][j] == false) {
					number(i, j, num);
					num++;
				}
			}
		}
		bridgelist = new ArrayList[num];
		for (int i = 0; i < bridgelist.length; i++) {
			bridgelist[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					for (int dir = 0; dir < 4; dir++) {
						if (i + dr[dir] < 0 || j + dc[dir] < 0 | i + dr[dir] >= N || j + dc[dir] >= M) {
							continue;
						}
						if (map[i + dr[dir]][j + dc[dir]] == 0) {
							bridge(i, j, dir);
						}
					}
				}
			}
		}
		System.out.println(prim());
//		System.out.println(Arrays.toString(bridgelist));
//		System.out.println(Arrays.deepToString(map));
	}

}