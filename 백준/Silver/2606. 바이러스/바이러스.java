import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static List<Integer>[] adjList;
    static boolean[] check;
    static int bfs(){
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        check[1]=true;
        queue.offer(1);
        while (!queue.isEmpty()) {
          int cur = queue.poll();

            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                if(!check[next]){
                    count++;
                    check[next]=true;
                    queue.offer(next);
                }
            }
        }
        return count;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        check = new boolean[N + 1];
        for (int i = 0; i <adjList.length ; i++) {
            adjList[i] = new ArrayList<>();
        }

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            adjList[end].add(start);
        }
        bw.append(bfs()+"");
        bw.close();
        br.close();
    }
}