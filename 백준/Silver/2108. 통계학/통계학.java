
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int total = 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();



        int[] plusArr = new int[4001];
        int[] minusArr = new int[4001];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());

            total += tmp;

            if (tmp >= 0) {
                plusArr[tmp]++;
            } else {
                minusArr[Math.abs(tmp)]++;
            }
            min = Math.min(min, tmp);
            max = Math.max(max, tmp);

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
        }
        bw.append(Math.round(((double) total) / N) + "\n");
        bw.append(minHeap.poll() + "\n");

        List<Integer> muchList = new ArrayList<>();

        int much = 0;

        for (int i = 0; i <4001 ; i++) {
            if(much< plusArr[i]){
                much = plusArr[i];
                muchList.clear();
                muchList.add(i);
            } else if (much == plusArr[i] && plusArr[i] != 0) {
                muchList.add(i);
            } else {
                continue;
            }
        }
        for (int i = 1; i <4001 ; i++) {
            if(much< minusArr[i]){
                much = minusArr[i];
                muchList.clear();
                muchList.add(i*(-1));
            } else if (much == minusArr[i] && minusArr[i] != 0) {
                muchList.add(i * (-1));
            } else {
                continue;
            }
        }
        Collections.sort(muchList);
        if(muchList.size()>1){
            bw.append(muchList.get(1) + "\n");
        }
        else{
            bw.append(muchList.get(0) + "\n");
        }
        bw.append((max - min) + "\n");
        bw.close();
        br.close();
    }
}