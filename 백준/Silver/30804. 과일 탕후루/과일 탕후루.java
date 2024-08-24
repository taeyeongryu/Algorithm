import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
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
        Map<Integer, Integer> fruitKind = new HashMap<>();
        fruitKind.put(fruit[right], 1);
        int max = 0;
        while (right < N && left < N) {
            //검사
            int kind = fruitKind.keySet().size();

            //종류가 2개 이하이면?
            if (kind <= 2) {
                max = Integer.max(right - left + 1, max);

                //증
                if(right<N-1){
                    right++;
                }else{
                    break;
                }
                if (fruitKind.getOrDefault(fruit[right], 0) == 0) {
                    fruitKind.put(fruit[right], 1);
                } else {
                    fruitKind.put(fruit[right], fruitKind.get(fruit[right]) + 1);
                }
            }
            //종류가 2개 초과이면
            else {
                //감
                if(fruitKind.get(fruit[left])>1){
                    fruitKind.put(fruit[left], fruitKind.get(fruit[left]) - 1);
                }else{
                    fruitKind.remove(fruit[left]);
                }
                left++;
            }
        }
        System.out.println(max);
    }
}