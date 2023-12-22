import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static class Node{
        int r;
        int c;
        int dist;
        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    static int[][] map,dist;
    static boolean[][] visited;
    static int N,M,startR,startC;

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startR, startC, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int curDist = cur.dist;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                //범위 check
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                //map위치 1인지 2인지 0인지 check
                if (map[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    dist[nr][nc]=curDist+1;
                    q.offer(new Node(nr, nc, curDist + 1));
                }
            }
        }
    }
    static void noVisitCheck(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    dist[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2){
                    startR = i;
                    startC = j;
                }
            }
        }
        bfs();
        noVisitCheck();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.append(dist[i][j] + " ");
            }
            bw.append("\n");
        }
        bw.close();
        br.close();

    }
}