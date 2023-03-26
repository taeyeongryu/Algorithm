import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] arr;
	static int[] tmp;

	static void mergeSort(int start, int end) {
		if (start + 1 == end) {
			return;
		}

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
			} else if (arr[idx1] <= arr[idx2]) {
				tmp[i] = arr[idx2++];
			} else {
				tmp[i] = arr[idx1++];
			}
		}
		for (int i = start; i < end; i++) {
			arr[i] = tmp[i];
		}
	}

	static void quickSort(int start, int end) {
		if (start + 1 >= end) {
			return;
		}
		int pivot = arr[start];
		int left = start + 1;
		int right = end - 1;
		while (true) {
			while (left <= right && arr[left] <= pivot) {
				left++;
			}
			while (left <= right && arr[right] >= pivot) {
				right--;
			}
			if (left > right) {
				break;
			}
			// swap
			int tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
		}
		int tmp = arr[right];
		arr[right] = arr[start];
		arr[start] = tmp;
		quickSort(start, right);
		quickSort(right + 1, end);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tmp = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		mergeSort(0, N);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			bw.append("" + arr[i] + "\n");
		}
		br.close();
		bw.close();
	}
}