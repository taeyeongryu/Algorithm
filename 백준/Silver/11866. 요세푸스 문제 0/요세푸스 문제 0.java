import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<Integer> queue = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        int count = 1;

        List<Integer> result = new ArrayList<>();

        while(!queue.isEmpty()){
            if(count==k){
                count = 1;
                Integer poll = queue.poll();
                result.add(poll);
            }
            else{
                count++;
                queue.offer(queue.poll());
            }
        }
        bw.append("<");
        for (int i = 0; i < result.size(); i++) {
            if(i!=result.size()-1){
                bw.append(result.get(i) + ", ");
            }else{
                bw.append(result.get(i)+"");
            }
        }
        bw.append(">");
        bw.close();
        br.close();
    }
}