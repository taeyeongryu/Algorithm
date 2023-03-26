import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static int[] arr;
	static int[] tmp;

	static void mergeSort(int start, int end) {
		if (start + 1 == end)
			return;
		int mid = (start + end) / 2;

		mergeSort(start, mid);
		mergeSort(mid, end);
		merge(start, end);

	}

	static void merge(int start, int end) {
		int mid = (start + end) / 2;
		int idx1 = start;
		int idx2 = mid;
		for (int i = start; i < end; i++) {
			if (idx1 == mid) {
				tmp[i] = arr[idx2++];
			} else if (idx2 == end) {
				tmp[i] = arr[idx1++];
			} else if (arr[idx1] < arr[idx2]) {
				tmp[i] = arr[idx1++];
			} else {
				tmp[i] = arr[idx2++];
			}
		}
		for (int i = start; i < end; i++) {
			arr[i] = tmp[i];
		}
	}

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tmp = new int[arr.length];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		mergeSort(0, arr.length);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append("" + arr[i] + "\n");
		}
		System.out.println(sb.toString());
	}

}