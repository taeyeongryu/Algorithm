import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i< tangerine.length; i++){
            if(map.containsKey(tangerine[i])){
                map.put(tangerine[i],map.get(tangerine[i])+1);
            }else{
                map.put(tangerine[i],1);
            }
        }
        List<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        int sum = 0;
        int answer = 0;
        for(Map.Entry<Integer,Integer> entry : entryList){
            sum+=entry.getValue();
            answer++;
            if(sum >= k){
                break;   
            }    
        }
        
        return answer;
    }
}