import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static void bfs(List<Integer>[] adjList, int start, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        visited[start]=true;
        q.offer(start);

        while(!q.isEmpty()){
            int cur = q.poll();
            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                if(!visited[next]){
                    visited[next]=true;
                    q.offer(next);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new List[N+1];

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
        boolean visited[] = new boolean[N + 1];
        int result = 0;
        for (int i = 1; i < N + 1; i++) {
            if(!visited[i]){
                result++;
                bfs(adjList,i,visited);
            }
        }
        bw.append(result + "");
        bw.close();
        br.close();

    }
}