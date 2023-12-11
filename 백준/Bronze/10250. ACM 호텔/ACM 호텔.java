import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int p = N/H;
            int q = N%H;
            String result = "";
            if(q==0){
                result = H + result;
                result += p >= 10 ? p : ("0" + p);
            }else{
                result = q + result;
                result += p >= 9 ? p+1 : ("0" + (p+1));
            }
            bw.append(result+"\n");
        }
        bw.close();
        br.close();

    }
}