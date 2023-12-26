import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int index;
        int time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", time=" + time +
                    '}';
        }
    }
    static int[] map = new int[101];
    static boolean[] visited = new boolean[101];
    static int[] ladder = new int[101];
    static int[] snake = new int[101];

    static int bfs(){
        int result = 0;
        Queue<Node> q = new LinkedList<>();
        visited[1] = true;
        q.offer(new Node(1, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int index = cur.index;
            int time = cur.time;

            if (index == 100) {
                result = time;
                break;
            }
            for (int i = 6; i >= 1; i--) {
                //다음 위치
                int nextIndex = index + i;
                //위치가 벗어나지 않는지 check
                if (!(1 <= nextIndex && nextIndex <= 100)) {
                    continue;
                }
                //사다리 라면
                if(ladder[nextIndex]!=0) {
                    //위치 이동
                    nextIndex = ladder[nextIndex];
                }
                //뱀이라면
                else if (snake[nextIndex]!=0){
                    //위치 이동
                    nextIndex = snake[nextIndex];
                }
                //방문한적 있는지 check
                if(visited[nextIndex]){
                    continue;
                }
                visited[nextIndex] = true;
                q.offer(new Node(nextIndex, time + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladder[start] = end;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snake[start] = end;
        }
        bw.append("" + bfs());
        bw.close();
        br.close();
    }
}