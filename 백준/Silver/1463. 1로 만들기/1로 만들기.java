import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        if(N>=2){
            for (int i = 2; i < dp.length; i++) {
                if (i % 3 == 0) {
                    dp[i] = Math.min(dp[i], dp[i / 3] + 1);
                }
                if (i % 2 == 0) {
                    dp[i] = Math.min(dp[i], dp[i / 2] + 1);
                }
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        }
//        System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp));
        bw.append(dp[N]+"");
        bw.close();
        br.close();
    }
}