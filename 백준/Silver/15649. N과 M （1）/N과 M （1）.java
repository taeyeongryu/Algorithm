import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static boolean[] visited;

	static void backtracking(int depth) {
		if (depth == M) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		} else {
			for (int i = 0; i < visited.length; i++) {
				if (visited[i] == false) {
					visited[i] = true;
					arr[depth] = i + 1;
					backtracking(depth + 1);
					visited[i] = false;
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M];
		visited = new boolean[N];
		backtracking(0);

	}

}