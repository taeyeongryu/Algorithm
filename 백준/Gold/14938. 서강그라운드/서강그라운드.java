import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m, r, t, MAX = 10000;
	static int[] items;
	static int[][] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		items = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < items.length; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		distance = new int[n + 1][n + 1];
		for (int i = 0; i < distance.length; i++) {
			Arrays.fill(distance[i], MAX);
		}
		for (int i = 0; i < n + 1; i++) {
			distance[i][i] = 0;
		}
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			distance[a][b] = l;
			distance[b][a] = l;
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int ki = 1; ki <= n; ki++) {
			int count = 0;
			for (int i = 1; i <= n; i++) {
				if(distance[ki][i]<=m) {
					count+=items[i];
				}
			}
			max = Math.max(max, count);
		}
		System.out.println(max);
	}
}