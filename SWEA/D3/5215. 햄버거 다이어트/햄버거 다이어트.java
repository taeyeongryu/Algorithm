import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static class ing {
		int cal;
		int taste;

		public ing(int taste, int cal) {
			this.cal = cal;
			this.taste = taste;
		}
	}

	static int N;
	static int L;
	static int max;

	static ing[] ings;
	static boolean[] choice;

	static void DFS(int depth) {
		if (depth == N) {
			int cal = 0;
			int taste = 0;
			for (int i = 0; i < ings.length; i++) {
				if (choice[i] == true) {
					cal += ings[i].cal;
					taste += ings[i].taste;
				}
			}
			if (cal <= L) {
				max = Math.max(max, taste);
			}
		} else {
			choice[depth] = true;
			DFS(depth + 1);
			choice[depth] = false;
			DFS(depth + 1);
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			L = sc.nextInt();
			max = 0;
			ings = new ing[N];
			choice = new boolean[N];
			for (int i = 0; i < N; i++) {
				ings[i] = new ing(sc.nextInt(), sc.nextInt());
			}
			DFS(0);
			System.out.printf("#%d %d%n",tc,max);

		}
	}

}