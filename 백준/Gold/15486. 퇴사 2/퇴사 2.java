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

        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 1];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            T[i + 1] = t;
            P[i + 1] = p;
        }

        for (int i = 1; i < N + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i]);
            if (T[i] + i - 1 <= N) {
                dp[i - 1 + T[i]] = Math.max(dp[i - 1 + T[i]], dp[i - 1] + P[i]);
            }
        }
        System.out.println(dp[N]);
    }
}