import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int max;
    //map 영역인지는 확인하고 들어온다, 즉 자리는 있는것
    //방문했는지도 확인함
    static void dfs(int r, int c, int sum, int depth) {
//        System.out.println("r : " + r + ", c : " + c);
        int num = map[r][c];
        if (depth == 3) {
            max = Math.max(max, sum + num);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];
            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                continue;
            }
            if(!visited[nextR][nextC]){
                visited[nextR][nextC] = true;
                dfs(nextR, nextC, sum + num, depth + 1);
                visited[nextR][nextC] = false;
            }
        }

    }
    public static void checkF(int r, int c){
        for (int i = 0; i < 4; i++) {

            int nextTwoR = r + dr[i] * 2;
            int nextTwoC = c + dc[i] * 2;
            if (nextTwoR < 0 || nextTwoR >= N || nextTwoC < 0 || nextTwoC >= M) {
                continue;
            }
            int sideR = r + dr[i] + dr[(i + 1) % 4];
            int sideC = c + dc[i] + dc[(i + 1) % 4];
            int sum = 0;
            if (!(sideR < 0 || sideR >= N || sideC < 0 || sideC >= M)) {
                sum += map[r][c];
                sum += map[r + dr[i]][c + dc[i]];
                sum += map[nextTwoR][nextTwoC];
                sum += map[sideR][sideC];
            }
            max = Math.max(sum, max);
            sum = 0;
            sideR = r + dr[i] + dr[(i + 3) % 4];
            sideC = c + dc[i] + dc[(i + 3) % 4];
            if (!(sideR < 0 || sideR >= N || sideC < 0 || sideC >= M)) {
                sum += map[r][c];
                sum += map[r + dr[i]][c + dc[i]];
                sum += map[nextTwoR][nextTwoC];
                sum += map[sideR][sideC];
            }
            max = Math.max(sum, max);
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, 0);
                visited[i][j] = false;
                checkF(i, j);
            }
        }
        System.out.println(max);
    }
}