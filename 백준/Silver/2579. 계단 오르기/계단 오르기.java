import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] DP = new int[2][N+1];
		int arr[] = new int[N+1];
		for (int i = 1; i < arr.length; i++) {
			arr[i]=sc.nextInt();
		}
		if (N>=2) {
			DP[0][0]=0;
			DP[1][0]=0;
			DP[0][1]=arr[1];
			DP[1][1]=0;
			DP[0][2]=arr[1]+arr[2];
			DP[1][2]=arr[2];
			for (int i = 3; i < N+1; i++) {
				DP[0][i]=DP[1][i-1]+arr[i];
				DP[1][i]=Math.max(DP[1][i-2],DP[0][i-2])+arr[i];
			}
			System.out.println(Math.max(DP[0][N],DP[1][N]));
		}
		else {
			System.out.println(arr[N]);
		}

	}
}