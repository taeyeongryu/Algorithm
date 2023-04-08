import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		int weight;

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// 인접리스트 만들자
		List<Node>[] adjlist = new ArrayList[n + 1];
		for (int i = 0; i < adjlist.length; i++) {
			adjlist[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjlist[s].add(new Node(e, w));
		}
		// 거리를 저장할 배열을 만든다.
		// 배열이기는 한데 pq의 배열이다.
		PriorityQueue<Integer>[] distpq = new PriorityQueue[n + 1];
		for (int i = 0; i < distpq.length; i++) {
			distpq[i] = new PriorityQueue<>(k, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return -Integer.compare(o1, o2);
				}
			});
		}
		// 자 다익스트라 드가자~~
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		distpq[1].add(0);
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int idx = cur.idx;
			int weight = cur.weight;
			for (int i = 0; i < adjlist[idx].size(); i++) {
				Node next = adjlist[idx].get(i);
				int nidx = next.idx;
				int nweight = next.weight;
				// 다음 노드의 거리배열이 k개가 안되면 거리배열에 저장하고
				// pq에 넣어준다.
				if (distpq[nidx].size() < k) {
					distpq[nidx].add(weight + nweight);
					pq.add(new Node(nidx, weight + nweight));
				}
				// 만약에 거리배열이 3개가 넘으면 가장큰 요소를 찾고
				// 그 노드보다 작으면 거리배열 갱신하고
				// pq에 넣어준다.
				else {
					if (distpq[nidx].peek() > weight + nweight) {
						distpq[nidx].poll();
						distpq[nidx].add(weight + nweight);
						pq.add(new Node(nidx, weight + nweight));
					}
				}
			}
		}
//		System.out.println(Arrays.toString(distpq));
		for (int i = 1; i < distpq.length; i++) {
			if (distpq[i].size() == k) {
				bw.append("" + distpq[i].peek() + "\n");
			} else {
				bw.append("-1\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
//		System.out.println(Arrays.toString(adjlist));
	}
}