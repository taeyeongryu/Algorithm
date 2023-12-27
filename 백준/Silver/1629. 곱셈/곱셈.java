import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static long pow(long A, long B, long C) {
        if(B==1){
            return A % C;
        }
        if(B==0){
            return 1;
        }
        //B가 짝수일 때
        long pow1 = 0;
        long pow2 = 0;
        if(B%2==0){
            pow1 = pow(A, B / 2, C);
            pow2 = pow1;
        }else{
            pow1 = pow(A, B / 2, C);
            pow2 = (pow1 * A) % C;
        }
        long result = (pow1 * pow2) % C;
        return result;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long C = Integer.parseInt(st.nextToken());
        long result = pow(A, B, C);
        System.out.println(result);
    }
}