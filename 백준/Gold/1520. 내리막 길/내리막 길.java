import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] arr, result;
	static boolean[][] visited;
	static int M, N;

	static int find(int r, int c) {
		if (result[r][c] > 0) {
			return result[r][c];
		} else if (visited[r][c]) {
			return result[r][c];
		} else {
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nc < 0 || nr >= M || nc >= N)
					continue;

				if (arr[r][c] < arr[nr][nc]) {
					result[r][c] += find(nr, nc);
				}
			}
//			System.out.println("r : " + r + ", c : " + c);
			visited[r][c] = true;
			return result[r][c];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[M][N];
		visited = new boolean[M][N];
		result = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result[0][0] = 1;
		System.out.println(find(M - 1, N - 1));
//		System.out.println(Arrays.deepToString(result));
	}
}