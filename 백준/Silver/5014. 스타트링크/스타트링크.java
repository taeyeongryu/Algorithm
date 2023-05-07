import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class floar {
		int stair;
		int times;

		public floar(int stair, int times) {
			super();
			this.stair = stair;
			this.times = times;
		}

		@Override
		public String toString() {
			return "floar [stair=" + stair + ", times=" + times + "]";
		}

	}

	static int F, S, G, U, D;
	static boolean visited[];
	static Queue<floar> q = new LinkedList<>();

	static int bfs() {
		while (!q.isEmpty()) {
			floar cur = q.poll();
			if (cur.stair == G) {
				return cur.times;
			}
			if (cur.stair + U <= F) {
				if (!visited[cur.stair + U]) {
					visited[cur.stair + U] = true;
					q.add(new floar(cur.stair + U, cur.times + 1));
				}
			}
			if (cur.stair - D >= 1) {
				if (!visited[cur.stair - D]) {
					visited[cur.stair - D] = true;
					q.add(new floar(cur.stair - D, cur.times + 1));
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();
		visited = new boolean[F + 1];
		visited[S] = true;
		q.add(new floar(S, 0));
		int result=bfs();
		System.out.println(result==-1?"use the stairs":result);
	}

}
