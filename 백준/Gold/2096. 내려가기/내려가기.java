import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] max = new int[3];
		int[] min = new int[3];

		StringTokenizer st = new StringTokenizer(br.readLine());
		max[0] = Integer.parseInt(st.nextToken());
		max[1] = Integer.parseInt(st.nextToken());
		max[2] = Integer.parseInt(st.nextToken());
		min[0] = max[0];
		min[1] = max[1];
		min[2] = max[2];
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a1 = max[0];
			int b1 = max[1];
			int c1 = max[2];
			int a2 = min[0];
			int b2 = min[1];
			int c2 = min[2];
			int read1 = Integer.parseInt(st.nextToken());
			int read2 = Integer.parseInt(st.nextToken());
			int read3 = Integer.parseInt(st.nextToken());
			max[0] = Math.max(a1, b1) + read1;
			max[1] = Math.max(Math.max(a1, b1), c1) + read2;
			max[2] = Math.max(b1, c1) + read3;
			min[0] = Math.min(a2, b2) + read1;
			min[1] = Math.min(Math.min(a2, b2), c2) + read2;
			min[2] = Math.min(b2, c2) + read3;
		}
		int maxvalue = 0;
		int minvalue = Integer.MAX_VALUE;
		for (int i = 0; i < min.length; i++) {
			maxvalue = Math.max(maxvalue, max[i]);
			minvalue = Math.min(minvalue, min[i]);
		}
		System.out.println(maxvalue+" "+minvalue);
	}

}