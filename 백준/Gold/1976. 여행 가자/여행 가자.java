import java.util.Scanner;

public class Main {
	public static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] dosi = new int[N+1][N+1];
		for (int i = 1; i < dosi.length; i++) {
			for (int j = 1; j < dosi.length; j++) {
				dosi[i][j]=sc.nextInt();
			}
		}
		int[] route = new int[M+1];
		for (int i = 1; i < route.length; i++) {
			route[i]=sc.nextInt();
		}
		parent = new int[N+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i]=i;
		}
		for (int i = 1; i < dosi.length; i++) {
			for (int j = 1; j < dosi.length; j++) {
				if (dosi[i][j]==1) {
					union(i, j);
				}
			}
		}
		int index = find(route[1]);
		for (int i = 2; i < route.length; i++) {
			if (index!=find(route[i])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[a] = b;
		}
	}
	public static int find(int a) {
		if (parent[a]==a) {
			return a;
		}
		else {
			return parent[a]=find(parent[a]);
		}
	}
			
}
