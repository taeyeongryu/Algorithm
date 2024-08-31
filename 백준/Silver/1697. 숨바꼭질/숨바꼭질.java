import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, count;
    static boolean[] visited;
    static class Step{
        int index;
        int count;
        public Step(int index, int count){
            this.index = index;
            this.count = count;
        }
        @Override
        public String toString(){
            return "index : " + index + ", count : " + count;
        }
    }
    static void bfs(){
        Queue<Step> queue = new LinkedList<>();
        visited[N] = true;
        queue.offer(new Step(N, 0));

        while(!queue.isEmpty()){
//            System.out.println("queue = " + queue);

            Step cur = queue.poll();
            if (3 * cur.index < 2 * K && 2 * cur.index < visited.length && !visited[cur.index * 2]) {
                if (2 * cur.index == K) {
                    count = cur.count+1;
                    break;
                } else {
                    visited[2 * cur.index] = true;
                    queue.offer(new Step(2 * cur.index, cur.count + 1));
                }
            }
            if (cur.index + 1 < visited.length && !visited[cur.index + 1]) {
                if(cur.index+1==K){
                    count = cur.count+1;
                    break;
                }else{
                    visited[cur.index+1]=true;
                    queue.offer(new Step(cur.index + 1, cur.count + 1));
                }
            }
            if (cur.index - 1 >= 0 && !visited[cur.index - 1]) {
                if(cur.index-1==K){
                    count = cur.count+1;
                    break;
                }else{
                    visited[cur.index-1]=true;
                    queue.offer(new Step(cur.index - 1, cur.count + 1));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[200001];

        if(N==K){
            bw.append("0");
        }
        else{
            bfs();
            bw.append(count + "");
        }
        bw.close();
        br.close();
    }
}