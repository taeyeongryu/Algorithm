import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1,};
    static Queue<int[]> queue;
    static int M,N;
    static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0]+dr[i];
                int nextC = cur[1]+dc[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
        }


    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new boolean[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int C = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());
                map[R][C]=true;
            }
            int result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j]&&!visited[i][j]){
                        visited[i][j]=true;
                        queue = new LinkedList<>();
                        queue.offer(new int[]{i, j});
                        result++;
                        bfs();
                    }
                }
            }

            bw.append(result + "\n");
        }
        bw.close();
        br.close();
    }
}