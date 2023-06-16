import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        //걸리는 시간의 최소
        long left = 0;
        //걸리는 시간의 최대
        long right = (long) n * times[0];
        
        while(left<=right){
            long sum = 0;
            long mid=(left+right)/2;
            for(int i = 0; i<times.length; i++){
                sum+=mid/times[i];
            }
            if(sum<n){
                    left=mid+1;
            }
            else{
                right=mid-1;
                answer=mid;
            }
        }
        return answer;
    }
}