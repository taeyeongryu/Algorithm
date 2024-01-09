import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1000000007;
    static long findMod(long under, long over, long mod){
        if (under == 0) {
            return 0;
        }
        //지수가 1이면
        if (over == 1) {
            return under % mod;
        }
        //지수가 짝수이면
        if(over%2==0){
            long firstResult = findMod(under, over / 2, mod);
            return (firstResult * firstResult) % mod;
        }else{
            long firstResult = findMod(under, over / 2, mod);
            long secondResult = firstResult * under % mod;
            return (firstResult * secondResult) % mod;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        long[] resultArr = new long[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            long b_x_2 = findMod(N, MOD - 2, MOD);

            resultArr[i] = (S * b_x_2) % MOD;
        }
        long result = 0;
        for (int i = 0; i < M; i++) {
            result = (result + resultArr[i]) % MOD;
        }
        bw.append(result + "");
        bw.close();
        br.close();
    }
}