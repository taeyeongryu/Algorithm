import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(arr);
		int max_count = 1;
		int count = 1;
		long curnum = arr[0];
		long max = Long.MIN_VALUE;
		for (int i = 1; i < arr.length; i++) {
			if (curnum==arr[i]) {
				count++;
			}
			else {
				curnum=arr[i];
				count=1;
			}
			if (count>max_count) {
				max = curnum;
				max_count = count;
			}
		}
//		System.out.println(max);
		System.out.println(max==Long.MIN_VALUE?arr[0]:max);
	}

}