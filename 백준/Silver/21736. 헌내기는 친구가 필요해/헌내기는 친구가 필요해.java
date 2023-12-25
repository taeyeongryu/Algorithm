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

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    static int N,M,startI,startJ,cnt;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static void bfs(){
        Queue<Node> q = new LinkedList<>();

        visited[startI][startJ] = true;
        q.offer(new Node(startI, startJ));

        while (!q.isEmpty()) {

            Node cur = q.poll();

            int r = cur.r;
            int c = cur.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                if (!visited[nr][nc] && map[nr][nc] != 'X') {
                    //빈공간이면
                    if(map[nr][nc]=='O'){
                        visited[nr][nc] = true;
                        q.offer(new Node(nr, nc));
                    }else{
                        cnt++;
                        visited[nr][nc] = true;
                        q.offer(new Node(nr, nc));
                    }
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
                char c = input.charAt(j);
                map[i][j] = c;
                if (c == 'I') {
                    startI = i;
                    startJ = j;
                }
            }
        }
        bfs();
        bw.append(cnt == 0 ? "TT" : cnt + "");
        bw.close();
        br.close();
    }
}