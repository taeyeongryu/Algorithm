import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node>{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight,o.weight);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", weight=" + weight +
                    '}';
        }
    }
    static int V, E;
    static ArrayList<Node>[] adjList;
    static boolean[] check;
    static int prim(int startNode){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));
        int result = 0;
        int checkCount = 0;
        while (!pq.isEmpty()){
            
            Node cur = pq.poll();

            //이미 확정된 노드면 건너 뛴다.
            if(check[cur.index]){
                continue;
            }
            check[cur.index]=true;
            result += cur.weight;
            checkCount++;
            if(checkCount==V){
                break;
            }
            for (int i = 0; i < adjList[cur.index].size(); i++) {
                Node next = adjList[cur.index].get(i);
                if(!check[next.index]){
                    pq.add(next);
                }
            }
        }
        return result;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[V + 1];
        check = new boolean[V + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight));
            adjList[end].add(new Node(start, weight));
        }
        System.out.println(prim(1));
    }
}