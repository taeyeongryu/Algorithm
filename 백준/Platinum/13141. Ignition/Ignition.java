import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class edge{
		int start;
		int end;
		int weight;
		public edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
	}
	static int n, m, MAX = 10000;
	static List<edge> edgelist= new ArrayList<>();
	static int[][] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		distance = new int[n + 1][n + 1];
		for (int i = 0; i < distance.length; i++) {
			Arrays.fill(distance[i], MAX);
		}
		for (int i = 0; i < n + 1; i++) {
			distance[i][i] = 0;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			edgelist.add(new edge(a, b, l));
			if(distance[a][b]>l) {
				distance[a][b] = l;
				distance[b][a] = l;
			}
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}
		//k에 불을 붙인다고 했을 때
		double min = Double.MAX_VALUE;
		for (int k = 1; k <=n; k++) {
			double time = Double.MIN_VALUE;
			for (int i = 0; i < edgelist.size(); i++) {
				edge cur = edgelist.get(i);
				double tmp = (distance[k][cur.end]+distance[k][cur.start]+cur.weight)/(double)2;
				time = Math.max(time, tmp);
			}
			min = Math.min(min, time);
		}
		System.out.println(min);
	}
}