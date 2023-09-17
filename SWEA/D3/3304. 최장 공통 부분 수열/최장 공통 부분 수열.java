import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int tc = 1; tc <= T ; tc++) {
            st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            int aLength = A.length();
            int bLength = B.length();
            int[][] dp = new int[aLength + 1][bLength + 1];

            for (int i = 1; i <=aLength ; i++) {
                for (int j = 1; j <=bLength ; j++) {
                    if (A.charAt(i-1) == B.charAt(j-1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            System.out.printf("#%d %d%n",tc,dp[aLength][bLength]);
        }
    }
}