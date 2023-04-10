import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] pre = new int[1];
		pre[0] = Integer.parseInt(br.readLine());
		int[] cur=null;

		if (N==1) {
			System.out.println(pre[0]);
		} else {
			for (int i = 2; i <= N; i++) {
				cur = new int[i];
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < cur.length; j++) {
					cur[j] = Integer.parseInt(st.nextToken());
				}
				for (int j = 0; j < i; j++) {
					if (j == 0) {
						cur[j] = pre[j] + cur[j];
					} else if (j == i - 1) {
						cur[j] = cur[j] + pre[j - 1];
					} else {
						cur[j] = Math.max(pre[j-1], pre[j]) + cur[j];
					}
				}
				pre = cur;
			}
			int max = 0;
			for (int i = 0; i < cur.length; i++) {
				max = Math.max(max, cur[i]);
			}
			System.out.println(max);

		}
	}
}