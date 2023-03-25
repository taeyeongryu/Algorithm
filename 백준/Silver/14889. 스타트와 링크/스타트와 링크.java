import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static boolean[] choice;
	static int[][] map;
	static int[] choice1;
	static int[] choice2;
	static int total1;
	static int total2;
	static int min = Integer.MAX_VALUE;

	// N개중 N/2개 뽑는 조합 생각한다.
	static void combi(int depth, int count) {
		// 다 뽑았으면
		if (count == N / 2) {
			total1 = 0;
			total2 = 0;
			choice1 = new int[N / 2];
			choice2 = new int[N / 2];
			// choice2 만든다
			int idx1 = 0;
			int idx2 = 0;
			for (int i = 0; i < choice.length; i++) {
				if (choice[i]) {
					choice1[idx1++] = i;
				} else {
					choice2[idx2++] = i;
				}
			}
//			System.out.println(Arrays.toString(choice1));
//			
//			System.out.println(Arrays.toString(choice2));
//			System.out.println();
			for (int i = 0; i < choice1.length; i++) {
				for (int j = i + 1; j < choice1.length; j++) {
					cal1(choice1[i], choice1[j]);
				}
			}
			for (int i = 0; i < choice2.length; i++) {
				for (int j = i + 1; j < choice2.length; j++) {
					cal2(choice2[i], choice2[j]);
				}
			}
			min = Math.min(min, Math.abs(total1 - total2));
			// 점수 계산하는 메서드 만든다.
			return;
		}
		if (depth == N) {
			return;
		}
		choice[depth] = true;
		combi(depth + 1, count + 1);
		choice[depth] = false;
		combi(depth + 1, count);
	}

	static void cal1(int i, int j) {
		total1 += map[i][j];
		total1 += map[j][i];
	}

	static void cal2(int i, int j) {
		total2 += map[i][j];
		total2 += map[j][i];
	}

	public static void main(String[] args) {
		//N choice map 초기화
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		choice = new boolean[N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		combi(0, 0);
		System.out.println(min);
	}

}