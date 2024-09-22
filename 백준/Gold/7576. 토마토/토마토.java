import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int[][] arr;
    static class Tomato{

        int r;
        int c;
        int time;
        public Tomato(int r, int c, int time){

            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    static boolean[][] visited;
    static Queue<Tomato> queue = new LinkedList();
    static int totalTomato;
    static int goodTomato;
    static int bfs(){
        int time = 0;
        while(!queue.isEmpty()){
            //지금 토마토
            Tomato cur = queue.poll();
            int curTime=cur.time;
            time=curTime;

            int curR = cur.r;
            int curC = cur.c;
            for (int i = 0; i < 4; i++) {

                int nextR = curR + dr[i];
                int nextC = curC + dc[i];
                if ( nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                if (arr[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                    goodTomato++;
                    visited[nextR][nextC]=true;
                    queue.offer(new Tomato( nextR, nextC, curTime + 1));
                }
            }
        }
        return time;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                if (arr[r][c] == 1 || arr[r][c] == 0) {
                    totalTomato++;
                }
                if (arr[r][c] == 1) {
                    goodTomato++;
                    visited[r][c] = true;
                    queue.offer(new Tomato(r, c, 0));
                }
            }
        }


        int result = bfs();
        if(totalTomato==goodTomato){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }
    }
}