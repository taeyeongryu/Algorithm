import java.util.*;
class Solution {
    public int solution(int[] topping) {
        Map<Integer,Integer> leftMap = new HashMap<>();
        Map<Integer,Integer> rightMap = new HashMap<>();
        
        int answer = 0;
        //모든 토핑을 오른쪽에 넣는다.
        for(int i = 0; i < topping.length; i++){
            int curTopping = topping[i];
            rightMap.put(curTopping, rightMap.getOrDefault(curTopping, 0)+1);
        }
       
        
        //i번 index까지 왼쪽에 들어간다는 의미이다
        for(int i = 0; i < topping.length; i++){
            int curTopping = topping[i];
            int curToppingCnt = rightMap.get(curTopping);
            //오른쪽 에서 하나 뺀다.
            if(curToppingCnt==1){
                rightMap.remove(curTopping);
            }else{
                rightMap.put(curTopping,curToppingCnt-1);
            }
            leftMap.put(curTopping, leftMap.getOrDefault(curTopping, 0)+1);
            //갯수 체킹
            if(checkTopping(leftMap,rightMap)){
                answer++;
            }
        }
        return answer;
    }
    boolean checkTopping(Map<Integer,Integer> leftMap, Map<Integer,Integer> rightMap){
        return leftMap.size()==rightMap.size();
    }
}