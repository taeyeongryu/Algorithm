import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length ; i++){
            pq.offer(works[i]);
        }
        while(n>0){
            if(pq.peek()>0){
                int tmp = pq.poll();
                tmp--;
                n--;
                pq.offer(tmp);
            }
            else{
                break;
            }
        }
        long answer = 0;
        System.out.println(pq);
        for(int i = 0; i < works.length; i++){
            int tmp = pq.poll();
            answer+=tmp*tmp;
        }
        
        return answer;
    }
}