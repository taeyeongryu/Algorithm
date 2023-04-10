import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		int[][] DP = new int[101][100000 + 1];
		for (int i = 1; i <= N; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}

		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <= 100000; j++) {
				if (W[i]>j) {
					DP[i][j]=DP[i-1][j];
				}else {
					DP[i][j]=Math.max(DP[i-1][j], DP[i-1][j-W[i]]+V[i]);
				}
			}
		}
		System.out.println(DP[N][K]);
	}
}
