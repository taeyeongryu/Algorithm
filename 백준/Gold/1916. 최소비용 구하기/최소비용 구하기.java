import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Node{
        int index;
        int weight;
        public Node(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }
    static int N,M;
    static int[] dist;
    static List<Node>[] adjList;
    static PriorityQueue<Node> pq;
    private static void dijkstra(int index) {
        dist[index] = 0;
        pq.offer(new Node(index, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIndex = cur.index;
            int curWeight = cur.weight;

            if (curWeight > dist[curIndex]) {
                continue;
            }
            for (int i = 0; i < adjList[curIndex].size(); i++) {
                Node next = adjList[curIndex].get(i);
                int nextIndex = next.index;
                int nextWeight = next.weight + curWeight;
                if (nextWeight < dist[nextIndex]) {
                    dist[nextIndex]=nextWeight;
                    pq.offer(new Node(nextIndex, nextWeight));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, weight));
        }
        st = new StringTokenizer(br.readLine());
        int startIndex = Integer.parseInt(st.nextToken());
        int endIndex = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node node1, Node node2){
                return Integer.compare(node1.weight, node2.weight);
            }
        });
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra(startIndex);
        bw.append(dist[endIndex] + "");
        bw.close();
        br.close();
        
    }

}