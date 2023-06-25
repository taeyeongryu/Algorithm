import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N, M, Q;

	static final int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		int[][] dist = new int[N + 1][N + 1];
		int[][] cost = new int[N + 1][N + 1];
		int[] dog = new int[N + 1];
		Integer[] lowDogTimeArr = new Integer[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dog[i] = Integer.parseInt(st.nextToken());
			lowDogTimeArr[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dist[i][j] = (i == j) ? 0 : INF;
				cost[i][j] = (i == j) ? dog[i] : INF;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = c;
			dist[b][a] = c;
			cost[a][b] = cost[b][a] = c + Math.max(dog[a], dog[b]);
		}
		Arrays.sort(lowDogTimeArr, 1, N + 1, new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return dog[i1] - dog[i2];
			}
		});
		int idx = -1;
		for (int k = 1; k <= N; k++) {
			idx = lowDogTimeArr[k];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][idx] != INF && dist[idx][j] != INF) {
						// 일반적인 최단거리 초기화 식
						if (dist[i][j] > dist[i][idx] + dist[idx][j]) {
							dist[i][j] = dist[i][idx] + dist[idx][j];
						}

						// 위에서 i, k, j 중 개한테 방해받는 시간이 가장 긴 정점을
						// 기준으로 비용에 시간을 추가로 더해 줌.
						if (cost[i][j] > dist[i][j] + Math.max(dog[i], Math.max(dog[idx], dog[j]))) {
							cost[i][j] = dist[i][j] + Math.max(dog[i], Math.max(dog[idx], dog[j]));
						}
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append((cost[start][end] == INF ? -1 : cost[start][end]) + "\n");
		}
		System.out.println(sb.toString());
	}

}