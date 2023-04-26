import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] print_arr;
	static Set<String> set = new HashSet<>();
	static List<String> list = new ArrayList<>();
	static StringBuilder sb;

	static void Back(int depth) {
		if (depth == M) {
			sb = new StringBuilder();
			for (int i = 0; i < print_arr.length; i++) {
				sb.append(print_arr[i] + " ");
			}
			if (!set.contains(sb.toString())) {
				set.add(sb.toString());
				list.add(sb.toString());
			}
		} else {
			for (int i = 0; i < arr.length; i++) {
				print_arr[depth] = arr[i];
				Back(depth + 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		print_arr = new int[M];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		Back(0);
		Iterator it = list.iterator();
		sb = new StringBuilder();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}