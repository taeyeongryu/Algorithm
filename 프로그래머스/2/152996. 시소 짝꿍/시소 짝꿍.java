import java.util.*;
class Solution {
    Map<Integer,Integer> map;
    public long check(int a){
        long result = 0;
        if(map.containsKey(a)){
            result+=map.get(a);
        }
        if(map.containsKey(2*a)){
            result+=map.get(2*a);
        }
        if(a%2==0&&map.containsKey(a*3/2)){
            result+=map.get(a*3/2);
        }
        if(a%3==0&&map.containsKey(a*4/3)){
            result+=map.get(a*4/3);
        }
        return result;
    }
    public long solution(int[] weights) {
        long answer = 0;
        map = new HashMap<>();
        for(int i = 0; i<weights.length; i++){
            map.put(weights[i],map.getOrDefault(weights[i],0)+1);
        }
        Arrays.sort(weights);
        for(int i = 0; i<weights.length; i++){
            int num = map.get(weights[i]);
            //지금 숫자 map에서 제거한다.
            if(num==1){
                map.remove(weights[i]);
            }else{
                map.put(weights[i],num-1);
            }
            
            answer+=check(weights[i]);
        }
        return answer;
    }
}