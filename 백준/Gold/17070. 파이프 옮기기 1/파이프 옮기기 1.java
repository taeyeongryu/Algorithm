import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        map = new int[N+2][N+2];
        dp = new int[3][N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][1][2] = 1;
        for (int i = 1; i <=N ; i++) {
            for (int j = 1; j <= N; j++) {
                if(hOrVCheck(i,j)){
                    dp[0][i][j] += dp[0][i][j - 1];
                    dp[0][i][j] += dp[2][i][j - 1];
                    dp[1][i][j] += dp[1][i - 1][j];
                    dp[1][i][j] += dp[2][i - 1][j];
                }
                if(dCheck(i,j)){
                    dp[2][i][j] += dp[0][i - 1][j - 1];
                    dp[2][i][j] += dp[1][i - 1][j - 1];
                    dp[2][i][j] += dp[2][i - 1][j - 1];
                }
            }
        }
        int result = dp[0][N][N] + dp[1][N][N] + dp[2][N][N];
        bw.append(result + "");
        bw.close();
        br.close();

    }

    private static boolean dCheck(int i, int j) {
        if(map[i][j]==1||map[i-1][j]==1||map[i][j-1]==1){
            return false;
        }
        return true;
    }

    private static boolean hOrVCheck(int i, int j) {
        if(map[i][j]==1){
            return false;
        }
        return true;
    }
}