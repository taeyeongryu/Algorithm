import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N + 1];
            int[][] dp = new int[2][N + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                arr[0][j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                arr[1][j] = Integer.parseInt(st.nextToken());
            }
            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];
            if(N!=1){
                for (int j = 2; j < N + 1; j++) {
                    dp[0][j] = Math.max(Math.max(dp[1][j - 1], dp[0][j - 2]), dp[1][j - 2]) + arr[0][j];
                    dp[1][j] = Math.max(Math.max(dp[0][j - 1], dp[0][j - 2]), dp[1][j - 2]) + arr[1][j];
                }
            }
            sb.append(Math.max(dp[0][N], dp[1][N]) + "\n");
        }
        System.out.println(sb.toString());
        br.close();
        
    }
}