import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] DP = new int[N + 1];
		if (N >= 2) {
			DP[0] = 0;
			DP[1] = 1;
			DP[2] = 3;
			for (int i = 3; i < DP.length; i++) {
				DP[i] = (DP[i - 1]  + DP[i - 2] * 2)%10007;
			}
			System.out.println(DP[N]);

		} else {
			System.out.println(1);
		}
	}
}