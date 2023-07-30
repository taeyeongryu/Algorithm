import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] arr : clothes){
            int num = map.getOrDefault(arr[1],0);
            map.put(arr[1],num+1);
        }
        Set<String> set = map.keySet();
        int answer = 1;
        for(String str : set){
            answer*=map.get(str)+1;
        }
    
        return answer-1;
    }
}