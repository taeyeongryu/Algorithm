import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack stack = new Stack();
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.add(0);
            }else{
                //스택이 비었다면.
                if(stack.isEmpty()){
                    answer=false;
                    break;
                }
                else{
                    stack.pop();
                }
            }
        }
        
      if(!stack.isEmpty()){
          answer=false;
      }
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}