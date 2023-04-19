import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		long[] F = new long[101];
		F[1]=1;
		F[2]=1;
		F[3]=1;
		for (int i = 4; i < F.length; i++) {
			F[i]=F[i-2]+F[i-3];
		}
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			System.out.println(F[N]);
		}
	}
}