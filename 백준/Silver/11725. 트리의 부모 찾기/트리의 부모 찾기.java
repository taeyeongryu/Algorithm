import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N + 1];
        Arrays.fill(parent, -1);
        parent[1] = 0;
        List<Integer>[] adjList = new ArrayList[N + 1];

        StringTokenizer st;
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                //부모가 있어?
                if (parent[next] != -1) {
                    continue;
                }
                parent[next] = cur;
                queue.offer(next);
            }
        }
        for (int i = 2; i <=N ; i++) {
            bw.append(parent[i] + "\n");
        }
        bw.close();
        br.close();
    }
}