import java.util.*;

public class Main {

	static int n;
	static int w;
	static int L;
	// 현재 다리에 올라가 있는 트럭의 갯수
	static int onnum_truk = 0;
	// 단위시간을 나타냄
	static int unit_time = 0;
	// idx트럭이 올라갈차례를 나타냄
	static int idx_truk = 0;
	static int onweight_truk = 0;

	static int[] trukarr;

	static Queue<Integer> queue = new LinkedList<>();

	static void time() {
		while (!queue.isEmpty()) {
			// 트럭을 다리위에 다 올리고, 다리위에 올라가있는 트럭이 다 사라지면
//			System.out.println("queue : " + queue);
//			System.out.println("onnum_truk : " + onnum_truk + ", onweight_truk : " + onweight_truk);
//			System.out.println("idx_truk : " + idx_truk + ", unit_time : " + unit_time);
			if (onnum_truk == 0 && idx_truk == n) {
				break;
			}
			// 올릴 트럭이 더 있을 때
			else if (idx_truk != n) {
				int left_truk = queue.poll();
				if (left_truk > 0) {
					onnum_truk--;
				}
				onweight_truk -= left_truk;
				// 다리위에 올릴 수 있으면
				if (L >= onweight_truk + trukarr[idx_truk]) {
					queue.add(trukarr[idx_truk]);
					onnum_truk++;
					onweight_truk += trukarr[idx_truk++];
				}
				// 없으면 빼고 빈트럭 넣는다.
				else {
					queue.add(0);
				}
				unit_time++;
			}
			// 올릴트럭은 없는데 다리위에 트럭이 있을 때
			else {
				while (onnum_truk > 0) {
					unit_time++;
					if (queue.poll() > 0) {
						onnum_truk--;
					}
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		w = sc.nextInt();
		L = sc.nextInt();
		trukarr = new int[n];
		for (int i = 0; i < trukarr.length; i++) {
			trukarr[i] = sc.nextInt();
		}
		for (int i = 0; i < w; i++) {
			queue.add(0);
		}
		time();
		System.out.println(unit_time);
	}

}