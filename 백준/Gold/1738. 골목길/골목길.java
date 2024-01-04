import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Edge{
        int start;
        int end;
        int weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    static long[] dist;
    static int[] route;
    static int N,M;
    static ArrayList<Edge> edgeList;

    static boolean bellmanford(int startNode){
        dist[startNode] = 0;
        for (int i = 0; i < N-1; i++) {
            boolean update = false;
            for (int j = 0; j < edgeList.size(); j++) {
                Edge edge = edgeList.get(j);
                int start = edge.start;
                int end = edge.end;
                int weight = edge.weight;
                if (dist[start] != Long.MIN_VALUE && dist[start] + weight > dist[end]) {
                    update=true;
                    route[end] = start;
                    dist[end] = dist[start] + weight;
                }
            }
            if(!update){
                break;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        route = new int[N + 1];
        edgeList = new ArrayList<>();
        Arrays.fill(dist,Long.MIN_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(start, end, weight));
        }
        //false이면 사이클 존재하지 않는 것
        //true이면 사이클 존재하는지 check 해봐야 된는 것
        boolean bellmanford = bellmanford(1);
        Stack stack = new Stack();
        if (dist[N] == Long.MIN_VALUE) {
            bw.append("-1");
            bw.close();
            br.close();
            return;
        }
        //사이클이 목적지로 가는데 영향을 미치는지 아닌지 check 해야 된다.
        if (bellmanford){
            for (int i = 0; i < edgeList.size(); i++) {
                Edge edge = edgeList.get(i);
                int start = edge.start;
                int end = edge.end;
                int weight = edge.weight;

                if(existCycle(start,end,weight)&&isPath(end)){
                    bw.append("-1");
                    bw.close();
                    br.close();
                    return;
                }
            }
        }


        int end = N;
        while (true) {
            stack.add(end);
            if (end == 1) {
                break;
            }
            end = route[end];
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            bw.append(stack.pop() + " ");
        }
        bw.close();
        br.close();
    }
    private static boolean isPath(int end) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        visited[end]=true;
        q.offer(end);
        while (!q.isEmpty()) {
            int curNode = q.poll();
            for (Edge edge : edgeList) {
                if (edge.start!=curNode){continue;}
                if(visited[edge.end]){continue;}
                visited[edge.end]=true;
                q.offer(edge.end);
            }
        }
        if(visited[N]){
            return true;
        }
        return false;
    }

    private static boolean existCycle(int start, int end, int weight) {
        if(dist[start]==Long.MIN_VALUE){return false;}
        if(dist[end]<dist[start]+weight){
            return true;
        }
        return false;
    }
}