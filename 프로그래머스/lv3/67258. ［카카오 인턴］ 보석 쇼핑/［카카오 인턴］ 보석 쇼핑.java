import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int min = Integer.MAX_VALUE;
        int[] answer = {};
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < gems.length; i++){
            set.add(gems[i]);
        }
        HashMap<String, Integer> map = new HashMap<>();
        
        
        int lt=0;
        int rt=0;
        map.put(gems[rt],1); 
        while(lt<=rt){
            //System.out.println("lt : "+lt+", rt : "+rt+", map : "+map);
                //검사
                if(map.keySet().size()==set.size()){
                    //최소값을 경신하는지 본다.
                    //지금 값이 최소라면
                     if(min>rt-lt){
                         answer = new int[]{lt+1,rt+1};
                         min = rt-lt;
                         //lt를 하나 올려준다.
                        if(map.get(gems[lt]).equals(new Integer(1))){
                            map.remove(gems[lt]);
                        }else{
                            map.put(gems[lt],map.get(gems[lt])-1);
                        }
                        lt++;
                     }
                    //지금 값이 최소가 아니라면
                    else{
                        if(map.get(gems[lt]).equals(new Integer(1))){
                            map.remove(gems[lt]);
                        }else{
                            map.put(gems[lt],map.get(gems[lt])-1);
                        }
                        lt++;
                    }
                }
                //검사 실패 즉, 보석이 부족한 경우
                else{
                    rt++;
                    if(rt<=gems.length-1){
                        map.put(gems[rt],map.getOrDefault(gems[rt],0)+1);    
                    }else{
                        break;
                    }
                }
            
        }
        return answer;
    }
}