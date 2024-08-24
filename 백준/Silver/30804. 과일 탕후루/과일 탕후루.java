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

        int[] fruit = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < fruit.length; i++) {
            fruit[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;
        int[] fruitKind = new int[11];
        fruitKind[fruit[right]] = 1;
        int max = 0;
        while (right < N && left < N) {
            //검사
            int kind = 0;
            for (int i = 0; i < fruitKind.length; i++) {
                if(fruitKind[i]>0){
                    kind++;
                }
            }

            //종류가 2개 이하이면?
            if (kind <= 2) {
                max = Integer.max(right - left + 1, max);

                //증
                if(right<N-1){
                    right++;
                }else{
                    break;
                }
                if (fruitKind[fruit[right]] == 0) {
                    fruitKind[fruit[right]] = 1;
                } else {
                    fruitKind[fruit[right]]++;
                }
            }
            //종류가 2개 초과이면
            else {
                //감
                if (fruitKind[fruit[left]] > 1) {
                    fruitKind[fruit[left]]--;
                } else {
                    fruitKind[fruit[left]] = 0;
                }
                left++;
            }
        }
        bw.append(max + "");
        bw.close();
        br.close();

    }
}