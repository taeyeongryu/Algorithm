import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> maxQueue;
    static PriorityQueue<Integer> minQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Map<Integer, Integer> map;

        for (int t = 0; t < T; t++) {
            map = new HashMap<>();
            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>(Collections.reverseOrder());


            int K = Integer.parseInt(br.readLine());
            for (int i = 0; i < K; i++) {

                st = new StringTokenizer(br.readLine());
                char operator = st.nextToken().charAt(0);
                int inputNum = Integer.parseInt(st.nextToken());
                //삽입
                if(operator=='I'){
                    map.put(inputNum, map.getOrDefault(inputNum, 0) + 1);
                    maxQueue.offer(inputNum);
                    minQueue.offer(inputNum);
                }
                //출력
                else{
                    //최대값 빼야한다면
                    if (inputNum == 1) {
                        while (!maxQueue.isEmpty()) {
                            int peek = maxQueue.peek();
                            int num = map.getOrDefault(peek, 0);
                            if (num > 0) {
                                break;
                            } else {
                                maxQueue.poll();
                            }
                        }
                        if(!maxQueue.isEmpty()){
                            int peek = maxQueue.peek();
                            int stock = map.getOrDefault(peek, 0);
                            if (stock > 1) {
                                map.put(peek, map.get(peek) - 1);
                            } else if (stock == 1) {
                                map.remove(peek);
                            }
                            //없으면 그냥 지나가 ~
                            maxQueue.poll();
                        }
                    }
                    //최소값 빼기
                    else {
                        while (!minQueue.isEmpty()) {
                            int peek = minQueue.peek();
                            int num = map.getOrDefault(peek, 0);
                            if (num > 0) {
                                break;
                            } else {
                                minQueue.poll();
                            }
                        }
                        if(!minQueue.isEmpty()){
                            int peek = minQueue.peek();
                            int stock = map.getOrDefault(peek, 0);
                            if (stock > 1) {
                                map.put(peek, map.get(peek) - 1);
                            } else if (stock == 1) {
                                map.remove(peek);
                            }
                            //없으면 그냥 지나가 ~
                            minQueue.poll();
                        }
                    }
                }

            }
            if (map.isEmpty()) {
                bw.append("EMPTY\n");
            } else  {
                while(!maxQueue.isEmpty()){
                    int poll = maxQueue.poll();
                    if(map.containsKey(poll)){
                        bw.append(poll + " ");
                        break;
                    }
                }
                while(!minQueue.isEmpty()){
                    int poll = minQueue.poll();
                    if(map.containsKey(poll)){
                        bw.append(poll + "\n");
                        break;
                    }
                }
            }
        }
        bw.close();
        br.close();
    }
}