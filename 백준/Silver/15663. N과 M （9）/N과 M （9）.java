import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] print_arr;
	static int[] arr;
	static boolean[] visited;
	static HashSet<String> set = new HashSet();
	static LinkedList<String> list = new LinkedList<>();
	static StringBuilder sb;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static void Back(int depth) {
		if (depth == M) {
			sb = new StringBuilder();
			for (int i = 0; i < print_arr.length; i++) {
				sb.append(print_arr[i] + " ");
			}
			if (!set.contains(sb.toString())) {
				list.add(sb.toString());
				set.add(sb.toString());
			}
			return;
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					print_arr[depth] = arr[i];
					Back(depth + 1);
					visited[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		print_arr = new int[M];
		visited = new boolean[N];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		Back(0);
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			bw.write(it.next());
			bw.write("\n");
		}
		bw.flush();
	}

}