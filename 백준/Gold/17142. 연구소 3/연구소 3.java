import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;
		int weight;

		public Point(int r, int c, int weight) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", weight=" + weight + "]";
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	
	static int N;
	static int M;
	
	static int min = Integer.MAX_VALUE;
	
	static int[][] map;
	static boolean[][] visited;
	static int[] choice;
	
	static List<Point> list;

	static void combi(int depth, int at) {
		if (depth == M) {
			// 여기에 최소값 찾아내는 메서드 실행
			bfs();
			return;
		}
		for (int i = at; i < list.size(); i++) {
			choice[depth] = i;
			combi(depth + 1, i + 1);
		}
	}

	static void bfs() {
		int[][] tmp = new int[N][N];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				tmp[i][j]=map[i][j];
			}
		}
		
		visited = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < choice.length; i++) {
			Point p = list.get(choice[i]);
			visited[p.r][p.c]=true;
			q.add(p);
		}
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if(map[cur.r][cur.c]==0) {
				tmp[cur.r][cur.c]=cur.weight;	
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				if(nr<0||nr>=N||nc<0||nc>=N) {
					continue;
				}
				if(visited[nr][nc]) {
					continue;
				}
				if(map[nr][nc]==0||map[nr][nc]==2) {
					if(min>cur.weight+1) {
						visited[nr][nc]=true;
						q.add(new Point(nr,nc,cur.weight+1));
						
					}
					else {
						break;
					}
				}
				
			}
		}
		int time = 0;
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				if(map[i][j]==0&&!visited[i][j]) {
					return;
				}
				if(map[i][j]==0&&time<tmp[i][j]) {
					time=tmp[i][j];
				}
			}
		}
		min = time;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		choice = new int[M];
		list = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Point(i, j, 0));
				}
			}
		}
		combi(0,0);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
}