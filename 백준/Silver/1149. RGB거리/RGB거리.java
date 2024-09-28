import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][3];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[3][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[j][i] = Math.min(dp[(j + 1) % 3][i - 1], dp[(j + 2) % 3][i - 1]) + map[i][j];
            }
        }
//        for (int i = 0; i < 3; i++) {
//            for (int j = 1; j <N+1 ; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, dp[i][N]);
        }
        bw.append(result + "");
        bw.close();
        br.close();
    }
}