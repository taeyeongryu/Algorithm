import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Document{
        int index;
        int weight;
        public Document(int index,int weight){
            this.weight=weight;
            this.index=index;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            LinkedList<Integer> weightList = new LinkedList<>();
            Queue<Document> queue = new LinkedList<>();
            int count = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int index = i;
                int weight = Integer.parseInt(st.nextToken());
                weightList.add(weight);
                queue.offer(new Document(index, weight));
            }
            Collections.sort(weightList, Collections.reverseOrder());
            while(!queue.isEmpty()){
                Document nowDocument = queue.poll();
                //지금 중요도가 젤 높을 때
                if(weightList.get(0)== nowDocument.weight){
                    count++;
                    weightList.removeFirst();
                    if (nowDocument.index == M) {
                        break;
                    }
                }
                //더 중요한 게 있을 때
                else{
                    queue.offer(nowDocument);
                }
            }
            bw.append(count + "\n");
        }
        bw.close();
        br.close();

    }
}