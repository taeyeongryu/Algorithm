import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Node {
		int r;
		int c;
		int z;
		int num;

		Node(int z, int r, int c, int num) {
			this.z = z;
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	// 동 서 남 북 상 하
	static int[] dr = { 0, 0, 1, -1, 0, 0 };
	static int[] dc = { 1, -1, 0, 0, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static int L;
	static int R;
	static int C;
	static int end_z;
	static int end_r;
	static int end_c;
	static int result;
	static char[][][] map;
	static boolean[][][] visited;

	static Queue<Node> queue;

	static void BFS() {
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
//			System.out.println("tmp.z : " + tmp.z + ", tmp.r : " + tmp.r + ", tmp.c : " + tmp.c);
			if (end_z == tmp.z && end_r == tmp.r && end_c == tmp.c) {
				result = tmp.num;
			}
			for (int i = 0; i < dc.length; i++) {
				int nr = tmp.r + dr[i];
				int nc = tmp.c + dc[i];
				int nz = tmp.z + dz[i];
				if (0 <= nz && nz < L && 0 <= nr && nr < R && 0 <= nc && nc < C) {
					if (!visited[nz][nr][nc]) {
						if (map[nz][nr][nc] == '.'||map[nz][nr][nc] == 'E') {
							visited[nz][nr][nc] = true;
							queue.offer(new Node(nz, nr, nc, tmp.num + 1));
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			queue = new LinkedList<>();
			result = Integer.MAX_VALUE;
			L = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			visited = new boolean[L][R][C];
			map = new char[L][R][C];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String tmp = sc.next();
					for (int j2 = 0; j2 < C; j2++) {
						map[i][j][j2] = tmp.charAt(j2);
						if (map[i][j][j2] == 'S') {
							visited[i][j][j2] = true;
							queue.offer(new Node(i, j, j2, 0));
						}
						if (map[i][j][j2] == 'E') {
							end_z = i;
							end_r = j;
							end_c = j2;
						}
					}
				}
			}
			BFS();
			System.out.println(result == Integer.MAX_VALUE ? "Trapped!" : "Escaped in " + result + " minute(s).");

		}
	}
}