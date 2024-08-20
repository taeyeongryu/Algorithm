import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }
        int count = 0;
        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            count++;
            int poll = q.poll();
            if(count!=K){
                q.offer(poll);
            }else{
                list.add(poll);
                count = 0;
            }
        }
        bw.append("<");

        for (int i = 0; i < list.size(); i++) {
            bw.append(list.get(i)+"");
            if(i==list.size()-1){
                break;
            }
            bw.append(", ");
        }
        bw.append(">");
        bw.close();
        br.close();

    }
}