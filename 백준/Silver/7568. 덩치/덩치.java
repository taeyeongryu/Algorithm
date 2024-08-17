import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[2][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            arr[0][i] = weight;
            arr[1][i] = height;
        }


        int[] rank = new int[N];
        for (int i = 0; i < rank.length; i++) {
            int count = 0;
            for (int j = 0; j <rank.length; j++) {
                if(i==j){
                    continue;
                }
                if (arr[0][i] < arr[0][j] && arr[1][i] < arr[1][j]) {
                    count++;
                }
            }
            rank[i] = count+1;
            bw.append(count + 1 + " ");
        }

        bw.close();
        br.close();

    }
}