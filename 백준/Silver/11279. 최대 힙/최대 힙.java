import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input>0){
                pq.offer(input);
            }else{
                if(pq.isEmpty()){
                    bw.append("0\n");
                }else{
                    bw.append(pq.poll()+"\n");
                }
            }
        }
        bw.close();
        br.close();
    }
}