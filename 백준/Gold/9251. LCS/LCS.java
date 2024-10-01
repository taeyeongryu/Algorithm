import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputA = br.readLine();
        String inputB = br.readLine();

        int[][] dp = new int[inputA.length() + 1][inputB.length() + 1];
        for (int i = 1; i <= inputA.length(); i++) {
            for (int j = 1; j <= inputB.length(); j++) {
                if(inputA.charAt(i-1)==inputB.charAt(j-1)){
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j - 1] + 1, dp[i][j - 1]), dp[i - 1][j]);
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j ]);
                }
            }
        }
//        for (int i = 0; i <dp.length ; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                bw.append(dp[i][j] + " ");
//            }
//            bw.append("\n");
//        }
        bw.append(dp[inputA.length()][inputB.length()]+"");
        bw.close();
        br.close();

    }
}