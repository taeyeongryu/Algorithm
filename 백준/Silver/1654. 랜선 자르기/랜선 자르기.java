import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int K, N;
    static long MAX = Long.MIN_VALUE;
    static int[] kable;
    static void maxSize(){
        long left = 1;
        long right = Integer.MAX_VALUE;
        long mid = 0;
        while(left<=right){
            mid = (left + right) / 2;
            long total = count(mid);
            if(total>=N){
                MAX = Math.max(MAX, mid);
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
    }

    private static long count(long mid) {
        long count = 0;
        for (int i : kable) {
            count += i / mid;
        }
        return count;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
//        if(K==N){
//
//        }
//        else{
            kable = new int[K];
            for (int i = 0; i < K; i++) {
                kable[i] = Integer.parseInt(br.readLine());
            }
            maxSize();
            System.out.println(MAX);
        }
//    }
}