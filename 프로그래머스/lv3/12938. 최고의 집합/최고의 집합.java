import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        if(n<=s){
            answer = new int[n];
            int q = s/n;
            int r = s%n;
            for(int i = 0; i < n-r ; i++){
                answer[i]=q;
            }
            for(int i = n-r; i < n; i++){
                answer[i]=q+1;
            }
        }
        else{
            answer = new int[1];
            answer[0]=-1;
        }
        
        return answer;
    }
}