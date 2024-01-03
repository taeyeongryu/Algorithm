import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }
    static int T,N,M,W;
    static List<Edge> edgeList;
    static int[][] edgeMap;
    static long[] dist;
    //true이면 음수간선이 존재한다는 얘기이다.
    static boolean bellmanford(int startNode){
        boolean update = false;
        dist = new long[N + 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[startNode] = 0;
        for (int i = 1; i < N; i++) {
            update = false;
            for (int j = 0; j < edgeList.size(); j++) {
                Edge edge = edgeList.get(j);
                int start = edge.start;
                int end = edge.end;
                int weight = edge.weight;
                if (dist[start] != Long.MAX_VALUE && dist[end] > dist[start] + weight) {
                    update=true;
                    dist[end] = dist[start] + weight;
                }
            }
            if (!update){
                break;
            }
        }
        if (update) {
            for (int j = 0; j < edgeList.size(); j++) {
                Edge edge = edgeList.get(j);
                int start = edge.start;
                int end = edge.end;
                int weight = edge.weight;
                if (dist[start] != Long.MAX_VALUE && dist[end] > dist[start] + weight) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edgeList = new ArrayList<>();
            edgeMap = new int[N + 1][N + 1];
            for (int j = 0; j < edgeMap.length; j++) {
                for (int k = 0; k < edgeMap.length; k++) {
                    edgeMap[j][k] = 10001;
                }
            }
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if (edgeMap[s][e]==10001) {
                    edgeMap[s][e] = w;
                    edgeMap[e][s] = w;
                }else{
                    if (edgeMap[s][e] > w){
                        edgeMap[s][e] = w;
                        edgeMap[e][s] = w;
                    }
                }

            }
            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edgeMap[s][e] = -w;
            }
            for (int j = 1; j <=N ; j++) {
                for (int k = 1; k <=N ; k++) {
                    if (edgeMap[j][k] != 10001) {
                        edgeList.add(new Edge(j, k, edgeMap[j][k]));
                    }
                }
            }
            for (int j = 1; j <= N; j++) {
                if(bellmanford(j)){
                    bw.append("YES\n");
                    break;
                }
                if(j==N){
                    bw.append("NO\n");
                }
            }
        }
        bw.close();
        br.close();
    }
}