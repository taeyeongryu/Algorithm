import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        final int M = 1234567891;
        final int R = 31;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[] rArr = new long[N];
        rArr[0] = 1;
        for (int i = 1; i < rArr.length; i++) {
            rArr[i] = (rArr[i - 1] * 31) % M;
        }

        String str = br.readLine();
        long result = 0;
        for (int i = 0; i <str.length() ; i++) {
            char ch = str.charAt(i);
            int chToInt = ch - 'a' + 1;
            long tmp = (chToInt * rArr[i]) % M;
            result = (result + tmp) % M;
        }
        bw.append(result + "");
        bw.close();
        br.close();
    }
}