import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static boolean visited[];
	static ArrayList<Integer>[] list;

	static int N;
	static int a, b;
	static int M;
	static int result = 0;

	static void DFS(int i, int chon) {
		visited[i] = true;
		if (i == b) {
			result = chon;
			return;
		} else {
			for (int j = 0; j < list[i].size(); j++) {
				if (!visited[list[i].get(j)]) {
					DFS(list[i].get(j), chon + 1);
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = sc.nextInt();
		b = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			list[x].add(y);
			list[y].add(x);
		}
		DFS(a, 0);
		System.out.println(result > 0 ? result : -1);

	}

}