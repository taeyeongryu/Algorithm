import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int x = 0;
		int y = 0;
		boolean flag = false;
		while (3 * y <= N) {
			int tmp = N - 3 * y;
			if (tmp % 5 == 0) {
				x = tmp / 5;
				flag = true;
				break;
			} else {
				y++;
			}
		}
		if (flag) {
			System.out.println(x + y);
		} else {
			System.out.println(-1);
		}
	}

}