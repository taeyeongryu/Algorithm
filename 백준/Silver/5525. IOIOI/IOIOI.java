import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray();
        int count = 0;
        int result = 0;

        for (int i = 0; i < M-2; i++) {
            if (arr[i] == 'I' && arr[i + 1] == 'O' && arr[i + 2] == 'I') {
                count++;
                i++;
            }else{
                count = 0;
            }
            if(count==N){
                count--;
                result++;
            }
        }
        System.out.println(result);
    }
}