import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length-1;
        int answer = 0;
        while(left<=right){
            if(people[left]+people[right]<=limit){
                answer++;
                left++;
                right--;
            }else{
                answer++;
                right--;
            }
        }
        return answer;
    }
}