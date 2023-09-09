import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] d;
    static int[][] stuff;
    static int N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
//          0 부피, 1 가치
            stuff = new int[N+1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                stuff[i][0] = Integer.parseInt(st.nextToken());
                stuff[i][1] = Integer.parseInt(st.nextToken());
            }
            d = new int[N + 1][K + 1];
            //물건번호
            for (int i = 1; i <=N; i++) {
                //가방 최대부피
                for (int j = 1; j <=K; j++) {
                    int num = 0;

                    if (j - stuff[i][0] >= 0) {
                        num = d[i - 1][j - stuff[i][0]] + stuff[i][1];
                    }
                    num = Math.max(num, d[i - 1][j]);
                    d[i][j] = num;
                }
            }
            System.out.printf("#%d %d%n", tc, d[N][K]);
        }
    }
}