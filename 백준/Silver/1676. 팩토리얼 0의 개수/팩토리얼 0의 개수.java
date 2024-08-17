import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int num = i;
            if(num%5==0){
                while(num%5==0){
                    count++;
                    num /= 5;
                }
            }
        }
        System.out.println(count);
    }
}