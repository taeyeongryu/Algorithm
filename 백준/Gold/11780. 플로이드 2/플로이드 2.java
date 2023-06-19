import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] price;
	static int[][] path;

	static int[] findPath(int i, int j) {
		if (path[i][j] == 0) {
			return new int[] { i, j };
		} else {
			int[] pre = findPath(i, path[i][j]);
			int[] post = findPath(path[i][j], j);
			int[] result = new int[pre.length + post.length - 1];
			for (int k = 0; k < pre.length; k++) {
				result[k] = pre[k];
			}
			for (int k = 1; k < post.length; k++) {
				result[pre.length - 1 + k] = post[k];
			}
			return result;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		price = new int[n + 1][n + 1];
		path = new int[n + 1][n + 1];

		for (int i = 0; i < n + 1; i++) {
			Arrays.fill(price[i], 100000000);
		}
		for (int i = 0; i < n + 1; i++) {
			price[i][i] = 0;
		}

		StringTokenizer st;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(price[start][end]>weight) {
				price[start][end] = weight;
			}
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (price[i][j] > price[i][k] + price[k][j]) {
						price[i][j] = price[i][k] + price[k][j];
						path[i][j] = k;
					}
				}
			}
		}
//		for (int i = 1; i <=n; i++) {
//			for (int j = 1; j <=n; j++) {
//				System.out.print(price[i][j]+" ");
//			}
//			System.out.println();
//		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(price[i][j]!=100000000) {
					sb.append(price[i][j] + " ");
				}else {
					sb.append("0 ");
				}
			}
			sb.append("\n");
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(price[i][j]!=0&&price[i][j]!=100000000) {
					int[] tmp = findPath(i, j);
					sb.append(tmp.length+" ");
					for (int k = 0; k < tmp.length; k++) {
						sb.append(tmp[k]+" ");
					}
					sb.append("\n");
				}else {
					sb.append("0\n");
				}
			}
		}
		System.out.println(sb.toString());
	}

}