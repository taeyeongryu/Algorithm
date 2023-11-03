import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int r;
        int c;
        boolean isWater;
        int time;

        public Node(int r, int c, boolean isWater, int time) {
            this.r = r;
            this.c = c;
            this.isWater = isWater;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", isWater=" + isWater +
                    ", time=" + time +
                    '}';
        }
    }
    static int R,C,finalTime;
    static Queue<Node> q = new LinkedList<>();
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean rangeCheck(int r,int c){
        return 0 <= r && r < R && 0 <= c && c < C;
    }
    static void bfs(){
        while(!q.isEmpty()){
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int time = cur.time;
            //물이라면
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!rangeCheck(nr, nc)) continue;
                //물이라면
                if(cur.isWater){
                    if(map[nr][nc]=='.'||map[nr][nc]=='S'){
                        map[nr][nc] = '*';
                        q.offer(new Node(nr, nc, true, time + 1));
                    }
                }
                //고슴도치라면
                else{
                    if(!visited[nr][nc]&&(map[nr][nc]=='.'||map[nr][nc]=='D')){
                        if(map[nr][nc]=='D'){
                            finalTime = time + 1;
                            return;
                        }
                        visited[nr][nc]=true;
                        q.offer(new Node(nr, nc, false, time + 1));
                    }
                }

            }

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                //물
                if(map[i][j]=='*'){
                    q.offer(new Node(i, j, true, 0));
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                //고슴도치
                if(map[i][j]=='S'){
                    visited[i][j]=true;
                    q.offer(new Node(i, j, false, 0));
                }
            }
        }
        bfs();
        bw.append(finalTime == 0 ? "KAKTUS" : finalTime + "");
        bw.close();
        br.close();

    }
}