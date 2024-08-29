import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, B, sum,min,max;
    static int[] heights;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        heights = new int[N * M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                heights[i * M + j] = Integer.parseInt(st.nextToken());
                sum += heights[i * M + j];
            }
        }
        Arrays.sort(heights);
        min = heights[0];
        max =  (B + sum) / (N * M);
        
        
        long result = Long.MAX_VALUE;
        int height = 0;
        for (int i = min; i <= max; i++) {
            long tmp = 0;
            for (int j = 0; j < heights.length; j++) {
                if(heights[j]<=i){
                    tmp += i - heights[j];
                }else{
                    tmp += (heights[j] - i) * 2;
                }
            }
            if(result>=tmp){
                result =tmp;
                height = i;
            }

        }
        bw.append(result + " " + height);
        bw.close();
        br.close();
    }
}