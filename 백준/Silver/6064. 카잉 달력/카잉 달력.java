import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int gcd(int a, int b){
        if(a%b==0){
            return b;
        }
        else{
            return gcd(b, a % b);
        }
    }
    static int lcm(int a, int b){
        return (a * b) / gcd(a, b);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int nmLcm = lcm(N, M);
            boolean flag = false;
            for (int i1 = 0; N*i1+x <= nmLcm; i1++) {
                int num = N * i1 + x;
                if (num % M == y || (num % M == 0 && y == M)) {
                    
                    flag = true;
                    bw.append(num + "\n");
                }
            }
            if(!flag){
                bw.append("-1\n");
            }
        }
        bw.close();
        br.close();
    }
}