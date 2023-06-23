import java.util.*;

class Solution {
    
    int[][] dp;
    
  
    public int solution(int n, int[] money) {
        
        dp = new int[money.length][n+1];
        for(int i = 0; i<=n; i++){
            if(i%money[0]==0){
                dp[0][i]=1;
            }
        }
        for(int i = 0; i<money.length; i++){
            dp[i][0]=1;
        }
        for(int i = 1; i<money.length; i++){
            for(int j = 1; j<=n;j++){    
                dp[i][j]=(dp[i][j]+dp[i-1][j])%1000000007;
                if(j-money[i]>=0){
                     dp[i][j]=(dp[i][j]+dp[i][j-money[i]])%1000000007;
                }
            }
        }

        int answer = dp[money.length-1][n];
        return answer;
    }
}