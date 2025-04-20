import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int lt = 0;
        int rt = people.length-1;
        int answer = 0;
        while(lt<=rt){
            if(lt==rt){
                answer++;
                break;
            }
            if(people[lt]+people[rt]<=limit){
                answer++;
                lt++;
                rt--;
            }else{
                answer++;
                rt--;
            }
        }
        return answer;
    }
}