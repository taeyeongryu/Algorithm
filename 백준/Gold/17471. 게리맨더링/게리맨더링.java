import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//	static class Node{
//		int idx;
//		int weight;
//		public Node(int idx, int weight) {
//			super();
//			this.idx = idx;
//			this.weight = weight;
//		}
//		@Override
//		public String toString() {
//			return "Node [idx=" + idx + ", weight=" + weight + "]";
//		}
//		
//	}
	static int N;
	static int[] population;
	static boolean[] visited;
	static int[] choice;
	static int totalpopulation;
	static int min = Integer.MAX_VALUE;
	static List<Integer>[] adjList;

	// 1부터 시작
	static void back(int depth) {
		if (depth == N + 1) {
//			System.out.println(Arrays.toString(choice));
			// 여기서 연걸성과 최소값 비교를 해야한다.
			int cha = cha();
//			System.out.println(cha);
			if(cha!=-1) {
				int zerocount=bfs(0);
				int onecount=bfs(1);
				if(zerocount+onecount==N) {
					min=cha;
//					System.out.println("min : "+min);
				}
			}
			return;
		}
		// 만약 depth 에 해당하는 것을 선택한다면
		choice[depth] = 1;
		back(depth + 1);
		// 만약 depth 에 해당하는 것을 선택하지 않는다면
		choice[depth] = 0;
		back(depth + 1);

	}

	static int cha() {
		int one = 0;
		int zero = 0;
		for (int i = 1; i < choice.length; i++) {
			if (choice[i] == 1) {
				one += population[i];
			} else {
				zero += population[i];
			}
		}
		if(one==0||zero==0) {
			return -1;
		}
		if (min > Math.abs(zero - one)) {
			return (int) Math.abs(zero - one);
		}
		return -1;
	}

	// 0과 1들이 연결되어 있는지 확인해보자.
	static int bfs(int type) {
		visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		int startidx = -1;
		int count = 0;
		for (int i = 1; i < choice.length; i++) {
			if (type == choice[i]) {
				startidx =i;
				break;
			}
		}
		count++;
		visited[startidx]=true;
		q.add(startidx);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < adjList[cur].size(); i++) {
				int next = adjList[cur].get(i);
				if(choice[next]==type&&!visited[next]) {
					count++;
					visited[next]=true;
					q.add(next);
				}
				
			}
		}
		return count;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		visited = new boolean[N + 1];
		choice = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalpopulation += population[i];
		}
		adjList = new List[N + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		back(1);
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
//		System.out.println(Arrays.toString(population));
//		System.out.println(Arrays.toString(adjList));
	}
}