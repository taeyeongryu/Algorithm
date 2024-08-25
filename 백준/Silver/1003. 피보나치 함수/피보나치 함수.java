import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[][] factorial = new int[2][41];
        factorial[0][0] = 1;
        factorial[1][0] = 0;
        factorial[0][1] = 0;
        factorial[1][1] = 1;

        for(int i = 2; i < 41; i++){
            factorial[0][i] = factorial[0][i - 1] + factorial[0][i - 2];
            factorial[1][i] = factorial[1][i - 1] + factorial[1][i - 2];
        }
        for (int i = 0; i < T; i++) {
            int find = Integer.parseInt(br.readLine());
            bw.append(factorial[0][find]+" ");
            bw.append(factorial[1][find] + "\n");
        }
        bw.close();
        br.close();
    }
}