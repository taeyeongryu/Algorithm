import java.util.*;

public class Solution {
	static int N;
	//
	static int[][] map;
	static int[] choice;

	static boolean np(int[] arr) {
		int n = arr.length;
		int i = n - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		int j = n - 1;
		while (arr[i - 1] >= arr[j]) {
			j--;
		}
		swap(arr, i - 1, j);

		int k = n - 1;
		while (i < k) {
			swap(arr, i++, k--);
		}
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static int dis(int i1, int j1, int i2, int j2) {
		return Math.abs(i1 - i2) + Math.abs(j1 - j2);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			choice = new int[N];
			map = new int[N + 2][2];
			for (int i = 0; i < N + 2; i++) {
				if (!(i == 0 || i == 1)) {
					choice[i - 2] = i;
				}
				map[i][0] = sc.nextInt();
				map[i][1] = sc.nextInt();
			}
			int min = Integer.MAX_VALUE;
			do {
				int total = 0;
				total += dis(map[0][0], map[0][1], map[choice[0]][0], map[choice[0]][1]);
				for (int i = 0; i < choice.length - 1; i++) {
					total += dis(map[choice[i]][0], map[choice[i]][1], map[choice[i + 1]][0], map[choice[i + 1]][1]);
				}
				total += dis(map[1][0], map[1][1], map[choice[N - 1]][0], map[choice[N - 1]][1]);
				min = Math.min(min, total);
			} while (np(choice));
			System.out.printf("#%d %d%n", tc, min);
		}
	}

}