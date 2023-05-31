import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        LinkedList<Integer> dq = new LinkedList<>();
        for(int i = 0; i< operations.length; i++){
            //더하기
            if(operations[i].charAt(0)=='I'){
                // System.out.println(operations[i].substring(2));
                int tmp = Integer.parseInt(operations[i].substring(2));
                dq.add(tmp);
                Collections.sort(dq);
            }
            //최소값 삭제
            else if(operations[i].charAt(2)=='-'){
                //값이 있으면
                if(!dq.isEmpty()){
                    dq.pollFirst();
                }
            }
            //최대값 삭제
            else{
                //값이 있으면
                if(!dq.isEmpty()){
                    dq.pollLast();
                }
            }
        }
        int[] answer =new int[2];
        if(dq.isEmpty()){
            answer[0]=0;
            answer[1]=0;
        }
        else{
            answer[0] = dq.get(dq.size()-1);
            answer[1] = dq.get(0);
                
        }
        return answer;
    }
}