import java.util.*;
class Solution {
    
    public int solution(int storey) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{storey,0});
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(cur[0]/10==0){
                min = Math.min(min,cur[1]+cur[0]%10);
                min = Math.min(min,11+cur[1]-cur[0]%10);
                continue;
            }
            int tmp = cur[0]%10;

            q.offer(new int[]{cur[0]/10,cur[1]+tmp});
            q.offer(new int[]{cur[0]/10+1,cur[1]+10-tmp});
        }
        return min;
    }
}