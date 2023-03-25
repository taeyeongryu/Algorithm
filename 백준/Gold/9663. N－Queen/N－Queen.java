import java.util.Scanner;

public class Main {
	static int N;
	static boolean[] col;
	static boolean[] side;
	static boolean[] side2;
	static int cnt;

	static void Back(int row) {
		if (row == N) {
			cnt++;
		}
		for (int i = 0; i < col.length; i++) {
			if (col[i] == false) {
				if (side[row + i] == false) {
					if (side2[N - 1 - row + i] == false) {
						col[i] = true;
						side[row + i] = true;
						side2[N - 1 - row + i] = true;
						Back(row + 1);
						col[i] = false;
						side[row + i] = false;
						side2[N - 1 - row + i] = false;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new boolean[N];
		side = new boolean[2 * N - 1];
		side2 = new boolean[2 * N - 1];
		Back(0);
		System.out.println(cnt);
	}

}
