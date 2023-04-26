import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] arr, result;
	static StringBuilder sb = new StringBuilder();

	static void back(int depth, int at) {
		if (depth == 6) {
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = at; i < arr.length; i++) {
			result[depth] = arr[i];
			back(depth + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			if (k == 0) {
				break;
			}
			arr = new int[k];
			result = new int[6];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			back(0, 0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}