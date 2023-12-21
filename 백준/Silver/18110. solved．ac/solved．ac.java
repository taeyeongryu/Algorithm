import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int ele = (int)Math.round(N * (0.15));

        int result = 0;

        Arrays.sort(arr);

        for (int i = ele+1; i <=N-ele ; i++) {
            result += arr[i];
        }

        double meanValue = (double) result / (N - (2 * ele));

        bw.append(Math.round(meanValue) + "");
        bw.close();
        br.close();
    }
}