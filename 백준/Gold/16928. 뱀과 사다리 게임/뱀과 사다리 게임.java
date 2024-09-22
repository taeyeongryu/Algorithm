import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
   static class Node{
       int n;
       int time;
       public Node(int n, int time) {
           this.n = n;
           this.time = time;
       }
       @Override
       public String toString(){
           return "n : " + n + ", time : " + time;
       }
   }
   static int[] teleport;
   static boolean[] visited;
   static int N,M;
   static Queue<Node> queue;
   static int findRealGoal(int n){
       if(teleport[n]==0){
           return n;
       }else{
           return findRealGoal(teleport[n]);
       }
   }
   static int bfs(){
       int result = 0;
       while(!queue.isEmpty()){

           Node cur = queue.poll();
           int curN = cur.n;
           int curTime = cur.time;

           if(curN==100){
               result = curTime;
               break;
           }

           for (int i = 1; i <= 6; i++) {
               int nextN = curN + i;
               if (nextN > 100) {
                   continue;
               }
               int realGoal = findRealGoal(nextN);
               if(!visited[realGoal]){
                   visited[realGoal]=true;
                   queue.offer(new Node(realGoal, curTime + 1));
               }
           }
       }
       return result;
   }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        teleport = new int[101];
        visited = new boolean[101];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            teleport[start] = end;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start =Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            teleport[start] = end;
        }
        visited[1]=true;
        queue.offer(new Node(1, 0));
        bw.append(bfs() + "");
        bw.close();
        br.close();
    }
}