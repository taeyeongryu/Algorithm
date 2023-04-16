import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long arr[] = new long[N + 1];
		long sum[] = new long[N + 1];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		long max = 0;
		long multi = 1;
		for (int i = arr.length - 1; i >= 1; i--) {
			sum[i] = arr[i] * multi++;
			
		}
		for (int i = 1; i < sum.length; i++) {
			max = Math.max(max, sum[i]);
		}
		System.out.println(max);
	}
}