class Solution {
    public int solution(int n) {
        if(n<3){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i = 3; i<dp.length; i++){
            int tmp = dp[i-1]+dp[i-2];
            dp[i]=tmp%1000000007;
        }
        int answer = dp[n];
        return answer;
    }
}