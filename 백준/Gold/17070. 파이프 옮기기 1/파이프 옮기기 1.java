import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;
    static boolean horCheck(int r, int c){
        boolean result = false;
        if (c + 1 < N + 1 && map[r][c + 1] == 0) {
            result = true;
        }
        return result;
    }
    static boolean verCheck(int r, int c){
        boolean result = false;
        if (r + 1 < N + 1 && map[r + 1][c] == 0) {
            result = true;
        }
        return result;
    }
    static boolean sideCheck(int r, int c){
        boolean result = false;
        if (r + 1 < N + 1 && c + 1 < N + 1) {
            if (map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
                result = true;
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        map = new int[N + 1][N + 1];
        dp = new int[3][N + 1][N + 1];
        dp[0][1][2] = 1;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                //가로, 세로, 대각 순서
                if (horCheck(i, j)) {
                    dp[0][i][j + 1] += dp[0][i][j];
                    dp[0][i][j + 1] += dp[2][i][j];
                }
                if (verCheck(i, j)) {
                    dp[1][i + 1][j] += dp[1][i][j];
                    dp[1][i + 1][j] += dp[2][i][j];
                }
                if (sideCheck(i, j)) {
                    dp[2][i + 1][j + 1] += dp[0][i][j];
                    dp[2][i + 1][j + 1] += dp[1][i][j];
                    dp[2][i + 1][j + 1] += dp[2][i][j];
                }
            }
        }
        int result = 0;
        result += dp[0][N][N];
        result += dp[1][N][N];
        result += dp[2][N][N];
        bw.append(result + "");

        bw.close();
        br.close();

    }


}