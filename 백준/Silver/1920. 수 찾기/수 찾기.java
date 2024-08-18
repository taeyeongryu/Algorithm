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
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] target = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < target.length; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        int[] origin = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < origin.length; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(target);

        outer:for (int i = 0; i < M; i++) {
            int num = origin[i];
            int lt = 0;
            int rt = N - 1;
            while (lt <= rt) {
                int mid = (lt + rt) / 2;
                if (num == target[mid]) {
                    bw.append("1\n");
                    continue outer;
                } else if (num < target[mid]) {
                    rt = mid - 1;
                } else {
                    lt = mid + 1;
                }
            }
            bw.append("0\n");
        }

        bw.close();
        br.close();

    }
}