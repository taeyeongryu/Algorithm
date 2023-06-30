import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i = 1; i<words.length; i++){
            if(!set.contains(words[i])&&words[i-1].charAt(words[i-1].length()-1)==words[i].charAt(0)){
                set.add(words[i]);
            }else{
                System.out.println(i);
                answer = new int[2];
                answer[0]=i%n+1;
                answer[1]=i/n+1;
                break;
            }    
        }
        
        

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다. 
        System.out.println("Hello Java");

        return answer;
    }
}