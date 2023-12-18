import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            int M = Integer.parseInt(br.readLine());

            StringTokenizer st = null;
            bw.append((M / 2) + 1 + "\n");

            List<Integer> midInt = new ArrayList<>();

            for (int j = 0; j < M; j++) {
                if (j % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }
                int tmp = Integer.parseInt(st.nextToken());
                minHeap.offer(tmp);
                if (minHeap.size() > maxHeap.size() + 1) {
                    maxHeap.offer(minHeap.poll());
                }
                //null check 해야한다.
                if(!maxHeap.isEmpty()){
                    if (minHeap.peek() < maxHeap.peek()) {
                        int minPoll = minHeap.poll();
                        int maxPoll = maxHeap.poll();

                        minHeap.offer(maxPoll);
                        maxHeap.offer(minPoll);
                    }
                }
                if (j % 2 == 0) {
                    int peek = minHeap.peek();
                    midInt.add(peek);
                }
            }
            for (int j = 0; j < midInt.size(); j++) {
                if ((j != 0 && j % 10 == 0)) {
                    bw.append("\n");
                }

                bw.append(midInt.get(j) + " ");

                if(j == midInt.size() - 1){
                    bw.append("\n");
                }
            }

        }
        bw.close();
        br.close();

    }
}