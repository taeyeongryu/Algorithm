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

        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[K];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long min = 1;
        long max = arr[K - 1];


        long result = Integer.MIN_VALUE;

        while(min<=max){
            long mid = (min + max) / 2;
            long count = 0;

            for (int i = 0; i < arr.length; i++) {
                count += arr[i] / mid;
            }
            if(count>=N){
                result = Math.max(mid, result);
                min = mid + 1;
            }else{
                max = mid-1;
            }
        }
        bw.append(result + "");
        bw.close();
        br.close();

    }
}