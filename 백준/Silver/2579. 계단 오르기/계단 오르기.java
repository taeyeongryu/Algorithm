import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] step = new int[N + 1];
        int[][] dp = new int[2][N + 1];


        for (int i = 1; i < N + 1; i++) {
            step[i] = Integer.parseInt(br.readLine());
        }
        dp[0][1]=step[1];
        if(N>=2){
            dp[0][2] = dp[0][1] + step[2];
            dp[1][2] = step[2];
        }
        if(N>=3){
            for (int i = 3; i < N + 1; i++) {
                dp[1][i] = Math.max(dp[0][i - 2], dp[1][i - 2]) + step[i];
                dp[0][i] = dp[1][i - 1] + step[i];
            }
        }
        int result = Math.max(dp[0][N], dp[1][N]);
        
        bw.append(result + "");
        bw.close();
        br.close();
    }
}