import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Node {
		int r;
		int c;
		int z;

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", z=" + z + "]";
		}

		public Node(int z, int r, int c) {
			this.r = r;
			this.c = c;
			this.z = z;
		}
	}

	static int[][][] maze;
	static int[][][] maze_tmp;
	// 0부터 3사이의 값이 중복되서 들어간다.
	static int[] rotate;
	// 0부터 4까지 사이의 값이 들어간다.
	static int[] arr;
	static boolean[] choice;
	// 상 하 좌 우 위 아래
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static int[][] start = { { 0, 0, 0 }, { 4, 0, 0 }, { 4, 4, 0 }, { 0, 4, 0 } };
	static int[][] end = { { 4, 4, 4 }, { 0, 4, 4 }, { 0, 0, 4 }, { 4, 0, 4 } };
	static int[][][] visited;
	static Queue<Node> queue = new LinkedList<>();
	static int min = Integer.MAX_VALUE;

	// bfs 부를때 visited초기화 하고 시작해야한다.
	// 방문처리는 안에서 한다.
	static void BFS(int start_idx) {
		int z = start[start_idx][0];
		int r = start[start_idx][1];
		int c = start[start_idx][2];
		if (maze_tmp[z][r][c] == 1) {
			visited[z][r][c] = 0;
			queue.add(new Node(z, r, c));
			while (!queue.isEmpty()) {
//				System.out.println(queue);
				Node tmp = queue.poll();
				int num = visited[tmp.z][tmp.r][tmp.c];
				for (int i = 0; i < dr.length; i++) {
					int nz = tmp.z + dz[i];
					int nr = tmp.r + dr[i];
					int nc = tmp.c + dc[i];
					if (0 > nz || 0 > nr || 0 > nc || nz >= 5 || nr >= 5 || nc >= 5) {
						continue;
					}
					if (maze_tmp[nz][nr][nc] == 1) {
						if (visited[nz][nr][nc] == 0) {
//							System.out.println("nz : " + nz);
//							System.out.println("nr : " + nr);
//							System.out.println("nc : " + nc);
							visited[nz][nr][nc] = num + 1;
							queue.offer(new Node(nz, nr, nc));
						}
					}
				}
			}
		} else {
			return;
		}
	}

	static void rotate(int[][] arr, int i) {
		for (int j = 0; j < i; j++) {
			rotate_one(arr);
		}
	}

	static void rotate_one(int[][] arr) {
		int[][] tmp = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				tmp[i][j] = arr[arr.length - j - 1][i];
			}
		}
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				arr[i][j] = tmp[i][j];
			}
		}
	}

	// rotate와 sequence가 정해지면 tmp 만드는 메서드
	static void maketmp() {
		int[][][] tmp = new int[5][5][5];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				for (int j2 = 0; j2 < tmp.length; j2++) {
					tmp[i][j][j2] = maze[i][j][j2];
				}
			}
		}
		// z축 돌면서
		for (int i = 0; i < 5; i++) {
			rotate(tmp[i], rotate[i]);
		}
		for (int i = 0; i < tmp.length; i++) {
			maze_tmp[i] = tmp[arr[i]];
		}
		// maze_tmp만들어 졌다.
	}

	static void go() {
		for (int i = 0; i < 1024; i++) {
			int tmp = i;
			for (int j = 0; j < rotate.length; j++) {
				rotate[j] = tmp % 4;
				tmp /= 4;
			}
			// 회전이 정해졌다.
			maketmp();
			// 정해진 회전과 위치로 만들었다 즉 maze_tmp 완성
			// 이제 bfs할차례
//			System.out.println(Arrays.deepToString(maze_tmp));
			for (int j = 0; j < start.length; j++) {
				visited = new int[5][5][5];
				BFS(j);
				int start_z = start[j][0];
				int start_r = start[j][1];
				int start_c = start[j][2];
				int end_z = end[j][0];
				int end_r = end[j][1];
				int end_c = end[j][2];
				if (visited[end_z][end_r][end_c] != 0) {
//					System.out.println("start_z : " + start_z);
//					System.out.println("start_r : " + start_r);
//					System.out.println("start_c : " + start_c);
					min = Math.min(min, visited[end_z][end_r][end_c]);
//					System.out.println("end_z : " + end_z);
//					System.out.println("end_r : " + end_r);
//					System.out.println("end_c : " + end_c);
//					System.out.println(Arrays.deepToString(visited));
//					System.out.println(visited[end_z][end_r][end_c]);
				}
			}
		}
	}

	static void permu(int depth) {
		if (depth == 5) {
			go();
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (!choice[i]) {
				choice[i] = true;
				arr[depth] = i;
				permu(depth + 1);
				choice[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		maze_tmp = new int[5][5][5];
		maze = new int[5][5][5];
		arr = new int[5];
		choice = new boolean[5];
		rotate = new int[5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int j2 = 0; j2 < 5; j2++) {
					maze[i][j][j2] = sc.nextInt();
				}
			}
		}
		permu(0);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
}