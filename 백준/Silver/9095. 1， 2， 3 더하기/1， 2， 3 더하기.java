import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] arr = new int[12];
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		for (int i = 4; i < arr.length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
		}
		for (int i = 0; i < T; i++) {
			System.out.println(arr[sc.nextInt()]);
		}
	}
}