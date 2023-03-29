import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int Search(int[] A, int num) {
		int start = 0;
		int end = A.length - 1;
		while (start <= end) {
			int mid = ((start + end) / 2);
			if (A[mid] == num) {
				return 1;
			} else if (A[mid] > num) {
				end = mid - 1;
			} else
				start = mid + 1;

		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		for (int i = 0; i < B.length; i++) {
			System.out.println(Search(A, B[i]));
		}
	}

}
