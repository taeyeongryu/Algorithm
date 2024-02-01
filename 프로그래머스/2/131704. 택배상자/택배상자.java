import java.util.*;
class Solution {
    public int solution(int[] order) {
        int[] arr = new int[order.length+1];
        for(int i = 0; i<order.length; i++){
            arr[order[i]]=i+1;
        }
        
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        
        
        for(int i = 1; i<arr.length; i++){
            //지금 검사해야 되는 것
            int cur = arr[i];
            int pre = list.get(list.size()-1);
            //만약에 지금 숫자가 list뒤에 딱 맞으면?
            if(pre+1==cur){
                //리스트 뒤에 넣는다.
                list.add(cur);
            }
            //아니면
            else{
                //stack에 넣는다.
                stack.push(cur);
            }
            //스택이 비어있는지 확인하고
            while(!stack.isEmpty()){
                int topInt = stack.peek();
                pre = list.get(list.size()-1);
                //스택의 가장 위에 숫자가 list에 딱 맞으면?
                if(pre+1==topInt){
                    list.add(stack.pop());
                }
                else{
                    break;
                }
            }
        }
        int answer = list.size()-1;
        return answer;
    }
}