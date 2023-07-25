import java.util.*;
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        // System.out.println(Arrays.toString(citations));
        int answer = 0;
        for(int i = 0; i<citations.length; i++){
            int h = citations.length-i;
            if(citations[i]>=h){
                answer=h;
                break;
            }
        }
        return answer;
    }
}