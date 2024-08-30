import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N,M,count;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Point I;
    static char[][] map;
    static boolean visited[][];

    public static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        visited[I.r][I.c]=true;
        queue.offer(I);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];
                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] != 'X') {
                    if(map[nextR][nextC]=='P'){
                        count++;
                    }
                    visited[nextR][nextC]=true;
                    queue.offer(new Point(nextR, nextC));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j]=='I'){
                    I = new Point(i, j);
                }
            }
        }
        bfs();
        if(count==0){
            bw.append("TT");
        } else{
            bw.append(count + "");
        }
        bw.close();
        br.close();
    }
}