import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int[] num_arr;
	// 연산자 숫자 들어있는 배열
	static int[] arr;
	// 이미 썻는지 확인하는 배열
	static boolean[] visited;
	// 선택한것 앞에서 부터 순서대로 넣어주는 배열
	static int[] choice;
	static int N;

	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	static void cal() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num_arr.length; i++) {
			list.add(num_arr[i]);
		}
		for (int i = 0; i < choice.length; i++) {
			int tmp1 = list.remove(0);
			int tmp2 = list.remove(0);
			switch (choice[i]) {
			case 0:
				list.add(0, tmp1 + tmp2);
				break;
			case 1:
				list.add(0, tmp1 - tmp2);
				break;
			case 2:
				list.add(0, tmp1 * tmp2);
				break;
			case 3:
				list.add(0, tmp1 / tmp2);
				break;
			}
		}
		max = Math.max(max, list.get(0));
		min = Math.min(min, list.get(0));
	}

	static void permutation(int depth) {
		if (depth == choice.length) {
			cal();
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choice[depth] = arr[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num_arr = new int[N];
		arr = new int[N - 1];
		choice = new int[N - 1];
		visited = new boolean[N - 1];
		for (int i = 0; i < N; i++) {
			num_arr[i] = sc.nextInt();
		}
		int plus = sc.nextInt();
		int minus = sc.nextInt();
		int multi = sc.nextInt();
		int div = sc.nextInt();
		int idx = 0;
		for (int i = 0; i < plus; i++) {
			arr[idx++] = 0;
		}
		for (int i = 0; i < minus; i++) {
			arr[idx++] = 1;
		}
		for (int i = 0; i < multi; i++) {
			arr[idx++] = 2;
		}
		for (int i = 0; i < div; i++) {
			arr[idx++] = 3;
		}
		permutation(0);
		System.out.println(max);
		System.out.println(min);
	}

}