import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i = 1; i<words.length; i++){
            String before = words[i-1];
            String now = words[i];
            boolean isCollect = before.charAt(before.length()-1)==now.charAt(0);
            if(isCollect){
                if(set.contains(now)){
                    answer[0]= i%n + 1;
                    answer[1]= i/n + 1;    
                    break;
                }else{
                    set.add(now);
                }
            }else{
                answer[0]= i%n + 1;
                answer[1]= i/n + 1;
                break;
            }
        }

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다. 
        // System.out.println("Hello Java");

        return answer;
    }
}