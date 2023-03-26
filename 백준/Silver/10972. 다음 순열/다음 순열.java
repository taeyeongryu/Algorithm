import java.util.*;

public class Main {
	static boolean np(int[] arr) {
		int n = arr.length;

		int i = n - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i--;
		}
		if (i == 0)
			return false;

		int j = n - 1;

		while (arr[i - 1] >= arr[j]) {
			j--;
		}
		swap(arr, i - 1, j);
		int k = n - 1;
		while (i < k) {
			swap(arr, i++, k--);
		}
		return true;

	}

	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=sc.nextInt();
		}
		if (np(arr)) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
		} else {
			System.out.println(-1);
		}
	}
}