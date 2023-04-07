import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int min = Integer.MAX_VALUE;
		int[] arr = new int[N];
		int[] choice = new int[2];
		int left = 0;
		int right = arr.length - 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (min > Math.abs(sum)) {
				choice[0] = arr[left];
				choice[1] = arr[right];
				min = Math.abs(sum);
			}
			if (sum > 0) {
				right--;
			} else if (sum < 0) {
				left++;
			} else {
				break;
			}
		}
		bw.write(""+choice[0]+" ");
		bw.write(""+choice[1]);
		bw.close();
		br.close();

	}
}