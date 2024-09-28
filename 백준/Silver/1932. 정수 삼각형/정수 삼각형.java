import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {-1, -1};
    static int[] dc = {-1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i<=N; i++){
            for (int j = 1; j <= i; j++) {
                for (int k = 0; k < 2; k++) {
                    int beforeR = i + dr[k];
                    int beforeC = j + dc[k];
                    dp[i][j] = Math.max(dp[beforeR][beforeC] + map[i][j], dp[i][j]);
                }
            }
        }
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                bw.append(map[i][j] + " ");
//            }
//            bw.append("\n");
//        }
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                bw.append(dp[i][j] + " ");
//            }
//            bw.append("\n");
//        }
        int result = 0;
        for (int i = 1; i <=N ; i++) {
            result = Math.max(result, dp[N][i]);
        }
        bw.append(result + "");
        bw.close();
        br.close();
    }
}