import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		int len1 = str1.length();
		int len2 = str2.length();
		int[][] DP = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					DP[i][j] = DP[i - 1][j - 1] + 1;

				} else {
					DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
				}
			}
		}
		Stack<Character> st = new Stack<>();
		int i = len1;
		int j = len2;
		while (true) {
			if (i == 0 || j == 0) {
				break;
			}
			if (DP[i][j] == DP[i - 1][j]) {
				i--;
			} else if (DP[i][j] == DP[i][j - 1]) {
				j--;
			} else {
				i--;
				j--;
				st.push(str1.charAt(i));
			}
		}
		System.out.println(DP[len1][len2]);
		StringBuilder sb = new StringBuilder();
		if (0 != DP[len1][len2]) {
			int size = st.size();
			for (int j2 = 0; j2 < size; j2++) {
				sb.append(st.pop());
			}
			System.out.println(sb.toString());
		}
	}
}