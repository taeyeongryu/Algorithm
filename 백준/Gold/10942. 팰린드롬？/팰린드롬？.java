import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] checkarr;
	static int[] arr;
	static int N, M;

	static int check(int start, int end) {
		//이미 구한적이 없으면
		if (checkarr[start][end]==-1) {
			// 같을때
			if (start == end) {
				checkarr[start][end] = 1;
			}
			// 하나차이일때
			else if (end - start == 1) {
				if (arr[start] == arr[end]) {
					checkarr[start][end] = 1;
				} else {
					checkarr[start][end] = 0;
				}
			}
			// 차이가 클때
			else {
				int tmp = check(start + 1, end - 1);
				if (tmp == 1) {
					if (arr[start] == arr[end]) {
						checkarr[start][end] = 1;
					} else {
						checkarr[start][end] = 0;
					}
				} else if (tmp == 0) {
					checkarr[start][end] = 0;
				}
			}
			return checkarr[start][end];
		}
		//이미 구한적이 있으면
		else {
			return checkarr[start][end];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		checkarr = new int[N + 1][N + 1];
		for (int i = 0; i < checkarr.length; i++) {
			Arrays.fill(checkarr[i], -1);
		}
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			bw.write(""+check(start, end)+"\n");
		}
		bw.close();
		br.close();
	}
}