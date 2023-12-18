import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Document{
        int index;
        int weight;
        public Document(int index, int weight){
            this.index = index;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Document{" +
                    "index=" + index +
                    ", weight=" + weight +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        Queue<Document> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i <T ; i++) {
            queue.clear();
            pq.clear();

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());


            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int weight = Integer.parseInt(st.nextToken());
                queue.offer(new Document(j, weight));
                pq.offer(weight);
            }

            while(!queue.isEmpty()){
                Document cur = queue.poll();
                int max = pq.peek();

                //최대 값이라면
                //q에서 제거한다.
                //찾는 값이라면 출력하고 break;
                if (cur.weight == max) {
                    pq.poll();
                    //찾는 값이라면
                    if(cur.index==M){
                        bw.append("" +(N-queue.size())+"\n");
                        break;
                    }
                }
                //최대값이 아니라면 q의 맨 뒤로 보낸다.
                else {
                    queue.offer(cur);
                }
            }

        }
        bw.close();
        br.close();

    }
}