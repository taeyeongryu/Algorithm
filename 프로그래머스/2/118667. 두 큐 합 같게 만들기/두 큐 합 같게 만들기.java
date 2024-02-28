import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        // long total = 0;
        long oneSum = 0;
        long twoSum = 0;
        for(int i = 0; i<queue1.length; i++){
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            oneSum+=queue1[i];
            twoSum+=queue2[i];
        }
        long goal = (oneSum+twoSum)/2;
        
        if((oneSum+twoSum)%2==1){return -1;}
        int time = 0;
        while(true){
            if(goal==oneSum){
                break;
            }
            if(time>4*(queue1.length)){
                return -1;
            }
            if(oneSum>goal){
                int tmp = q1.poll();
                q2.offer(tmp);
                oneSum-=tmp;
                twoSum+=tmp;
            }else{
                int tmp = q2.poll();
                q1.offer(tmp);
                oneSum+=tmp;
                twoSum-=tmp;
            }
            time++;
        }
        return time;
    }
}