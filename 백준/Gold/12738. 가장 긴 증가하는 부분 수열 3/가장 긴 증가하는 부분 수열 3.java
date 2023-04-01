import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[] L;

	static int Binarysearch(int left, int right, int key) {
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (L[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		// 수열 담는 배열
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int j = 0; j < arr.length; j++) {
			arr[j] = Integer.parseInt(st.nextToken());
		}
		// idx에 해당하는 길이의 증가수열의 끝값을 저장하는 배열
		L = new int[N + 1];
		Arrays.fill(L, Integer.MIN_VALUE);
		int len = 0;
		int idx = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > L[len]) {
				len += 1;
				L[len] = arr[i];
			} else {
				idx = Binarysearch(0, len, arr[i]);
				L[idx] = arr[i];
			}
//			System.out.println("i : "+ i);
//			System.out.println("idx : "+ idx);
//			System.out.println(len);
//			System.out.println(Arrays.toString(arr));
//			System.out.println(Arrays.toString(L));
//			System.out.println();
		}
		System.out.println(len);
	}

}