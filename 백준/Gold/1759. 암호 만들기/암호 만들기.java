import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] arr;
	static char[] result;
	static String moeum = "aeiou";;
	static StringBuilder sb = new StringBuilder();

	static void Back(int depth, int at, int mo) {
		if (L - mo < 2) {
			return;
		}
		
		if (depth == L) {
			if (mo<1) {
				return;
			}
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i] + "");
			}
			sb.append("\n");
			return;
		}
		for (int i = at; i < arr.length; i++) {
			// 자음이면
			if (moeum.indexOf(arr[i]) == -1) {
				result[depth] = arr[i];
				Back(depth + 1, i + 1, mo);
			}
			// 모음이면
			else {
				result[depth] = arr[i];
				Back(depth + 1, i + 1, mo + 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		result = new char[L];
		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		Back(0, 0, 0);
		System.out.println(sb.toString());
	}

}