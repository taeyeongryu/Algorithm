import java.util.*;

public class Solution {
    public int dp(int n){
        if(n <= 2){
            return 1;
        }
        if(n%2==0){
            return dp(n/2);
        }else{
            return dp(n-1)+1;   
        }
    }
    
    public int solution(int n) {
        return dp(n);
    }
}