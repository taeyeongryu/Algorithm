import java.util.Scanner;

public class Main {
	static int[] arr;
	static int cnt = 0;
	static int N, S;

	static void Back(int depth, int total) {
		if (depth == N) {
			if (total==S) {
				cnt++;
			}
		} else {
			Back(depth + 1, total + arr[depth]);
			Back(depth + 1, total);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=sc.nextInt();
		}
		if (S==0) {
			cnt--;
		}
		Back(0, 0);
		System.out.println(cnt);

	}

}