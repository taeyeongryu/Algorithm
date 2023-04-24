import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		int total = 0;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i + 1] > arr[i]) {
				continue;
			} else if (arr[i + 1] == arr[i]) {
				arr[i]--;
				total++;
			} else {
				int tmp = arr[i]-arr[i+1];
				arr[i]=arr[i]-tmp-1;
				total+=tmp+1;
			}
		}
		System.out.println(total);
	}
}