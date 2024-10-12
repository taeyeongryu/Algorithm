import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N, M;
    static int[] inputCheck;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputCheck = new int[N + 1];
        adjList = new ArrayList[N + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        //pd숫자
        for (int i = 0; i < M; i++) {
            int before = -1;
            st = new StringTokenizer(br.readLine());
            int singerNum = Integer.parseInt(st.nextToken());

            for (int j = 0; j < singerNum; j++) {
                int now = Integer.parseInt(st.nextToken());
                if(before==-1){
                    before = now;
                    continue;
                }
                adjList[before].add(now);
                inputCheck[now]++;
                before = now;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < inputCheck.length; i++) {
            if(inputCheck[i]==0){
                queue.offer(i);
            }
        }
        List<Integer> resultList = new ArrayList<>();
        while(!queue.isEmpty()){
            int cur = queue.poll();
            resultList.add(cur);
            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                inputCheck[next]--;
                if(inputCheck[next]==0){
                    queue.offer(next);
                }
            }
        }
        boolean flag = true;

        for (int i = 0; i <inputCheck.length ; i++) {
            if(inputCheck[i]!=0){
                flag = false;
                break;
            }
        }

        if(flag){
            for (int i = 0; i < resultList.size(); i++) {
                bw.append(resultList.get(i) + "\n");
            }
        } else{
            bw.append("0");
        }
        bw.close();
        br.close();
    }
}