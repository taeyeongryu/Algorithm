import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> maxQueue= new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> minQueue= new PriorityQueue<>();
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < T; i++) {
            maxQueue.clear();
            minQueue.clear();
            map.clear();
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                String oper = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                //값 삽입
                if (oper.equals("I")) {
                    insert(num);
                } else {
                    //최대값 제거
                    if (num==1){
                        removeMaxValue();
                    }
                    //최소값 제거
                    else{
                        removeMinValue();
                    }
                }
            }

            if (!map.isEmpty()) {
                int max = removeMaxValue();
                bw.append(max+ " ");
                if(map.isEmpty()){
                    bw.append(max+"\n");    
                }else{
                    bw.append(removeMinValue() + "\n");
                }
            } else {
                bw.append("EMPTY\n");
            }
        }
        bw.close();
        br.close();
    }

    //현재 queue에서 가지고있는 최소값을 제거한다.
    //맞지 않는 최소값도 계속 삭제한다.
    private static int  removeMinValue() {
        int min = 0;
        if(!map.isEmpty()){
            while(true){
                min = minQueue.poll();
                int getValue = map.getOrDefault(min, 0);
                if(getValue ==0){
                    continue;
                } else if (getValue ==1) {
                    map.remove(min);
                    break;
                }else{
                    map.put(min, getValue - 1);
                    break;
                }
            }

        }
        return min;
    }

    private static int  removeMaxValue() {
        int max = 0;
        if(!map.isEmpty()){
            while(true){
                max = maxQueue.poll();
                int getValue = map.getOrDefault(max, 0);
                if(getValue ==0){
                    continue;
                } else if (getValue ==1) {
                    map.remove(max);
                    break;
                }else{
                    map.put(max, getValue - 1);
                    break;
                }
            }

        }
        return max;
    }

    private static void insert(int num) {
        maxQueue.offer(num);
        minQueue.offer(num);
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
}