import java.util.*;
class Solution {
    
    public int solution(String s) {
        Map<Character,Integer> left= new HashMap<>();
        Map<Character,Integer> right= new HashMap<>();
        left.put('(',1);
        left.put('{',2);
        left.put('[',3);
        right.put(')',1);
        right.put('}',2);
        right.put(']',3);
        
        int answer = 0;
        if(s.length()%2==1){
            return 0;
        }
        for(int i = 0; i<s.length(); i++){
            answer+=isGood(s,i,left,right)?1:0;
        }
        return answer;
    }
    public boolean isGood(String str,int index ,Map<Character,Integer> left,Map<Character,Integer> right){
        Stack<Character> stack = new Stack<>();
        for(int i = index; i < index + str.length(); i++){
            char cur = str.charAt(i % str.length()); 
            if(left.containsKey(cur)){
                stack.add(cur);
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    if(left.get(stack.peek())==right.get(cur)){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
            
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
}