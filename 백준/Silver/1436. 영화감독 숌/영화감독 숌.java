import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		int num = 1;
		while (true) {
			String tmp = "" + num;
			if (tmp.indexOf("666") != -1) {
				cnt++;
			}
			if (cnt == N) {
				System.out.println(num);
				break;
			}
			num++;
		}
	}

}