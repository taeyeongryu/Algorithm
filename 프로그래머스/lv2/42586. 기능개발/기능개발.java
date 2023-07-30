import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] remainday = new int[progresses.length];
        for(int i = 0; i<progresses.length; i++){
            int remaintime = 100-progresses[i];
            if(remaintime%speeds[i]==0){
                remainday[i]=remaintime/speeds[i];
            }else{
                remainday[i]=remaintime/speeds[i]+1;
            }
        }
        System.out.println(Arrays.toString(remainday));
        List<Integer> list = new LinkedList<>();
        int num = remainday[0];
        int count = 0;
        for(int i = 0; i<progresses.length; i++){
           if(num>=remainday[i]){
               count++;
           }
            else{
            list.add(count);
            count=1;
            num=remainday[i];    
            }
        }
        list.add(count);
        
        int[] answer = new int[list.size()];
        int i = 0;
        for(Integer a : list){
            answer[i++]=a;
        }
        return answer;
    }
}