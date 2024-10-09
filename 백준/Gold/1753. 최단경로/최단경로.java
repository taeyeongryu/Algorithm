import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {


        int index;
        int weight;

        Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        public int compareTo(Node n1){
            return Integer.compare(this.weight, n1.weight);
        }

    }
    static int V, E;
    static int[] dist;
    static List<Node>[] adjList;
    static void dij(int start){
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIndex = cur.index;
            int curWeight = cur.weight;

            if(curWeight>dist[curIndex]){
                continue;
            }

            for (int i = 0; i < adjList[curIndex].size(); i++) {
                Node next = adjList[curIndex].get(i);
                int nextIndex = next.index;
                int nextWeight = next.weight;

                if(dist[nextIndex]>curWeight+nextWeight){
                    dist[nextIndex] = curWeight + nextWeight;
                    pq.offer(new Node(nextIndex, curWeight + nextWeight));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        adjList = new ArrayList[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Node(e, w));
        }
        dij(start);
        for (int i = 1; i < dist.length; i++) {
            if(dist[i]==Integer.MAX_VALUE){
                bw.append("INF\n");
            }else{
                bw.append(dist[i] + "\n");
            }
        }

        bw.close();
        br.close();
    }
}