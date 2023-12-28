import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N+1];
        int[] ascDP = new int[N + 1];
        int[] descDP = new int[N + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(ascDP, 1);
        Arrays.fill(descDP, 1);
        ascDP[0] = 0;
        descDP[0] = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if(arr[j]<arr[i]){
                    ascDP[i] = Math.max(ascDP[i], ascDP[j] + 1);
                }
            }
        }
        for (int i = N-1; i >= 1; i--) {
            for (int j = N; j > i; j--) {
                if(arr[j]<arr[i]){
                    descDP[i] = Math.max(descDP[i], descDP[j] + 1);
                }
            }
        }
        int result = 0;
        for (int i = 1; i <=N ; i++) {
            int tmp = ascDP[i] + descDP[i]-1;
            result = Math.max(result, tmp);
        }
        System.out.println(result);
    }
}