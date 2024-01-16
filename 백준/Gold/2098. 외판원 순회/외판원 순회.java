import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, dp;
    static final int INF = 987654321;
    static int N,fullBit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        fullBit = (1 << N) - 1;
        StringTokenizer st = null;
        map = new int[N][N];
        dp = new int[N][fullBit];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        System.out.println(tsp(0, 1));


    }
    static int tsp(int x, int check){
        if(check==fullBit){
            if (map[x][0] == 0) {
                return INF;
            } else {
                return map[x][0];
            }
        }
        if (dp[x][check] != -1) {
            return dp[x][check];
        }
        dp[x][check] = INF;
        for (int i = 0; i < N; i++) {
            int next = check | (1 << i);
            if (map[x][i] == 0 || (check & (1 << i)) != 0) {
                continue;
            }
            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + map[x][i]);
        }
        return dp[x][check];
    }
}