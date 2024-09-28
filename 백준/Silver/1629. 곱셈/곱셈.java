import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static long[] checked;
    static long pow (long A, long B, long C){
       if(B==1){
           return A % C;
       }
       else{
           long tmp = pow(A, B / 2, C);
           //B가 짝수이면
           if (B % 2 == 0) {
                   return (tmp * tmp) % C;
               }
           //B가 홀수이면
           else {
               return ((tmp * tmp) % C * A) % C;
           }
       }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long C = Integer.parseInt(st.nextToken());

        long pow = pow(A, B, C);
        bw.append(pow + "");
        bw.close();
        br.close();

    }
}