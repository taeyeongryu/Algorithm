import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int[][] DP;
	static int N;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		DP = new int[2][N + 1];
		StringTokenizer stoken = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(stoken.nextToken());
		}
		for (int i = 1; i < DP[0].length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (DP[0][i] < DP[0][j] + 1) {
						DP[0][i] = DP[0][j] + 1;
						DP[1][i] = j;
					}
				}
			}
		}
		int max = 0;
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			if (max < DP[0][i]) {
				max = DP[0][i];
				idx = i;
			}
		}
		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < max; i++) {
			st.push(arr[idx]);
			idx = DP[1][idx];
		}
		bw.write(max+"\n");
		int size = st.size();
		for (int i = 0; i < size; i++) {
			bw.write(st.pop()+" ");
		}
		bw.close();
		br.close();
	}

}