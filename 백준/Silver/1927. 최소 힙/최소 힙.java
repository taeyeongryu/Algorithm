import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input==0){
                if(pq.isEmpty()){
                    bw.append("0\n");
                }
                else{
                    bw.append(pq.poll()+"\n");
                }
            }else{
                pq.offer(input);
            }
        }
        bw.close();
        br.close();
    }
}