import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int index;
        long weight;
        public Node(int index, long weight){
            this.index = index;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node n1){
            return Long.compare(this.weight, n1.weight);
        }
    }
    static List<Node>[] adjList;
    static long[] fromStart;
    static long[] fromA;
    static long[] fromB;
    static int N,E,A,B;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        fromA = new long[N + 1];
        fromB = new long[N + 1];
        fromStart = new long[N + 1];

        Arrays.fill(fromA, Long.MAX_VALUE);
        Arrays.fill(fromB, Long.MAX_VALUE);
        Arrays.fill(fromStart, Long.MAX_VALUE);


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
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        dij(1, fromStart);
        dij(A, fromA);
        dij(B, fromB);
//        System.out.println("Arrays.toString(fromStart) = " + Arrays.toString(fromStart));
//        System.out.println("Arrays.toString(fromA) = " + Arrays.toString(fromA));
//        System.out.println("Arrays.toString(fromB) = " + Arrays.toString(fromB));
        boolean flag1 = true;
        boolean flag2 = true;
        if (fromStart[N] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            long result1 = 0;
            long result2 = 0;
            if (fromStart[A] == Long.MAX_VALUE || fromA[B] == Long.MAX_VALUE || fromB[N] == Long.MAX_VALUE) {
                result1 = Long.MAX_VALUE;
            } else {
                result1 = fromStart[A] + fromA[B] + fromB[N];
            }

            if (fromStart[B] == Long.MAX_VALUE || fromB[A] == Long.MAX_VALUE || fromA[N] == Long.MAX_VALUE) {
                result2 = Long.MAX_VALUE;
            } else {
                result2 = fromStart[B] + fromB[A] + fromA[N];
            }

            if (result1 == Long.MAX_VALUE && result2 == Long.MAX_VALUE) {
                System.out.println(-1);
            } else
                System.out.println(Math.min(result1, result2));
        }
    }
    static void dij(int start, long[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curIndex = cur.index;
            long curWeight = cur.weight;
            if(curWeight>dist[curIndex]){
                continue;
            }
            for (int i = 0; i <adjList[curIndex].size() ; i++) {
                Node next = adjList[curIndex].get(i);
                int nextIndex = next.index;
                long nextWeight = next.weight;

                if(curWeight+nextWeight<dist[nextIndex]){
                    dist[nextIndex] = curWeight + nextWeight;
                    pq.offer(new Node(nextIndex, dist[nextIndex]));
                }
            }
        }
    }
}