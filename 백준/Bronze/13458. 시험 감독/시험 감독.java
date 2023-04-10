import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		int B = sc.nextInt();
		int C = sc.nextInt();
		int[] result = new int[N];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= B) {
				result[i] = 1;
			} else {
				result[i] = (arr[i] - B) % C == 0 ? (arr[i] - B) / C : (arr[i] - B) / C + 1;
				result[i]++;
			}
		}
		long total = 0;
		for (int i = 0; i < result.length; i++) {
			total += result[i];
		}
		System.out.println(total);
//		System.out.println(Arrays.toString(result));
	}
}