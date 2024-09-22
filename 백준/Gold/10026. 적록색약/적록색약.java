import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Color{
        int r;
        int c;
        char color;
        public Color(int r, int c, char color){
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][] visited;
    static Queue<Color> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N][N];
        map = new char[N][N];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        int normalCount=0;
        int colorCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    
                    normalCount++;
                    visited[i][j] = true;
                    queue.offer(new Color(i,j,map[i][j]));
                    normalbfs();
                }
            }
        }
        visited = new boolean[N][N];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {

                    colorCount++;
                    visited[i][j] = true;
                    queue.offer(new Color(i,j,map[i][j]));
                    colorbfs();
                }
            }
        }
        bw.append(normalCount + " " + colorCount);
        bw.close();
        br.close();
    }

    private static void normalbfs() {
        while(!queue.isEmpty()){
            Color cur = queue.poll();
            int curR = cur.r;
            int curC = cur.c;
            char curColor = cur.color;

            for (int i = 0; i <4 ; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                    continue;
                }
                if (map[nextR][nextC] == curColor && !visited[nextR][nextC]) {
                    visited[nextR][nextC]=true;
                    queue.offer(new Color(nextR, nextC, curColor));
                }
            }
        }
    }
    private static void colorbfs() {
        while(!queue.isEmpty()){
            Color cur = queue.poll();
            int curR = cur.r;
            int curC = cur.c;
            char curColor = cur.color;

            for (int i = 0; i <4 ; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                    continue;
                }
                if (!visited[nextR][nextC]) {
                    if(curColor=='R'||curColor=='G'){
                        if(map[nextR][nextC]=='R'||map[nextR][nextC]=='G'){
                            visited[nextR][nextC]=true;
                            queue.offer(new Color(nextR, nextC, map[nextR][nextC]));
                        }
                    }else{
                        if (map[nextR][nextC] == 'B') {
                            visited[nextR][nextC] = true;
                            queue.offer(new Color(nextR, nextC, curColor));
                        }
                    }
                }
            }
        }
    }
}