import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int max = Integer.MIN_VALUE;
		long[] arr = new long[N];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = sc.nextLong();
		}
		int[] DP = new int[N];
		for (int j = 0; j < DP.length; j++) {
			DP[j] = 1;
			for (int j2 = 0; j2 < j; j2++) {
				if (arr[j2] < arr[j]) {
					DP[j] = Math.max(DP[j], DP[j2] + 1);
				}
			}
			max = Math.max(max, DP[j]);
		}
		System.out.println(max);
	}

}