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
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] maxarr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int max = arr[arr.length - 1];
			for (int i = arr.length - 1; i >= 0; i--) {
				max = Math.max(max, arr[i]);
				maxarr[i] = max;
			}
			long total = 0;
			for (int i = 0; i < arr.length; i++) {
				total+=maxarr[i]-arr[i];
			}
			bw.write(""+total+"\n");
		}
		bw.close();
		br.close();
	}
}