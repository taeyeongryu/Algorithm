import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, 1, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int[][][] arr;
    static class Tomato{
        int h;
        int r;
        int c;
        int time;
        public Tomato(int h,int r, int c, int time){
            this.h = h;
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    static boolean[][][] visited;
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
            int curH = cur.h;
            int curR = cur.r;
            int curC = cur.c;
            for (int i = 0; i < 6; i++) {
                int nextH = curH + dh[i];
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];
                if (nextH < 0 || nextH >= H || nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                if (arr[nextH][nextR][nextC] == 0 && !visited[nextH][nextR][nextC]) {
                    goodTomato++;
                    visited[nextH][nextR][nextC]=true;
                    queue.offer(new Tomato(nextH, nextR, nextC, curTime + 1));
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
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][N][M];
        visited = new boolean[H][N][M];
        for (int h = 0; h <H ; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    arr[h][r][c] = Integer.parseInt(st.nextToken());
                    if (arr[h][r][c] == 1 || arr[h][r][c] == 0) {
                        totalTomato++;
                    }
                    if(arr[h][r][c]==1){
                        goodTomato++;
                        visited[h][r][c]=true;
                        queue.offer(new Tomato(h, r, c, 0));
                    }
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