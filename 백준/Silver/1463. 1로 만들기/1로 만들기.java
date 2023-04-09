import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		arr[1] = 0;
		for (int i = 2; i < arr.length; i++) {
			if (i % 3 == 0) {
				arr[i] = arr[i / 3] + 1;
			}
			if (i % 2 == 0) {
				if (arr[i] != 0) {
					arr[i] = Math.min(arr[i], arr[i / 2] + 1);
				} else {
					arr[i] = arr[i / 2] + 1;

				}
			}
			if (arr[i] != 0) {
				arr[i] = Math.min(arr[i], arr[i - 1] + 1);
			} else {
				arr[i] = arr[i - 1] + 1;
			}
		}
		System.out.println(arr[N]);

	}

}