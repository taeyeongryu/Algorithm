import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int a:scoville){
            pq.add(a);
        }
        while(!pq.isEmpty()){
            //하나인데
            if(pq.size()==1){
                //조건을 만족하면 ture
                int min = pq.poll();
                
                if(min>=K){return count;}
                //조건을 만족하지 못하면 false;
                else{return -1;}
            }else{
                int min = pq.poll();
                //만약 최소 값이 조건을 만족하면
                if(min>=K){return count;}
                else{
                    int secondMin = pq.poll();
                    pq.add(min+(secondMin*2));
                    count++;
                }
            }
        }
       return count;
    }
}