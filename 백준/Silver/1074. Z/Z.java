import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,R,C, count;
    static void find(int r, int c, int N){
        if (r == R && c == C) {
            count++;
            return;
        }
        int length = (int) Math.pow(2, N);
        //2 사분면
        if (R < r + length/2 && C <c + length/2) {
            find(r, c, N - 1);
        }
        //3 사분면
        else if (R >= r + length/2 && C <c + length/2) {
            count += (length / 2) * (length / 2) * 2;
            find(r + length / 2, c,N-1);
        }
        //1 사분면
        else if (R <r + length / 2 && C >=c + length / 2) {
            count += (length / 2) * (length / 2);
            find(r, c + length / 2, N - 1);
        }
        //4 사분면
        else {
            count += (length / 2) * (length / 2) * 3;
            find(r + length / 2, c + length / 2, N - 1);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        find(0, 0, N);
        bw.append(count - 1 + "");
        bw.close();
        br.close();

    }
}