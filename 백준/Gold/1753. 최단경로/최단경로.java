import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", weight=" + weight +
                    '}';
        }
    }
    static void dji(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start]=0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
//            System.out.println(pq);
            Node cur = pq.poll();

            if(dist[cur.index]<cur.weight){
                continue;
            }

            for (int i = 0; i <adjList[cur.index].size(); i++) {
                Node next = adjList[cur.index].get(i);
                if(dist[cur.index]+next.weight<dist[next.index]){
                    dist[next.index] = dist[cur.index] + next.weight;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }
    }
    static int V, E;
    static final int MAX_VALUE = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V + 1];

        Arrays.fill(dist,MAX_VALUE);

        adjList = new ArrayList[V + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, w));
        }
        dji(start);
        for (int i = 1; i < V+1; i++) {
            bw.write(dist[i] == MAX_VALUE ? "INF\n" : "" + dist[i] + "\n");
        }
        bw.close();
        br.close();
    }
}