import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	//여기서 노드를 index와 distance로만 설정한 이유는
	//이 노드는 인접리스트를 구성할 때와 PriorityQueue에 넣을 때 사용하는데
	//1. 인접리스트에 넣을때는 인접리스트의 인덱스 값이 있기때문에
	//   시작점이 어디인지를 굳이 나타낼 필요가 없어서
	//2. pq에 넣을 때는 다익스트라의 시작점부터 그 해당 노드까지의 거리를 저장하기 때문에
	//   현재 노드가 무었인지 시작점부터 지금노드까지의 최소비용이 얼마인지를 저장한다.
	//   따라서 2가지의 멤버변수만 선언했다.
	static class Node implements Comparable<Node> {
		int index;
		int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance >= o.distance ? 1 : -1;
		}
	}

	static final int INF = Integer.MAX_VALUE;
	
	static int n, m, start;
	//인접리스트
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	//최단거리임을 확정하기 위한 배열
	//이 배열의 값을 무한대로 채워놓은다음 그 값보다 작은 값이 pq에서 pop되면 
	//그 시작노드부터 그 노드까지의 거리를 확정해준다.
	static int[] d;
	static int[] trace;

	static void dijkstra(int start) {
		//pq선언
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//먼저 시작노드에서 시작노드 까지의 거리는 0이기 때문에 0으로 설정하고 시작한다.
		//이렇게 하는 이유는 만약 시작노드를 0으로 바꿔 놓지 않으면 다른 노드에서 시작노드를 향하는 edge가
		//pq에 들어가게 되고 그러면 시작노드의 도착 값이 이상한 값으로 설정 될수 있다.
		//0으로 설정하면 0을 도착노드로 하는 edge는 절대 pq에 들어가지 않는다.
		d[start] = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int dist = node.distance;
			int now = node.index;
			if (d[now] < dist) {
				continue;
			}
			for (int i = 0; i < graph.get(now).size(); i++) {
				Node next_node = graph.get(now).get(i);
				int index = next_node.index;
				int distance = next_node.distance;

				if (d[now] + distance < d[index]) {
					d[index] = d[now] + distance;
					pq.offer(new Node(index, d[now] + distance));
					trace[index] = now;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		d = new int[n + 1];
		trace = new int[n + 1];
		StringTokenizer st;
		Arrays.fill(d, INF);
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Node(end, num));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		Stack<Integer> stack = new java.util.Stack<>();
		stack.add(end);
		int end1 = end;
		int trace_start = 0;
		while (true) {
			trace_start = trace[end1];
			if (trace_start == 0) {
				break;
			}
			stack.add(trace_start);
			end1 = trace_start;
		}
		System.out.println(d[end]);
		System.out.println(stack.size());
		int st_size = stack.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < st_size; i++) {
			sb.append(stack.pop()+" ");
		}
		System.out.println(sb.toString());
	}
}
