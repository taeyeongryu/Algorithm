class Solution {
    int solution(int[][] land) {
        int N = land.length;
        int[][] dp = new int[N+1][4];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    if(j==k)continue;
                    dp[i+1][k] = Math.max(dp[i+1][k],dp[i][j]+land[i][k]);
                }
            }
        }
        int max = 0;
        for(int i = 0; i < 4; i++){
            max = Math.max(max,dp[N][i]);
        }
        int answer = max;
        return answer;
    }
}