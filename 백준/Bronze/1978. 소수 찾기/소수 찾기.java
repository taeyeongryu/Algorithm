import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static boolean[] primeArr = new boolean[1001];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 2; i<=1000; i++){
            primeArr[i]=true;
        }

        for (int i = 2; i<=1000; i++){
            if(primeArr[i]){
                for (int j = i * 2; j <= 1000; j = j + i) {
                    primeArr[j] = false;
                }
            }
        }
        
        int total = 0;
        
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());

            if(primeArr[tmp]){
                total++;
            }
        }

        bw.append(total+"");
        bw.close();
        br.close();
    }
}