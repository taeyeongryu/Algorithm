import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr1 = new int[N];
		int[] arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		int idx = 0;
		int idx1 = 0;
		int idx2 = 0;
		int[] result = new int[N + M];

		while (idx1 < N && idx2 < M) {
			if (arr1[idx1] < arr2[idx2]) {
				result[idx++] = arr1[idx1++];
			} else {
				result[idx++] = arr2[idx2++];
			}
		}
		if (idx1 == N) {
			for (int i = idx2; i < M; i++) {
				result[idx++] = arr2[i];
			}
		} else {
			for (int i = idx1; i < N; i++) {
				result[idx++] = arr1[i];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			sb.append("" + result[i] + " ");
		}
		System.out.println(sb.toString());
	}
}