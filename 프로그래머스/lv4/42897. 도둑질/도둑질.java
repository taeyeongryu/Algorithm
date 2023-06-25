import java.util.*;
class Solution {
    
    public int solution(int[] money) {
        int n = money.length;
        int[] dp0 = new int[n+1];
        int[] dp1 = new int[n+1];
        dp1[1]=money[0];
        //첫째꺼 더한 것
        for(int i = 2; i<n ; i++){
            dp1[i]=Math.max(dp1[i-1],dp1[i-2]+money[i-1]);
        }
        dp1[n]=dp1[n-1];
        for(int i = 2; i<n+1 ; i++){
            dp0[i]=Math.max(dp0[i-1],dp0[i-2]+money[i-1]);
        }
        // System.out.println(Arrays.toString(dp0));
        // System.out.println(Arrays.toString(dp1));
        int answer = Math.max(dp0[n],dp1[n]);
        return answer;
    }
}