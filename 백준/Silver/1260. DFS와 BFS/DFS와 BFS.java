import java.io.*;
import java.util.*;

public class Main {

    static int N, M, V;
    static boolean[] visited;
    static List<Integer>[] adjList;
    static BufferedWriter bw;
    static void dfs(int vertex) throws IOException {
        for (int i = 0; i < adjList[vertex].size(); i++) {
            int nextVertex = adjList[vertex].get(i);
            if(!visited[nextVertex]){
                visited[nextVertex]=true;
                bw.append(nextVertex + " ");
                dfs(nextVertex);
            }
        }
    }

    static void bfs(int vertex) throws Exception {
        visited[vertex] = true;
        bw.append(vertex + " ");
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < adjList[cur].size(); i++) {
                int nextVertex = adjList[cur].get(i);
                if (!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    bw.append(nextVertex + " ");
                    queue.offer(nextVertex);
                }
            }
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start);
        }
        for(int i = 0; i< adjList.length; i++){
            Collections.sort(adjList[i]);
        }
        visited[V]=true;
        bw.append(V + " ");
        dfs(V);
        visited = new boolean[N + 1];
        bw.append("\n");
        bfs(V);
        bw.close();


    }
}