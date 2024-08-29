import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Pair implements Comparable<Pair>{
        int index;
        int number;
        Pair(int index, int number){
            this.index = index;
            this.number = number;
        }

        @Override
        public int compareTo(Pair pair){
            return Integer.compare(this.number, pair.number);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Pair> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            list.add(new Pair(i, tmp));
        }
        Collections.sort(list);
        int[] resultArr = new int[N+1];

        for(int i = 1; i <= N; i++){
            Pair pair = list.get(i-1);
            int index = pair.index;
            int number = pair.number;
            if(i==1){
                resultArr[index] = 0;
            }
            else{
                if (list.get(i - 2).number < number) {
                    resultArr[index] = resultArr[list.get(i - 2).index] + 1;
                } else {
                    resultArr[index] = resultArr[list.get(i - 2).index];
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            bw.append(resultArr[i]+" ");
        }
        bw.close();
        br.close();
    }
}